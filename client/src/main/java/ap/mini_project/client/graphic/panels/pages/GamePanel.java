package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.graphic.panels.BoardPanel;
import ap.mini_project.client.listener.RequestListener;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Cell;
import ap.mini_project.shared.model.GameStatus;
import ap.mini_project.shared.model.Ship;
import ap.mini_project.shared.requests.ClickOnBoardRequest;
import ap.mini_project.shared.requests.ClickOnBtnRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class GamePanel extends AbstractPanel {
    private final RequestListener listener;
    private final BoardPanel boardPanel1;
    private final BoardPanel boardPanel2;
    private final int cellH;
    private final int cellW;
    private JButton changeBoardBtn = new JButton("changeBoard");
    private JCheckBox readyBtn = new JCheckBox("ready");
    private JLabel timerLabel = new JLabel();
    private JLabel enemyUsernameLabel = new JLabel();
    private JLabel myUsername = new JLabel();
    private final JLabel label;
    private boolean flag1 = true;

    public GamePanel(RequestListener listener, Board board2, Board board1) {
        this.listener = listener;
        this.cellH = (Constant.HEIGHT - 200) / board1.getH();
        this.cellW = (Constant.WIDTH/2 - 200) / board1.getW();
        this.boardPanel1 = new BoardPanel(board1, cellW, cellH);
        this.add(boardPanel1);


        label = new JLabel(board1.getMessage());
        label.setBounds(300, 40, 100, 100);
        this.add(label);

        boardPanel2 = new BoardPanel(board2,((Constant.WIDTH/2)- 200) / board1.getW()
                ,(Constant.HEIGHT - 200) / board1.getH());
        boardPanel2.setLocation(700,100);
        boardPanel2.setMyBoard(true);
        this.add(boardPanel2);

         changeBoardBtn.setBounds(900,40,200,40);
         changeBoardBtn.addActionListener(e->this.changeBoardBtnAction());
         changeBoardBtn.setVisible(false);
        this.add(changeBoardBtn);

        readyBtn.setBounds(1100,40,100,40);
        readyBtn.setVisible(false);
        readyBtn.addActionListener(e->{
            if (readyBtn.isSelected())
            this.readyBtnAction();
            else this.unreadyBtnAction();
        });
        this.add(readyBtn);

        timerLabel.setBounds(630,20,60,60);
        timerLabel.setForeground(Color.red);
        timerLabel.setFont(new Font(null,Font.BOLD,40));
        this.add(timerLabel);


        enemyUsernameLabel.setBounds(0,0,100,40);
        this.add(enemyUsernameLabel);


        myUsername.setBounds(0,50,200,40);
        this.add(myUsername);
    }

    public void setGamePanel(Board board1,Board board2,String time,String enemyUsername,String gameStatus,String myUsername) {

        boardPanel1.setBoard(board2);
        label.setText(board1.getMessage());
        timerLabel.setText(time);
        this.myUsername.setText("my username : @"+myUsername);
        if (gameStatus.equals("WAITING")){
            changeBoardBtn.setVisible(true);
            readyBtn.setVisible(true);
        }
        if (gameStatus.equals("RUNNING") && flag1){
            flag1 = false;
            changeBoardBtn.setVisible(false);
            readyBtn.setVisible(false);
            boardPanel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    click(e.getX() / cellW, e.getY() / cellH);
                }
            });
            readyBtn.setVisible(false);
            changeBoardBtn.setVisible(false);
        }
        boardPanel2.setMyBoard(true);
        boardPanel2.setBoard(board1);
        boardPanel2.repaint();
        enemyUsernameLabel.setText("enemy : @"+enemyUsername);

    }

    private void click(int x, int y) {
        listener.listen(new ClickOnBoardRequest(x, y));
    }

    public void readyBtnAction(){
        listener.listen(new ClickOnBtnRequest("ready"));
    }
    public void unreadyBtnAction(){
        listener.listen(new ClickOnBtnRequest("unready"));
    }
    public void changeBoardBtnAction(){
        listener.listen(new ClickOnBtnRequest("changeBoard"));
    }
}
