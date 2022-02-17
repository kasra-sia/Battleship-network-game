package ap.mini_project.server.controller.network;

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
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class SocketResponseSender implements ResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private String token = null;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());

        this.gson = new GsonBuilder()
                .registerTypeAdapter(Request.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();
    }

    @Override
    public Request getRequest() {
//        System.out.println(1);
        String eventString = scanner.nextLine();
        Request event = gson.fromJson(eventString, Request.class);
//        System.out.println(event);
        if (!(event instanceof LoginRequest) && !event.getAuthToken().equals(token))
                throw new Error("security alert : wrong AuthToken");
//        System.out.println(event.getAuthToken());
        return event;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public void sendResponse(Response response) {
        if ((response instanceof LoginResponse && ((LoginResponse) response).isSuccess())) {
                token = generateNewToken();
                ((LoginResponse) response).setMessage(token);
        }
        printStream.println(gson.toJson(response, Response.class));
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
