package ap.mini_project.server.controller.network;


import ap.mini_project.shared.requests.Request;
import ap.mini_project.shared.response.Response;

public interface ResponseSender {
    Request getRequest();

    void sendResponse(Response response);

    void close();
}
