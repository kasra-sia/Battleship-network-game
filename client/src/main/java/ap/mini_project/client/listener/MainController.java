package ap.mini_project.client.listener;

import ap.mini_project.client.graphic.GraphicalAgent;
import ap.mini_project.client.graphic.PanelType;
import ap.mini_project.client.graphic.panels.pages.GamePanel;
import ap.mini_project.client.graphic.panels.pages.LoginPanel;
import ap.mini_project.client.graphic.panels.pages.RegistrationPanel;
import ap.mini_project.shared.model.Board;

//import ap.mini_project.shared.model.Player;
import ap.mini_project.shared.requests.Request;
import ap.mini_project.shared.response.*;
import ap.mini_project.shared.util.Loop;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor {
    private final RequestSender requestSender;
    private final List<Request> requests;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;
//    private static Player owner;

    public MainController(RequestSender requestSender) {
        this.requestSender = requestSender;
        this.requests = new LinkedList<>();
        this.loop = new Loop(10, this::sendRequests);
        this.graphicalAgent = new GraphicalAgent(this::addRequest);
    }

    public void start() {
        loop.start();
        graphicalAgent.initialize();
        graphicalAgent.goToOpeningPanel();
    }


    private void addRequest(Request request) {
        synchronized (requests) {
            requests.add(request);
        }
    }

    private void sendRequests() {
        List<Request> temp;
        synchronized (requests) {
            temp = new LinkedList<>(requests);
            requests.clear();
        }
        for (Request event : temp) {
            Response response = requestSender.send(event);
            response.visit(this);
        }
    }

    @Override
    public void visitBoard(GameForm gameForm) {
        graphicalAgent.gotoGamePanel(gameForm);
    }

    @Override
    public void visitLiveGames(LinkedList<LiveGameForm> linkedList) {
              graphicalAgent.gotoLivePagePanel(linkedList);
    }

    @Override
    public void visitLiveStream(LiveGameForm liveGameForm) {
             graphicalAgent.gotoGameStreamPanel(liveGameForm);
    }

    @Override
    public void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
        graphicalAgent.gotoMainMenu();
        synchronized (requests) {
            requests.clear();
        }
    }

    @Override
    public void visitLoginResult(boolean success, String message) {
        if (success) {
            graphicalAgent.myUsername = ((LoginPanel) graphicalAgent.getPanels().get(PanelType.LOGIN_PAGE)).getUsername();
            graphicalAgent.gotoMainMenu();
        } else {
            ((LoginPanel) graphicalAgent.getPanels().get(PanelType.LOGIN_PAGE)).setWarnings();
        }
        }

    @Override
    public void visitRegistrationResult(boolean success, String message) {
        if (success) {
            graphicalAgent.myUsername = ((LoginPanel) graphicalAgent.getPanels().get(PanelType.LOGIN_PAGE)).getUsername();
            graphicalAgent.gotoMainMenu();
        }else {
            System.out.println(message);
            ((RegistrationPanel) graphicalAgent.getPanels().get(PanelType.REGISTRATION_PAGE)).setWarnings(message);

        }
    }

    @Override
    public void visitPersonalInfo(PersonalInfoForm personalInfoForm) {
        graphicalAgent.gotoInfoPage(personalInfoForm);
    }

    @Override
    public void visitScoreBoard(LinkedList<PlayerScoreForm> linkedList) {
        graphicalAgent.gotoScorePage( linkedList);
    }

    @Override
    public void visitStringMessage(String s) {
//        if (s.equals("start"))
//        ((GamePanel) graphicalAgent.getPanels().get(PanelType.GAME_PANEL)).setStartingSetup();
//        if (s.equals("waiting"))
//            ((GamePanel) graphicalAgent.getPanels().get(PanelType.GAME_PANEL)).setWaitingSetup();
    }


}
