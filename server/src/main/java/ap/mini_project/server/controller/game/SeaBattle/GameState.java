package ap.mini_project.server.controller.game.SeaBattle;


import ap.mini_project.server.controller.game.BoardBuilder;
import ap.mini_project.server.db.Context;
import ap.mini_project.shared.model.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    private Side sideToTurn;
    private int result = -1;
    private Player[] players = new Player[2];
    private Board[] boards = new Board[2];
    private boolean[] isReady = new boolean[2];
    private volatile int[] remainingTime = new int[2];
    private int[] numberOfChangingMap = new int[2];
    private GameStatus gameStatus;

    public GameState(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
        boards[0] = BoardBuilder.getRandomBoard();
        boards[1] = BoardBuilder.getRandomBoard();

        remainingTime[0] = 30;
        startCounter(Side.PLAYER_ONE);

        remainingTime[1]=30;
        startCounter(Side.PLAYER_TWO);
        gameStatus = GameStatus.WAITING;
    }

    synchronized void shootOnCell(int i, int j, Side side) {
        if (side != sideToTurn)
            return;
        if(-1>=i || i>=10 || -1>=j || j>=10)
            return;
        Cell targetCell = boards[side.getOther().getIndex()].getCells()[i][j];
        if (targetCell.isDamaged())
            return;
        targetCell.getDamaged();
        Ship targetShip = targetCell.getShip();
        if (targetShip != null){
            remainingTime[side.getIndex()] = 25;
            targetShip.getDamaged();
            if (targetShip.getHealth()==0){
                destroyShip(targetShip);
                checkForEndGame();
            }
        }else remainingTime[side.getIndex()] = 0;

    }

    public Side getSideToTurn() {
        return sideToTurn;
    }

    public void setSideToTurn(Side sideToTurn) {
        this.sideToTurn = sideToTurn;
    }

    public boolean isEnd() {
        return result != -1;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult(){
        return result;
    }

    public void startCounter(Side side){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTime[side.getIndex()]>0)
                    remainingTime[side.getIndex()]--;
            }
        };
        timer.schedule(timerTask,0,1000);
    }
    public Board changeBoard(Side side){
        String message = boards[side.getIndex()].getMessage();
        boards[side.getIndex()] = BoardBuilder.getRandomBoard();
        boards[side.getIndex()].setMessage(message);
        return boards[side.getIndex()];
    }
    public Player[] getPlayers() {
        return players;
    }

    public Board[] getBoards() {
        return boards;
    }

    public boolean[] getIsReady() {
        return isReady;
    }

    public int[] getRemainingTime() {
        return remainingTime;
    }

    public int[] getNumberOfChangingMap() {
        return numberOfChangingMap;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


    public void destroyShip(Ship ship){
          ship.neighboringCellsPoint().forEach(point->{
              boards[sideToTurn.getOther().getIndex()].getCells()[point.x][point.y].getDamaged();
              System.out.println(point.toString());
          });
    }
    public synchronized void checkForEndGame(){
       boolean temp = true;
        for (Ship ship : boards[sideToTurn.getOther().getIndex()].getShips()) {
            if (ship.getHealth() != 0)
                temp = false;
        }
        if (temp) {
            Context context = new Context();
            switch (sideToTurn) {
                case PLAYER_ONE :
                    gameStatus = GameStatus.PLAYER_ONE_WON;
                    players[0].increaseScore();
                    players[1].decreaseScore();
                    context.Players.update(players[0]);
                    context.Players.update(players[1]);
                    break;
                case PLAYER_TWO :
                    gameStatus = GameStatus.PLAYER_TWO_WON;
                    players[1].increaseScore();
                    players[0].decreaseScore();
                    context.Players.update(players[1]);
                    context.Players.update(players[0]);
                    break;
            }
        }
    }
}