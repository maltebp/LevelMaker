package model.game;

public class Wall {

    private double x;
    private double y;
    private double width = 1;
    private double height = 1;


    public Wall(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getCenterX(){
        return x+width/2;
    }

    public double getCenterY(){
        return y+height/2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
