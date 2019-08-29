package model;

import java.awt.*;
import java.util.LinkedList;

public class Player {

    private double x;
    private double y;
    private double facing;

    public LinkedList<CollisionPoint> getPoints() {
        return points;
    }

    public void clearPoints(){
        points.clear();
    }

    public void addPoint(double x, double y){
        points.add(new CollisionPoint(x, y));
    }

    private LinkedList<CollisionPoint> points = new LinkedList<>();

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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public class CollisionPoint{
        public double x;
        public double y;

        public CollisionPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public double getFacing() {
        return facing;
    }

    public void setFacing(double facing) {
        this.facing = facing;
    }
}
