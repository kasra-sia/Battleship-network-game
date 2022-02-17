package ap.mini_project.server.controller;

import ap.mini_project.server.controller.game.Game;
import ap.mini_project.server.controller.game.GameLobby;
import ap.mini_project.server.controller.game.SeaBattle.SeaBattleGame;
import ap.mini_project.server.controller.network.ResponseSender;
import ap.mini_project.server.db.Context;
import ap.mini_project.shared.model.*;
import ap.mini_project.shared.requests.LoginForm;
import ap.mini_project.shared.requests.RequestVisitor;
import ap.mini_project.shared.requests.StartGameRequest;
import ap.mini_project.shared.response.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class ClientHandler extends Thread implements RequestVisitor {
    private final ResponseSender sender;
    private final Context context;
    private final GameLobby gameLobby;
    private Side side;
    private Game game;
    private Player owner;
    private volatile boolean running;


    public ClientHandler(ResponseSender sender, GameLobby gameLobby) throws IOException {
        context = new Context();
        this.sender = sender;
        this.gameLobby = gameLobby;
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    public void run() {
        while (running) {
            try {
                sender.sendResponse(sender.getRequest().visit(this));
            } catch (NoSuchElementException e) {
                if (owner != null) {
                    System.out.println("@" + owner.getUsername() + " disconnected");
                    owner.setPlayerStatus(PlayerStatus.OFFLINE);
                    context.Players.update(owner);
                    synchronized (gameLobby){
                    if (gameLobby.getWaiting() == this)
                        gameLobby.setWaiting(null);}
                    if (game!=null){
                      game.getBoard(side).getShips().forEach(ship -> ship.setHealth(0));
                      game.getGameState().setSideToTurn(side.getOther());
                      game.getGameState().checkForEndGame();
                    }
                } else {
                    System.out.println("client disconnected");

                }sender.close();
                running = false;
            }
        }

    }

    @Override
    public Response startGame(StartGameRequest startGameRequest) {
        gameLobby.startGameRequest(this);
        return getBoard();
    }

    @Override
    public Response clickOnBoard(int x, int y) {
        game.click(x, y, side);
        return getBoard();
    }

    @Override
    public Response getBoard() {
        Board board1 = null;
        Board board2 = null;
        if (game == null) {
            board1 = new Board(10, 10);
            board2 = new Board(10, 10);
            return new BoardResponse(new GameForm(board1, board2, "", "", ""));
        } else {
            if (game.getGameStatus() == GameStatus.WAITING
                    || game.getGameStatus() == GameStatus.RUNNING) {
                board1 = game.getBoard(side);
                board2 = game.getBoard(side.getOther());

                switch (game.getGameStatus()) {
                    case WAITING:
                        board1 = game.getBoard(side);
                        board2 = game.getBoard(side.getOther());
                        if (game.getRemainingTime(side) == 0 && game.getRemainingTime(side.getOther()) == 0)
                            game.startGame();
                        break;
                    case RUNNING:
                        board1 = game.getBoard(side);
                        board2 = game.getBoard(side.getOther());
                        if (game.getRemainingTime(side) == 0 && game.getGameState().getSideToTurn() == side)
                            game.nextTurn(side);
                        break;
                }
            } else {
                Game game = this.game;
                SeaBattleGame.liveGames.remove(((SeaBattleGame) this.game));
                this.game = null;
                switch (game.getGameStatus()) {
                    case PLAYER_ONE_WON:
                        if (side == Side.PLAYER_ONE)
                            return new ShowMessage("win");
                        else return new ShowMessage("lose");
                    case PLAYER_TWO_WON:
                        if (side == Side.PLAYER_TWO)
                            return new ShowMessage("win");
                        else return new ShowMessage("lose");
                }
            }
        }

        return new BoardResponse(new GameForm(board1,
                board2,
                String.valueOf(game.getRemainingTime(side)),
                game.getUsername(side.getOther()),
                game.getGameStatus().toString()));
    }

    @Override
    public Response login(LoginForm loginForm) {
        LinkedList<Player> temp = new LinkedList<>(context.Players.all());
        for (Player player : temp) {
            if (player.getUsername().equals(loginForm.getUsername())) {
                if (player.getPassword().equals(loginForm.getPassword())
                        && player.getPlayerStatus() != PlayerStatus.ONLINE) {
                    owner = player;
                    player.setPlayerStatus(PlayerStatus.ONLINE);
                    context.Players.update(owner);
                    return new LoginResponse(true, "authToken");
                }
            }
        }
        return new LoginResponse(false, "wrongUsernameOrPassword");
    }

    @Override
    public Response register(LoginForm loginForm) {
        boolean flag = false;
        if (context.Players.all() != null)
            for (Player player : context.Players.all()) {
                if (player.getUsername().equals(loginForm.getUsername()))
                    flag = true;
            }
        if (flag)
            return new RegisterResponse(false, "duplicateUsername");
        else {
            owner = new Player(loginForm.getUsername(), loginForm.getPassword());
            owner.setPlayerStatus(PlayerStatus.ONLINE);
            context.Players.add(owner);
            return new RegisterResponse(true, "authToken");

        }
    }

    @Override
    public Response getPersonalInfo() {
        return new PersonalInfoResponse(new PersonalInfoForm(
                owner.getWinnings(),
                owner.getLoses(),
                owner.getScore(),
                owner.getUsername()));
    }

    @Override
    public Response getScoreBoard() {
        LinkedList<PlayerScoreForm> playerScoreForms = new LinkedList<>();
        for (Player player : context.Players.all()) {
            playerScoreForms.add(new PlayerScoreForm(
                    player.getUsername(),
                    String.valueOf(player.getScore()),
                    player.getPlayerStatus()));
        }
        Comparator<PlayerScoreForm> comparator = Comparator.comparing(PlayerScoreForm::getScore);
        Collections.sort(playerScoreForms, comparator);
        return new ScoreBoardResponse(playerScoreForms);
    }

    @Override
    public Response clickOnBtn(String s) {
        if (s.equals("changeBoard")) {
            game.changeBoard(side);
            return getBoard();
        }

        if (s.equals("ready")) {
            game.setReady(side, true);

        }
        if (s.equals("unready")) {
            game.setReady(side, false);
        }
        return getBoard();
    }

    @Override
    public Response visitStringRequest(String s) {
        return null;
    }

    @Override
    public Response getLiveStream(int i) {
        for (SeaBattleGame liveGame:SeaBattleGame.liveGames) {
            if (liveGame.getId() == i) {
                String[]temp = new String[2];
                temp[0]=liveGame.getGameState().getPlayers()[0].getUsername();
                temp[1]=liveGame.getGameState().getPlayers()[1].getUsername();
                return new LiveStreamResponse(new LiveGameForm(
                        temp,
                        liveGame.getGameState().getBoards(),
                        liveGame.getGameState().getSideToTurn(),
                        liveGame.getGameStatus(),
                        liveGame.getId()));
            }
        }
        return null;
    }

    @Override
    public Response getLiveGames() {
        LinkedList<LiveGameForm> forms = new LinkedList<>();
        SeaBattleGame.liveGames.forEach(liveGame->{
            String[]temp = new String[2];
            temp[0]=liveGame.getGameState().getPlayers()[0].getUsername();
            temp[1]=liveGame.getGameState().getPlayers()[1].getUsername();
            forms.add(new LiveGameForm(
                    temp,
                    liveGame.getGameState().getBoards(),
                    liveGame.getGameState().getSideToTurn(),
                    liveGame.getGameStatus(),
                    liveGame.getId()));
        });
        return new LiveGamesResponse(forms);
    }


    public void setSide(Side side) {
        this.side = side;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getOwner() {
        return owner;
    }
}
