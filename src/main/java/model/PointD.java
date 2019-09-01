package model;

import java.awt.*;

/**
 * Point class which operates in doubles
 */
public class PointD {
    public double x;
    public double y;

    public PointD(){
        this.x = 0;
        this.y = 0;
    }

    public PointD(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Create a PointD object from a Point object.
     * @param point The point object to copy values from
     */
    public PointD(Point point){
        x = point.x;
        y = point.y;
    }

    /**
     * Copies the object into a new PointD object.
     * @return A copy of the object
     */
    public PointD copy(){
        return new PointD(x,y);
    }


    /**
     * @return The x, y values in the format: (x.xx, y.yy)
     */
    public String toString(){
        return String.format("(%.2f,%.2f", x, y);
    }
}
