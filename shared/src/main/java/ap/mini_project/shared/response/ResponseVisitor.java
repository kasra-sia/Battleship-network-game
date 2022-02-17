package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;

import java.util.LinkedList;

public interface ResponseVisitor {
    void visitBoard(GameForm gameForm);

    void showMessage(String message);

    void visitLoginResult(boolean success,String message);

    void visitRegistrationResult(boolean success,String message);

    void visitPersonalInfo(PersonalInfoForm personalInfoForm);

    void visitScoreBoard(LinkedList<PlayerScoreForm> playerScoreForms);

    void visitStringMessage(String message);

    void visitLiveGames(LinkedList<LiveGameForm> liveGameForms);

    void visitLiveStream(LiveGameForm liveGameForm);

}
