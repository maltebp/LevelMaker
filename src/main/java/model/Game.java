package model;

import controller.Repeater;
import controller.scenes.Scene;

import java.util.LinkedList;

public class Game {
    private Level level;
    private Player player;
    private LinkedList<Wall> walls = new LinkedList<Wall>();
    private boolean[] moveKeysPressed = new boolean[4];
    private LinkedList<Projectile> playerProjectiles = new LinkedList<>();


    private int width;
    private int height;

    public Game(Level level){
        this.level = level;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void addWall(Wall wall){
        walls.add(wall);
    }

    public LinkedList<Wall> getWalls(){
        return new LinkedList<>(walls);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedList<Projectile> getPlayerProjectiles(){
        return playerProjectiles;
    }

    public void removePlayerProjectile(Projectile projectile){
        playerProjectiles.remove(projectile);
    }

    public void addPlayerProjectile(Projectile projectile){
        playerProjectiles.add(projectile);
    }
}
