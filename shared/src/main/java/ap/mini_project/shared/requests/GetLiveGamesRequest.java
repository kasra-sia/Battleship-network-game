package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class GetLiveGamesRequest extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getLiveGames();
    }
}
