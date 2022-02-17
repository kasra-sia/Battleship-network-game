package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.requests.GetPersonalInfoRequest;
import ap.mini_project.shared.requests.StartGameRequest;

import javax.swing.*;
import java.util.LinkedList;

public class MainMenuPanel extends AbstractPanel {
    private JButton start;
    private JButton exit;
    private JButton liveGames;
    private JButton scorePage;
    private JButton infoPage;

    private final RequestListener requestListener;
    private LinkedList<StringEventListener> listeners = new LinkedList<>();

    public MainMenuPanel(RequestListener requestListener) {

        this.requestListener = requestListener;
        start = new JButton("new game");
        start.setBounds(Constant.BUTTON_X, Constant.START_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        start.addActionListener(e -> this.startAction());
        add(start);
        exit = new JButton("exit");
        exit.setBounds(Constant.BUTTON_X, Constant.EXIT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        exit.addActionListener(e -> this.exitAction());
        add(exit);
        liveGames = new JButton("liveGames");
        liveGames.setBounds(Constant.BUTTON_X, Constant.LIVE_GAME_PAGE_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        liveGames.addActionListener(e -> this.liveGameBtnAction());
        add(liveGames);
        scorePage = new JButton("ScorePage");
        scorePage.setBounds(Constant.BUTTON_X, Constant.SCORE_PAGE_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        scorePage.addActionListener(e -> this.scorePageAction());
        add(scorePage);
        infoPage = new JButton("infoPage");
        infoPage.setBounds(Constant.BUTTON_X, Constant.INFO_PAGE_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
        infoPage.addActionListener(e -> this.infoPageAction());
        add(infoPage);
    }


    private void startAction() {
        requestListener.listen(new StartGameRequest());
    }
    private void exitAction() {
        System.exit(0);
    }
    private void liveGameBtnAction(){listeners.forEach(listener -> listener.listen("livePage"));}
    private void scorePageAction(){listeners.forEach(listener->listener.listen("scorePage"));}
    private void infoPageAction(){requestListener.listen(new GetPersonalInfoRequest());}

    public void addStringListener(StringEventListener listener) {
        this.listeners.add(listener);
    }
}
