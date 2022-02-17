package ap.mini_project.shared.requests;

import ap.mini_project.shared.response.Response;

public class GetLiveStreamRequest extends Request{
    int id;

    public GetLiveStreamRequest(int id) {
        this.id = id;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getLiveStream(id);
    }
}
