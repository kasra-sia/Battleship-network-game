package ap.mini_project.shared.model;

import java.awt.*;
import java.util.LinkedList;

public class Board {
    private int h, w;
    private Cell[][] cells;
    private String message;
    private LinkedList<Ship> ships = new LinkedList<>();
    public Board(int h, int w) {
        this.h = h;
        this.w = w;
        cells = new Cell[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cells[i][j] = new Cell();
                if ((i+j)%2==0) {
                    cells[i][j].setColor(Color.BLACK);
                }
                else cells[i][j].setColor(Color.WHITE);
            }
        }

    }
    public void addShip(Ship ship) {
        ships.add(ship);
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.isVertical()) {
                    cells[ship.getX()][i+ship.getY()].setShip(ship);
                }else cells[i+ship.getX()][ship.getY()].setShip(ship);

            }
        }


    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public Cell[][] getCells() {
        return cells;
    }


    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedList<Ship> getShips() {
        return ships;
    }

    public int getDamagedCells(){
        int count = 0;
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                if (cells[i][j].isDamaged())
                    count++;
            }
        }
        return count;
    }
    public int getDamagedShips(){
        int count = 0;
        for (Ship ship : ships) {
            if (ship.getHealth()==0)
                count++;
        }
        return count;
    }

}
