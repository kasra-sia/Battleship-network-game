package ap.mini_project.client.graphic.panels;


import ap.mini_project.client.graphic.ImageLoader;
import ap.mini_project.shared.model.Board;
import ap.mini_project.shared.model.Cell;

import javax.swing.*;
import java.awt.*;

public class  BoardPanel extends JPanel {
    boolean isMyBoard;
    private Board board;
    private final int cellH;
    private final int cellW;

    public BoardPanel(Board board, int cellW, int cellH) {
        setLayout(null);
        this.board = board;
        this.cellW = cellW;
        this.cellH = cellH;
        this.setBounds(100, 100, cellW * board.getW(), cellH * board.getH());
    }

    public void setLocation(int x,int y) {
        this.setBounds(x, y, cellW * board.getW(), cellH * board.getH());
    }

    public synchronized void setBoard(Board board) {
        this.board = board;
    }

    public void setMyBoard(boolean myBoard) {
        isMyBoard = myBoard;
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < board.getW(); i++) {
            for (int j = 0; j < board.getH(); j++) {
                Cell tem = board.getCells()[i][j];
                g.setColor(tem.getColor());
                if (isMyBoard && tem.getShip()!=null && !tem.isDamaged()){
                    g.setColor(Color.GREEN);
//                    System.out.println(tem.getColor().toString());
                }
                g.fillRect(i * cellW, j * cellH, cellW, cellH);
                if (tem.getPieceName() != null) {
                    g.drawImage(ImageLoader.getInstance().getImage(tem.getPieceName()), i * cellW, j * cellH,
                            cellW, cellH, null);
                }
            }
        }
    }
}
