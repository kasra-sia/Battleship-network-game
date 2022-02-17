package ap.mini_project.shared.model;

import java.awt.*;

public class Cell {
    private Ship ship = null;
    private boolean isDamaged = false;
    private String pieceName;
    private Color color;
    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void getDamaged() {
        isDamaged = true;
        if (ship==null)
        setColor(Color.lightGray);
        else setColor(Color.red);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }
}
