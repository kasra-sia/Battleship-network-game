package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;

public class GameForm {
    private final Board[]boards = new Board[2];
    private String remainingTime = "0" ;
    private final String enemyUsername;
    private final String gameStatus;

    public GameForm(Board board1, Board board2, String remainingTime,String enemyUsername, String gameStatus) {
        this.enemyUsername = enemyUsername;
        this.gameStatus = gameStatus;
        boards[0]=board1;
        boards[1]=board2;
        this.remainingTime = remainingTime;
    }

    public Board[] getBoards() {
        return boards;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public String getEnemyUsername() {
        return enemyUsername;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
