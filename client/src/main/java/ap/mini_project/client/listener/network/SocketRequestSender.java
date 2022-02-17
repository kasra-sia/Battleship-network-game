package ap.mini_project.client.listener.network;

import ap.mini_project.client.listener.RequestSender;
import ap.mini_project.shared.gson.Deserializer;
import ap.mini_project.shared.gson.Serializer;
import ap.mini_project.shared.requests.LoginRequest;
import ap.mini_project.shared.requests.Request;
import ap.mini_project.shared.response.LoginResponse;
import ap.mini_project.shared.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketRequestSender implements RequestSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private  String token = null;
    public SocketRequestSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .create();
    }

    @Override
    public Response send(Request request) {
        if (!(request instanceof LoginRequest))
            request.setAuthToken(token);
        String eventString = gson.toJson(request, Request.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        Response response =gson.fromJson(responseString, Response.class);
        if (response instanceof LoginResponse && ((LoginResponse) response).isSuccess())
            token = ((LoginResponse) response).getMessage();
        return response;
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
