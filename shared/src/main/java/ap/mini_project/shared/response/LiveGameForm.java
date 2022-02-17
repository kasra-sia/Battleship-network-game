package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.model.Side;

public class LiveGameForm {
    private String[] players = new String[2];
    private Board[] boards = new Board[2];
    private final String[]damagedCells = new String[2];
    private final String[]damagedShips = new String[2];
    private final int gameID;
    private final Side sideToTurn;
    private final GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public LiveGameForm(String[] players, Board[] boards, Side sideToTurn, GameStatus gameStatus, int gameID) {
        this.players = players;
        this.boards = boards;
        this.sideToTurn = sideToTurn;
        this.gameStatus = gameStatus;
        this.gameID = gameID;
        this.damagedCells[0] = String.valueOf(boards[0].getDamagedCells());
        this.damagedCells[1] = String.valueOf(boards[1].getDamagedCells());
        this.damagedShips[0] = String.valueOf(boards[0].getDamagedShips());;
        this.damagedShips[1] = String.valueOf(boards[1].getDamagedShips());;
    }

    public String[] getPlayers() {
        return players;
    }

    public Board[] getBoards() {
        return boards;
    }

    public String[] getDamagedCells() {
        return damagedCells;
    }

    public String[] getDamagedShips() {
        return damagedShips;
    }

    public int getGameID() {
        return gameID;
    }

    public Side getSideToTurn() {
        return sideToTurn;
    }
}
