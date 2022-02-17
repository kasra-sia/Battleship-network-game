package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public abstract class Request {
    protected String authToken = null;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public abstract Response visit(RequestVisitor requestVisitor);
}
