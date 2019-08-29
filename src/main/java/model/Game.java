package model;

import java.util.LinkedList;

public class Game {
    private Level level;
    private Player player;
    private LinkedList<Wall> walls = new LinkedList<Wall>();
    private LinkedList<Cannon> cannons = new LinkedList<>();
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

    public void addCannon(Cannon cannon){
        cannons.add(cannon);
    }

    public LinkedList<Cannon> getCannons(){
        return new LinkedList<>(cannons);
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
        return new LinkedList<Projectile>(playerProjectiles);
    }

    public void removePlayerProjectile(Projectile projectile){
        playerProjectiles.remove(projectile);
    }

    public void addPlayerProjectile(Projectile projectile){
        playerProjectiles.add(projectile);
    }

}
