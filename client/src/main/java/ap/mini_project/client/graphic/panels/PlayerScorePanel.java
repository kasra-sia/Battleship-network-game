package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.listener.StringEventListener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PlayerScorePanel extends JPanel {
    private LinkedList<StringEventListener> stringEventListeners = new LinkedList<>();
    private JLabel ScoreLabel = new JLabel();
    private JLabel playerNameLabel =new JLabel();
    private JLabel playerStatusLabel =new JLabel();
    public PlayerScorePanel(String score,String name,String playerStatus) {
        this.ScoreLabel.setText("score :"+score);
        this.playerNameLabel.setText("     \t\t name :"+name);
        this.playerStatusLabel.setText(playerStatus);
        stringEventListeners = new LinkedList<>();
        this.setPreferredSize(new Dimension(Constant.WIDTH*2/3,Constant.HEIGHT/4));
        this.setBackground(Color.ORANGE);

        this.add(playerNameLabel);
        this.add(playerStatusLabel);
        this.add(ScoreLabel);
//        this.add(playerNameLabel);

        this.setLayout(new GridLayout());
    }

}
