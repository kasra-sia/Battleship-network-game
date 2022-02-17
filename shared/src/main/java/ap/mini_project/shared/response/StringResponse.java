package ap.mini_project.shared.response;

public class StringResponse extends Response{
    private String message;

    public StringResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitStringMessage(message);
    }
}
