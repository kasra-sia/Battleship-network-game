package ap.mini_project.client.graphic.panels;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.MyFrame;
import ap.mini_project.client.graphic.panels.pages.LivePagePanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.requests.Request;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class LiveGameView extends JPanel {
    private JButton joinBtn;
    private JLabel player1UsernameLabel;
    private JLabel player2UsernameLabel;
    private JLabel p1DestroyedShipNumLabel;
    private JLabel p2DestroyedShipNumLabel;
    private JLabel p1DestroyedCellsNumLabel;
    private JLabel p2DestroyedCellsNumLabel;
    private LinkedList<StringEventListener> stringEventListeners ;

    public LiveGameView() {
        stringEventListeners = new LinkedList<>();
        this.setPreferredSize(new Dimension(Constant.WIDTH-200,Constant.HEIGHT/4));
        this.setBackground(Color.pink);
        this.setLayout(new GridLayout(3,2));


        player1UsernameLabel = new JLabel("player1 :");
        player2UsernameLabel = new JLabel("player2 :");

        p1DestroyedShipNumLabel = new JLabel("destroyed ships : ");
        p2DestroyedShipNumLabel = new JLabel("destroyed ships : ");

        p1DestroyedCellsNumLabel = new JLabel("destroyed cells : ");
        p2DestroyedCellsNumLabel = new JLabel("destroyed cells : ");

        this.add(player1UsernameLabel);
        this.add(p1DestroyedShipNumLabel);
        this.add(p1DestroyedCellsNumLabel);

        this.add(player2UsernameLabel);
        this.add(p2DestroyedShipNumLabel);
        this.add(p2DestroyedCellsNumLabel);
        joinBtn = new JButton("join");
        joinBtn.addActionListener(this::JoinGameAction);
        this.add(joinBtn);

    }
    public void JoinGameAction(ActionEvent event){
       stringEventListeners.forEach(stringEventListener -> stringEventListener.listen("join"));
    }
    public void addStringListener(StringEventListener stringEventListener){
        stringEventListeners.add(stringEventListener);
    }
    public void setPanel(String username1, String username2,String p1Ships, String p2Ships,String p1Cells,String p2Cells ){
        player1UsernameLabel.setText("player1 : @"+username1);
        player2UsernameLabel.setText("player2 : @"+username2);

        p1DestroyedShipNumLabel.setText("destroyed ships : "+p1Ships);
        p2DestroyedShipNumLabel.setText("destroyed ships : "+p2Ships);

        p1DestroyedCellsNumLabel.setText("destroyed cells : "+p1Cells);
        p2DestroyedCellsNumLabel.setText("destroyed cells : "+p2Cells);
    }
    public void setJoinBtnDisable(){}

//    public static void main(String[] args) {
//        Frame frame =  new MyFrame();
//        frame.setVisible(true);
//        frame.setSize(Constant.WIDTH,Constant.HEIGHT);
////        frame.add(new LivePagePanel(request -> {
////            return;
////        }));
//    }
}
