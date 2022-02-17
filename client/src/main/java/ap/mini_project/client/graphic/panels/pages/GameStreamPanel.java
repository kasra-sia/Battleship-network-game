package ap.mini_project.client.graphic.panels.pages;

import ap.mini_project.client.graphic.Constant;
import ap.mini_project.client.graphic.panels.AbstractPanel;
import ap.mini_project.client.graphic.panels.BoardPanel;
import ap.mini_project.client.listener.StringEventListener;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.response.LiveGameForm;

import javax.swing.*;
import java.awt.*;

public class GameStreamPanel extends AbstractPanel {
    private final BoardPanel boardPanel1;
    private final BoardPanel boardPanel2;
    private final int cellH;
    private final int cellW;
    private JLabel turnLabel = new JLabel();
    private JLabel player1Label;
    private JLabel player2Label;
//    private final JLabel label;
    private JButton backBtn = new JButton("back");
    private StringEventListener stringEventListener;

    public GameStreamPanel(LiveGameForm liveGameForm) {
        Board board1 = liveGameForm.getBoards()[0];
        Board board2 = liveGameForm.getBoards()[1];
        this.cellH = (Constant.HEIGHT - 200) / board1.getH();
        this.cellW = (Constant.WIDTH/2 - 200) / board1.getW();
        this.boardPanel1 = new BoardPanel(board1, cellW, cellH);
        this.add(boardPanel1);

        player1Label = new JLabel("player one : @"+liveGameForm.getPlayers()[0]);
        player1Label.setBounds(300, 40, 300, 100);
        this.add(player1Label);

        boardPanel2 = new BoardPanel(board2,((Constant.WIDTH/2)- 200) / board1.getW()
                ,(Constant.HEIGHT - 200) / board1.getH());
        boardPanel2.setLocation(700,100);
        this.add(boardPanel2);

        player2Label = new JLabel("player two : @"+liveGameForm.getPlayers()[1]);
        player2Label.setBounds(900, 40, 300, 100);
        this.add(player2Label);

        backBtn.setBounds(1100,20,100,40);
        backBtn.addActionListener(e->backBtnAction());
        this.add(backBtn);

        turnLabel.setBounds(630,20,150,60);
        turnLabel.setForeground(Color.red);
        turnLabel.setText(liveGameForm.getSideToTurn().toString());
        this.add(turnLabel);
    }

    public void setGamePanel(LiveGameForm liveGameForm) {

        boardPanel1.setBoard(liveGameForm.getBoards()[0]);
        turnLabel.setText(liveGameForm.getSideToTurn().toString());
        this.player1Label.setText("player one : @"+liveGameForm.getPlayers()[0]);

        boardPanel2.setBoard(liveGameForm.getBoards()[1]);
        boardPanel2.repaint();
        player2Label.setText("player two : @"+liveGameForm.getPlayers()[1]);

    }
    private void backBtnAction(){
         stringEventListener.listen("back");
    };

    public void setStringEventListener(StringEventListener stringEventListener) {
        this.stringEventListener = stringEventListener;
    }
}
