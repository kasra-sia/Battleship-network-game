package ap.mini_project.shared.response;

public class LoginResponse extends Response{
    protected boolean success;
    protected String message;

    public LoginResponse(boolean success,String message) {
        this.success = success;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess(){
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
       responseVisitor.visitLoginResult(success,message);
    }
}
