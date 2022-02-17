package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.LiveGameForm;
import ap.mini_project.shared.response.Response;

public interface RequestVisitor {
    Response startGame(StartGameRequest startGameRequest);

    Response clickOnBoard(int x, int y);

    Response getBoard();

    Response getLiveGames();

    Response login(LoginForm loginForm);

    Response register(LoginForm loginForm);

    Response getPersonalInfo();

    Response getScoreBoard();

    Response clickOnBtn(String btnName);

    Response visitStringRequest(String message);

    Response getLiveStream(int id);
    // ...
}
