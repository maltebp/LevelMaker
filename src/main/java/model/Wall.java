package model;

public class Wall extends Entity {

    private boolean intersected = false;

    public void setIntersected(boolean toggle){
        intersected = toggle;
    }

    public boolean isIntersected(){
        return intersected;
    }

    public Wall(double x, double y) {
        super(x, y);
    }
}
