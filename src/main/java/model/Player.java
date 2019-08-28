package model;

public class Player {

    private double x;
    private double y;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void adjustX(double adjustment){
        x += adjustment;
    }

    public void adjustY(double adjustment){
        y += adjustment;
    }
}
