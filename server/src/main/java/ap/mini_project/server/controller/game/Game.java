package ap.mini_project.server.controller.game;

import ap.mini_project.server.controller.game.SeaBattle.GameState;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.model.Side;

public interface Game {
    void click(int x, int y, Side side);

    Board getBoard(Side side);

    int getResult();

    GameState getGameState();

    void setReady(Side side, boolean isReady);

    int getRemainingTime(Side side);

    void setRemainingTime(int time,Side side);

    void changeBoard(Side side);

    GameStatus getGameStatus();

    String getUsername(Side side);

    void nextTurn(Side side);

    void startGame();
}
