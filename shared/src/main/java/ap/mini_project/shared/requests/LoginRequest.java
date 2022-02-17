package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class LoginRequest extends Request {

    protected LoginForm loginForm;

    public LoginRequest(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.login(loginForm);
    }
}
