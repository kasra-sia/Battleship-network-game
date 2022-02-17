package ap.mini_project.client.listener;


import ap.mini_project.shared.requests.Request;
import ap.mini_project.shared.response.Response;

public interface RequestSender {
    Response send(Request event);

    void close();
}
