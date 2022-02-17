package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.ComponentsScrollPanel;
import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.graphic.panels.LiveGameView;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.requests.GetLiveStreamRequest;
import ap.mini_project.shared.response.LiveGameForm;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class LivePagePanel extends AbstractPanel {
    private ComponentsScrollPanel<LiveGameView> componentsScrollPanel;
    private JButton backBtn;
    private LinkedList<StringEventListener> stringEventListeners = new LinkedList<>();
    private RequestListener requestListener;
    public LivePagePanel(RequestListener requestListener) {
        this.requestListener = requestListener;
        this.setLayout(new BorderLayout());
        componentsScrollPanel = new ComponentsScrollPanel<>(new Dimension(Constant.WIDTH-20, Constant.HEIGHT-70 ));
        this.add(componentsScrollPanel, BorderLayout.CENTER);
//        LinkedList<LiveGameView> temp = new LinkedList<>();
//        temp.add(new LiveGameView());
//        temp.add(new LiveGameView());
//        temp.add(new LiveGameView());
//        temp.add(new LiveGameView());
//        temp.add(new LiveGameView());
//        temp.add(new LiveGameView());

        backBtn = new JButton("back");
        backBtn.addActionListener(e -> backAction());
        this.add(backBtn, BorderLayout.NORTH);
    }

    public void backAction() {
        for (StringEventListener stringEventListener : stringEventListeners) {
            stringEventListener.listen("back");
        }
    }
    public void setPage(LinkedList<LiveGameForm> liveGameForms){
        LinkedList<LiveGameView> temp = new LinkedList<>();
        for (LiveGameForm liveGameForm:liveGameForms) {
            LiveGameView temp1 = new LiveGameView();
            temp1.setPanel(
                    liveGameForm.getPlayers()[0],
                    liveGameForm.getPlayers()[1],
                    liveGameForm.getDamagedShips()[0],
                    liveGameForm.getDamagedShips()[1],
                    liveGameForm.getDamagedCells()[0],
                    liveGameForm.getDamagedCells()[1]);
            temp.add(temp1);
            temp1.addStringListener(new StringEventListener() {
                @Override
                public void listen(String stringEvent) {
                    if (stringEvent.equals("join"))
                        if (liveGameForm.getGameStatus()!= GameStatus.WAITING)
                       requestListener.listen(new GetLiveStreamRequest(liveGameForm.getGameID()));
                        else JOptionPane.showMessageDialog(null,"game has not started yet ");
                }
            });
        }
        componentsScrollPanel.setComponentsList(temp);
    }

    public void addStringEventListeners(StringEventListener stringEventListener) {
        this.stringEventListeners.add(stringEventListener);
    }
}
