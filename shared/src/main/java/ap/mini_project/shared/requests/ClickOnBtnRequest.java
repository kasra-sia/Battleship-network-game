package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class ClickOnBtnRequest extends Request {
    String btnName;
    public ClickOnBtnRequest(String btnName) {
        this.btnName = btnName;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.clickOnBtn(btnName);
    }
}
