package model;

public class Projectile {
    private double x;
    private double y;
    private double direction;
    private double xVel;
    private double yVel;
    private double scale;
    private boolean hit = false;
    private boolean destroy = false;

    public Projectile(double x, double y, double scale, double direction, double xVel, double yVel) {
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        this.scale = scale;
        this.direction = direction;
    }

    public void adjustX(double x){
        this.x += x;
    }

    public void adjustY(double y){
        this.y += y;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public boolean hasHit() {
        return hit;
    }

    public void setHasHit(boolean toggle){
        hit = toggle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getScale() {
        return scale;
    }

    public PointD getPos(){
        return new PointD(x, y);
    }

    public void destroy(){
        destroy = true;
    }
}
