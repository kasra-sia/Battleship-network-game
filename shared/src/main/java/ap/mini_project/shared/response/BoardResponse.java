package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;

public class BoardResponse extends Response {
    private GameForm gameForm;

    public BoardResponse(GameForm gameForm) {
        this.gameForm = gameForm;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitBoard(gameForm);
    }
}
