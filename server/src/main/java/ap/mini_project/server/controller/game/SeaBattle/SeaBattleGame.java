package ap.mini_project.server.controller.game.SeaBattle;
import ap.mini_project.server.controller.game.Game;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.model.Player;
import ap.mini_project.shared.model.Side;

import java.util.LinkedList;

public class SeaBattleGame implements Game {
    public volatile static int idMaker = 0;

    public int getId() {
        return id;
    }

    private int id;
    public static final LinkedList<SeaBattleGame> liveGames = new LinkedList<>();
    private final GameState gameState ;
    public SeaBattleGame(Player player1,Player player2) {
        this.id = idMaker;
        idMaker++;
        gameState = new GameState(player1,player2);
        gameState.getBoards()[0].setMessage("waiting for enemy");
        gameState.getBoards()[1].setMessage("waiting for enemy");
        synchronized (liveGames){
        liveGames.add(this);}
    }

    @Override
    public void click(int x, int y, Side side) {
          if (gameState.getGameStatus() ==GameStatus.RUNNING)
          gameState.shootOnCell(x,y,side);

    }

    @Override
    public Board getBoard(Side side) {
        return gameState.getBoards()[side.getIndex()];
    }

    @Override
    public int getResult() {
        return gameState.getResult();
    }

    @Override
    public GameState getGameState() {
      return this.gameState;
    }

    @Override
    public synchronized void setReady(Side side, boolean isReady) {
        gameState.getIsReady()[side.getIndex()] = isReady;
        if (isReady) {
            getBoard(side.getOther()).setMessage("enemy is ready");
            if(gameState.getIsReady()[side.getOther().getIndex()] && gameState.getIsReady()[side.getIndex()])
                startGame();

        }else getBoard(side.getOther()).setMessage("waiting for enemy");
    }

    @Override
    public int getRemainingTime(Side side) {
        return gameState.getRemainingTime()[side.getIndex()];
    }

    @Override
    public synchronized void setRemainingTime(int time,Side side) {
        gameState.getRemainingTime()[side.getIndex()] = time;
    }

    @Override
    public void changeBoard(Side side) {
        if (getRemainingTime(side) != 0
                && getGameState().getNumberOfChangingMap()[side.getIndex()] < 3) {
            gameState.changeBoard(side);
            getGameState().getRemainingTime()[side.getIndex()] += 10;
            getGameState().getNumberOfChangingMap()[side.getIndex()]++;
            if (getGameState().getNumberOfChangingMap()[side.getIndex()] == 3) {
                setRemainingTime(0,side);
                setReady(side,true);
            }
        }
    }

    @Override
    public GameStatus getGameStatus() {
        return gameState.getGameStatus();
    }

    @Override
    public String getUsername(Side side) {
        return gameState.getPlayers()[side.getIndex()].getUsername();
    }

    @Override
    public void nextTurn(Side side) {

        gameState.setSideToTurn(side.getOther());
        setRemainingTime(25,side.getOther());
        getBoard(side).setMessage("enemy's turn");
        getBoard(side.getOther()).setMessage("your turn");
    }

    public void startGame(){
        gameState.setGameStatus(GameStatus.RUNNING);
        setRemainingTime(25,Side.PLAYER_ONE);
        gameState.setSideToTurn(Side.PLAYER_ONE);
        gameState.getBoards()[0].setMessage("your turn");
        gameState.getBoards()[1].setMessage("enemy's turn");
        setRemainingTime(0,Side.PLAYER_TWO);

    }
}
