package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class ClickOnBoardRequest extends Request {
    private final int x, y;

    public ClickOnBoardRequest(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.clickOnBoard(x, y);
    }
}
