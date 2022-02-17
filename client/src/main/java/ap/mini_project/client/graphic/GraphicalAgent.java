package ap.mini_project.client.graphic;

import ap.mini_project.client.graphic.panels.*;
import ap.mini_project.client.graphic.panels.pages.*;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.LoginPageToOpeningPageListener;
import ap.mini_project.client.listener.RegisterPageToOpeningPageListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.requests.GetBoardRequest;
import ap.mini_project.shared.requests.GetLiveGamesRequest;
import ap.mini_project.shared.requests.GetLiveStreamRequest;
import ap.mini_project.shared.requests.GetScoreBoardRequest;
import ap.mini_project.shared.response.GameForm;
import ap.mini_project.shared.response.LiveGameForm;
import ap.mini_project.shared.response.PersonalInfoForm;
import ap.mini_project.shared.response.PlayerScoreForm;
import ap.mini_project.shared.util.Loop;

import javax.swing.*;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class GraphicalAgent {
    private final RequestListener requestListener;
    private final MyFrame frame;
    private final Map<PanelType, AbstractPanel> panels;
    private Loop loop;
    public  String myUsername;


    public GraphicalAgent(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.frame = new MyFrame();
        this.panels = new EnumMap<>(PanelType.class);

    }

    public void initialize() {
        frame.setVisible(true);
    }

    public void goToOpeningPanel() {
        OpeningPanel openingPanel = new OpeningPanel(requestListener);
        openingPanel.addListener(new LoginPageToOpeningPageListener(this));
        openingPanel.addListener(new RegisterPageToOpeningPageListener(this));
        frame.setContentPane(openingPanel);
        panels.put(PanelType.OPENING_PAGE, openingPanel);
        if (loop != null)
            loop.stop();
    }

    public void goToLoginPage() {
        LoginPanel loginPanel = new LoginPanel(requestListener);
        loginPanel.addStringEventListener(stringEvent -> {
            if (stringEvent.equals("back"))
                goToOpeningPanel();
        });
        frame.setContentPane(loginPanel);
        panels.put(PanelType.LOGIN_PAGE, loginPanel);
        if (loop != null)
            loop.stop();
    }

    public void goToRegistrationPage() {
        RegistrationPanel registrationPanel = new RegistrationPanel(requestListener);
        registrationPanel.addStringEventListener(stringEvent -> {
            if (stringEvent.equals("back"))
                goToOpeningPanel();
        });
        frame.setContentPane(registrationPanel);
        panels.put(PanelType.REGISTRATION_PAGE, registrationPanel);
        if (loop != null)
            loop.stop();
    }

    public void gotoMainMenu() {
        MainMenuPanel panel = new MainMenuPanel(requestListener);
        panel.addStringListener(stringEvent -> {
            if (stringEvent.equals("livePage"))
                requestListener.listen(new GetLiveGamesRequest());
            if (stringEvent.equals("scorePage"))
                requestListener.listen(new GetScoreBoardRequest());
        });
        frame.setContentPane(panel);
        panels.put(PanelType.MAIN_MENU, panel);
        if (loop != null)
            loop.stop();
    }

    public void gotoLivePagePanel(LinkedList<LiveGameForm> linkedList) {
        if (frame.getContentPane() != panels.get(PanelType.LivePage_Panel)) {

            LivePagePanel livePagePanel = new LivePagePanel(requestListener);
            livePagePanel.setPage(linkedList);
            livePagePanel.addStringEventListeners(new StringEventListener() {
                @Override
                public void listen(String stringEvent) {
                    if (stringEvent.equals("back")) {
                        loop.stop();
                        gotoMainMenu();
                    }
                }
            });
            frame.setContentPane(livePagePanel);
            panels.put(PanelType.LivePage_Panel, livePagePanel);
            loop = new Loop(1, this::updateLiveGames);
            loop.start();
        } else {
            LivePagePanel livePagePanel = ((LivePagePanel) panels.get(PanelType.LivePage_Panel));
            livePagePanel.setPage(linkedList);
        }
    }

    public void gotoScorePage(LinkedList<PlayerScoreForm> linkedList) {
        LinkedList<PlayerScorePanel> playerScorePanels = new LinkedList<>();
        linkedList.forEach(playerScoreForm ->
                playerScorePanels.add(new PlayerScorePanel(
                        playerScoreForm.getScore(),
                        playerScoreForm.getUsername(),
                        playerScoreForm.getPlayerStatus().toString())));
        if (frame.getContentPane() != panels.get(PanelType.SCORE_PAGE)) {
            ScorePage scorePage = new ScorePage();
            scorePage.addStringEventListener(stringEvent -> {
                if (stringEvent.equals("back"))
                    gotoMainMenu();
            });
            frame.setContentPane(scorePage);
            scorePage.setList(playerScorePanels);
            panels.put(PanelType.SCORE_PAGE, scorePage);
            loop = new Loop(3, this ::updateScoreBoard);

            loop.start();
        } else {

            ScorePage scorePage = ((ScorePage) panels.get(PanelType.SCORE_PAGE));
            scorePage.setList(playerScorePanels);
        }
    }

    public void gotoInfoPage(PersonalInfoForm personalInfoForm) {
        InfoPage infoPage = new InfoPage(personalInfoForm.getUsername(),
                String.valueOf(personalInfoForm.getLoses()),
                String.valueOf(personalInfoForm.getWinnings()),
                String.valueOf(personalInfoForm.getScore()));
        infoPage.setStringEventListener(stringEvent -> {
            if (stringEvent.equals("back"))
                gotoMainMenu();
        });
        frame.setContentPane(infoPage);
        panels.put(PanelType.INFO_PAGE, infoPage);


    }


    public void gotoGamePanel(GameForm gameForm) {
        if (frame.getContentPane() != panels.get(PanelType.GAME_PANEL)) {
            GamePanel gamePanel = new GamePanel(requestListener, gameForm.getBoards()[1], gameForm.getBoards()[0]);
            frame.setContentPane(gamePanel);
            panels.put(PanelType.GAME_PANEL, gamePanel);
            loop = new Loop(2, this::updateBoard);
            loop.start();
        } else {
            GamePanel gamePanel = (GamePanel) panels.get(PanelType.GAME_PANEL);
            gamePanel.setGamePanel(gameForm.getBoards()[0],gameForm.getBoards()[1], gameForm.getRemainingTime(),gameForm.getEnemyUsername(),gameForm.getGameStatus(),myUsername);
        }
    }
    public void gotoGameStreamPanel(LiveGameForm liveGameForm){

        if (frame.getContentPane() != panels.get(PanelType.GAME_STREAM_PANEL)) {
            GameStreamPanel gameStreamPanel = new GameStreamPanel(liveGameForm);
            gameStreamPanel.setStringEventListener(new StringEventListener() {
                @Override
                public void listen(String stringEvent) {
                    if (stringEvent.equals("back"))
                        gotoMainMenu();
                }
            });
            frame.setContentPane(gameStreamPanel);
            panels.put(PanelType.GAME_STREAM_PANEL, gameStreamPanel);
            loop.stop();
            loop = new Loop(2, ()->updateLiveStream(liveGameForm.getGameID()));
            loop.start();
        } else {
            GameStreamPanel gameStreamPanel = (GameStreamPanel) panels.get(PanelType.GAME_STREAM_PANEL);
            gameStreamPanel.setGamePanel(liveGameForm);
            if (liveGameForm.getGameStatus() == GameStatus.PLAYER_ONE_WON) {
                JOptionPane.showMessageDialog(null, "player one won");
                gotoMainMenu();
            }
            if (liveGameForm.getGameStatus() == GameStatus.PLAYER_TWO_WON) {
                JOptionPane.showMessageDialog(null, "player two won");
                gotoMainMenu();
            }
        }
    }

    private void updateBoard() {
        requestListener.listen(new GetBoardRequest());
    }

    private void updateLiveGames() {
        requestListener.listen(new GetLiveGamesRequest());
    }

    private void updateScoreBoard() {
        requestListener.listen(new GetScoreBoardRequest());
    }

    private void updateLiveStream(int id){
        requestListener.listen(new GetLiveStreamRequest(id));
    }

    public Map<PanelType, AbstractPanel> getPanels() {
        return panels;
    }
}
