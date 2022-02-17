package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.requests.StartGameRequest;

import javax.swing.*;
import java.util.LinkedList;

public class OpeningPanel extends AbstractPanel {
    private JButton exit;
    private JButton login;
    private JButton register;
    private final RequestListener requestListener;
    private LinkedList<StringEventListener> listeners = new LinkedList<>();
    public OpeningPanel(RequestListener requestListener) {
    this.requestListener = requestListener;
    exit = new JButton("exit");
        exit.setBounds(Constant.BUTTON_X, Constant.EXIT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        exit.addActionListener(e -> this.exitAction());
    add(exit);
    login = new JButton("login");
        login.setBounds(Constant.BUTTON_X, Constant.LOGIN_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        login.addActionListener(e -> this.openLoginPageAction());
    add(login);
    register = new JButton("register");
        register.setBounds(Constant.BUTTON_X, Constant.REGISTER_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        register.addActionListener(e -> this.openRegistrationPageAction());
    add(register);
}


    private void startAction() {
        requestListener.listen(new StartGameRequest());
    }

    private void exitAction() {
        System.exit(0);
    }

    private void openLoginPageAction(){
        for (StringEventListener listener:listeners) {
            listener.listen("openLoginPage");
        }

    }
    private void openRegistrationPageAction(){
        for (StringEventListener listener:listeners) {
            listener.listen("openRegistrationPage");
        }
    }

    public void addListener(StringEventListener listener) {
        this.listeners.add(listener);
    }
}
