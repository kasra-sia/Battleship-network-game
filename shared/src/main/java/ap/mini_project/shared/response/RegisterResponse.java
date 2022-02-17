package ap.mini_project.shared.response;

public class RegisterResponse extends LoginResponse {
    public RegisterResponse(boolean success,String message) {
        super(success,message);
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitRegistrationResult(success,message);
    }
}
