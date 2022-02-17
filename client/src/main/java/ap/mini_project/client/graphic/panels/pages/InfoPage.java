package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.listener.StringEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class InfoPage extends AbstractPanel {
    private JLabel scoreLabel = new JLabel();
    private JLabel winningsLabel = new JLabel();
    private JLabel losesLabel = new JLabel();
    private JLabel usernameLabel = new JLabel();
    private JButton backBtn = new JButton("back");
    private StringEventListener stringEventListener;
    public InfoPage(String username, String loses, String winnings, String score) {

        usernameLabel.setText("username : " + username);
        losesLabel.setText("loses : " + loses);
        winningsLabel.setText("winnings : " + winnings);
        scoreLabel.setText("score : " + score);
        this.setLayout(new GridLayout(6,1));
        this.add(backBtn);
        backBtn.setBackground(Color.WHITE);
        backBtn.addActionListener(this::backAction);
        this.add(usernameLabel);
        this.add(losesLabel);
        this.add(winningsLabel);
        this.add(scoreLabel);
    }

    public void setStringEventListener(StringEventListener stringEventListener) {
        this.stringEventListener = stringEventListener;
    }
    private void backAction(ActionEvent actionEvent){
        stringEventListener.listen("back");
}


}
