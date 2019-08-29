package model;

import java.awt.*;

public class Cannon {

    private PointD pos;
    private double facing = 0;
    private double facingVel;
    private double cooldown;
    private double scale;
    private double remainingCooldown = 0;
    private double health;

    public Cannon(double x, double y, double scale, double facingVel, double cooldown) {
        this.pos = new PointD(x, y);
        this.scale = scale;
        this.facingVel = facingVel;
        this.cooldown = cooldown;
    }


    public PointD getPos() {
        return pos.copy();
    }

    public void setX(double x) {
        this.pos.x = x;
    }

    public void setY(double y) {
        this.pos.y = y;
    }

    public double getX(){
        return pos.x;
    }

    public double getY(){
        return pos.y;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setPos(PointD pos) {
        this.pos = pos;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public double getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(double remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public void adjustRemainingCooldown(double adjustment){
        this.remainingCooldown += adjustment;
    }


    public void setFacing(double facing){
        this.facing = facing;
    }

    public double getFacing() {
        return facing;
    }

    public double getFacingVel() {
        return facingVel;
    }

    public double getScale() {
        return scale;
    }
}

