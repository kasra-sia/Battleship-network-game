package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.requests.LoginForm;
import ap.mini_project.shared.requests.RegisterRequest;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;

public class RegistrationPanel extends AbstractPanel {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField password1Field = new JPasswordField();
    private JPasswordField password2Field = new JPasswordField();
    private JButton registerBtn = new JButton("register");
    private JButton backBtn = new JButton("back");
    private JLabel passwordConfirmationWarning;
    private JLabel password1RequiredWarning;
    private JLabel password2RequiredWarning;
    private JLabel duplicateUsernameWarning;
    private JLabel userNameRequiredWarning;
    private RequestListener requestListener;
    private LinkedList<StringEventListener> stringEventListeners = new LinkedList<>();
    public RegistrationPanel(RequestListener requestListener) {
        this.requestListener = requestListener;
        Border innerBorder = BorderFactory.createTitledBorder("register");
        Border outerBoarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setPreferredSize(new Dimension(600, 400));
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));
        registerBtn.setBackground(Color.white);
//        registerBtn.addActionListener(this);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.1;
        gc.weighty = 0.1;
        //////////////// 0
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 200);
        gc.anchor = GridBagConstraints.LINE_END;
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
        gc.gridy = 2;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        userNameRequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        userNameRequiredWarning.setVisible(false);
        this.add(userNameRequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        duplicateUsernameWarning = new JLabel("<html><font color=red>This username has been already taken</font>");
        duplicateUsernameWarning.setVisible(false);
        this.add(duplicateUsernameWarning, gc);

        /////////////// 2
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password1: "), gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        password1Field.setPreferredSize(new Dimension(140,20));
        this.add(password1Field, gc);

        gc.gridx = 2;
        gc.gridy = 2;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        password1RequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        password1RequiredWarning.setVisible(false);
        this.add(password1RequiredWarning, gc);

        /////////////// 3
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 7);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password2: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        password2Field.setPreferredSize(new Dimension(140,20));
        this.add(password2Field, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, -50, 0, 60);
        gc.anchor = GridBagConstraints.LINE_START;
        password2RequiredWarning = new JLabel("<html><font color=red>This filed is required </font>");
        password2RequiredWarning.setVisible(false);
        this.add(password2RequiredWarning, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 50);
        gc.anchor = GridBagConstraints.LINE_START;
        passwordConfirmationWarning = new JLabel("<html><font color=red>Wrong password confirmation</font>");
        passwordConfirmationWarning.setVisible(false);
        this.add(passwordConfirmationWarning, gc);

        /////////////// 3
        gc.weightx = 1;
        gc.weighty = 2;

        gc.gridx = 1;
        gc.gridy = 4;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        registerBtn.addActionListener(e -> registerAction());
        this.add(registerBtn, gc);
        ///////////////4
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
        return password1Field.getText();
    }

    public void registerAction() {
        requestListener.listen(new RegisterRequest(new LoginForm(usernameField.getText(),password1Field.getText())));
        resetWarnings();
    }
    private void resetWarnings(){
        duplicateUsernameWarning.setVisible(false);
        userNameRequiredWarning.setVisible(false);
        password2RequiredWarning.setVisible(false);
        password1RequiredWarning.setVisible(false);
        passwordConfirmationWarning.setVisible(false);

    }

    public void addStringEventListener(StringEventListener stringEventListener) {
        this.stringEventListeners.add(stringEventListener);
    }
    public void backAction(){
        for (StringEventListener  listener:stringEventListeners) {
            listener.listen("back");
        }
    }
    public void setWarnings(String message){
        if (message.equals("duplicateUsername")){
            duplicateUsernameWarning.setVisible(true);
        }
    }
    public String getUsername(){
        return usernameField.getText();
    }
}
