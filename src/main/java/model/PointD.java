package model;

import java.awt.*;

public class PointD {
    public double x = 0;
    public double y = 0;

    public PointD(){}

    public PointD(double x, double y){
        this.x = x;
        this.y = y;
    }

    public PointD(Point point){
        x = point.x;
        y = point.y;
    }

    public String toString(){
        return String.format("(%.2f,%.2f", x, y);
    }
}
