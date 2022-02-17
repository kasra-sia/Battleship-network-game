package ap.mini_project.shared.response;

import java.util.LinkedList;

public class ScoreBoardResponse extends Response{
   private LinkedList<PlayerScoreForm> playerScoreForms;

    public ScoreBoardResponse(LinkedList<PlayerScoreForm> playerScoreForms) {
        this.playerScoreForms = playerScoreForms;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
       responseVisitor.visitScoreBoard(playerScoreForms);
    }
}
