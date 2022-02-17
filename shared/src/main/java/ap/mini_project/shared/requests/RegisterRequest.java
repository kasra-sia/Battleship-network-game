package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class RegisterRequest extends LoginRequest {

    public RegisterRequest(LoginForm loginForm) {
        super(loginForm);
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.register(loginForm);
    }
}
