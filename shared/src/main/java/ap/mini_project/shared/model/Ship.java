package ap.mini_project.shared.model;


import java.awt.*;
import java.util.LinkedList;

public class Ship {
    private final int length,width;
    private final boolean isVertical;
    private int x,y;
    private int health;
    private final ShipType shipType;
    public Ship (boolean isVertical, ShipType shipType,int x,int y) {
        this.isVertical = isVertical;
        this.x = x;
        this.y=y;
        this.health = shipType.getLength();
        this.shipType = shipType;
        this.width = shipType.getWidth();
        this.length = shipType.getLength();
    }
    public void getDamaged(){health--;}
    public boolean isVertical(){return isVertical;}

    public int getHealth() {
        return health;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public synchronized LinkedList<Point> neighboringCellsPoint() {
        LinkedList<Point> ans = new LinkedList<>();
        if (isVertical) {
            for (int i = y; i < length + y; i++) {
                if (x - 1 >= 0)
                    ans.add(new Point(x - 1, i));
                if (x + 1 < 10)
                    ans.add(new Point(x + 1, i));
            }
                if (y + length  < 10) ans.add(new Point(x, y + length ));
                if (y - 1 >= 0){
                    ans.add(new Point(x, y - 1));
                    System.out.println(1);
                }
            System.out.println(2);
                if (y + length < 10 && x + 1 < 10) ans.add(new Point(x + 1, y + length ));
                if (y + length < 10 && x - 1 >= 0) ans.add(new Point(x - 1, y + length ));
                if (y - 1 >= 0 && x + 1 < 10) ans.add(new Point(x + 1, y - 1));
                if (y - 1 >= 0 && x - 1 >= 0) ans.add(new Point(x - 1, y - 1));
            }


        if (!isVertical) {
            System.out.println(21);
            for (int i = x; i < length + x; i++) {
                if (y - 1 >= 0)
                    ans.add(new Point(i, y - 1));
                if (y + 1 < 10)
                    ans.add(new Point(i, y + 1));
            }
                if (x + length  < 10) ans.add(new Point(x + length , y));
                if (x - 1 >= 0) ans.add(new Point(x - 1, y));
                if (x + length  < 10 && y + 1 < 10) ans.add(new Point(x + length , y + 1));
                if (x + length < 10 && y - 1 >= 0) ans.add(new Point(x + length , y - 1));
                if (x - 1 >= 0 && y + 1 < 10) ans.add(new Point(x - 1, y + 1));
                if (x - 1 >= 0 && y - 1 >= 0) ans.add(new Point(x - 1, y - 1));
            }

        return ans;
    }
}
