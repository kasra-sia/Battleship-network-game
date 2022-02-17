package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.ComponentsScrollPanel;
import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.graphic.panels.LiveGameView;
import ap.mini_project.client.graphic.panels.PlayerScorePanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.LinkedList;

public class ScorePage extends AbstractPanel {

    private ComponentsScrollPanel<PlayerScorePanel> componentsScrollPanel;
    private JButton backBtn;
    private LinkedList<StringEventListener> stringEventListeners = new LinkedList<>();


    public ScorePage() {
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>(new Dimension(Constant.WIDTH -200, Constant.HEIGHT - 70));
        this.add(componentsScrollPanel, BorderLayout.CENTER);
        backBtn = new JButton("back");
        backBtn.addActionListener(this::backAction);
        this.add(backBtn, BorderLayout.NORTH);
    }

    public void backAction(ActionEvent actionEvent) {
        stringEventListeners.forEach(listener -> listener.listen("back"));
    }

    public void addStringEventListener(StringEventListener stringEventListener) {
        stringEventListeners.add(stringEventListener);
    }
    public void setList(LinkedList<PlayerScorePanel> playerScorePanels){
        Collections.reverse(playerScorePanels);
        componentsScrollPanel.setComponentsList(playerScorePanels);
        repaint();
        revalidate();
    }
}
