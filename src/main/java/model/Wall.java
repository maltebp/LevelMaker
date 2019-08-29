package model;

public class Wall extends Entity {

    private boolean intersected = false;

    private double width = 1;
    private double height = 1;

    public void setIntersected(boolean toggle){
        intersected = toggle;
    }

    public boolean isIntersected(){
        return intersected;
    }

    public Wall(double x, double y) {
        super(x, y);
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
