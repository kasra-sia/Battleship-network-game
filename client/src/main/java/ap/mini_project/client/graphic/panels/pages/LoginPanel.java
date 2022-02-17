package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.requests.LoginForm;
import ap.mini_project.shared.requests.LoginRequest;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;

public class LoginPanel extends AbstractPanel {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginBtn = new JButton("login");
    private JButton backBtn = new JButton("back");
    private JLabel wrongUsernameOrPasswordWarning;
    private LinkedList<StringEventListener> stringEventListeners = new LinkedList<>();
    private RequestListener requestListener;
    public LoginPanel(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder("login");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setPreferredSize(new Dimension(600, 400));
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        loginBtn.setBackground(Color.CYAN);
        loginBtn.addActionListener(e ->loginAction());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
        //////////////// 0
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 200);
        gc.anchor = GridBagConstraints.LINE_END;
        wrongUsernameOrPasswordWarning = new JLabel("<html><font color=red>Wrong username or password </font>");
        wrongUsernameOrPasswordWarning.setVisible(false);
        this.add(wrongUsernameOrPasswordWarning,gc);
        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        passwordField.setPreferredSize(new Dimension(140,20));
        this.add(passwordField, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_START;

        /////////////// 3
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(loginBtn, gc);
        //////////////// 4
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 2;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        backBtn.addActionListener(e ->backAction());
        this.add(backBtn, gc);
    }
    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    private void resetWarnings(){
        wrongUsernameOrPasswordWarning.setVisible(false);
    }
    public void addStringEventListener(StringEventListener stringEventListener) {
        this.stringEventListeners.add(stringEventListener);
    }

    public void backAction() {
        for (StringEventListener listener : stringEventListeners) {
            listener.listen("back");
        }
    }
    public void loginAction(){
       requestListener.listen(new LoginRequest(new LoginForm(usernameField.getText(),passwordField.getText())));
    }
    public void setWarnings(){
        wrongUsernameOrPasswordWarning.setVisible(true);
    }

    public String getUsername(){
        return usernameField.getText();
    }

}
