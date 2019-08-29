package model;

import java.util.LinkedList;

public class Game {
    private Level level;
    private Player player;
    private long startTime;
    private long time;
    private LinkedList<Wall> walls = new LinkedList<Wall>();
    private LinkedList<Cannon> cannons = new LinkedList<>();
    private LinkedList<Projectile> playerProjectiles = new LinkedList<>();
    private LinkedList<Projectile> cannonProjectiles = new LinkedList<>();

    private int width;
    private int height;
    private GameState state;

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

    public Level getLevel() {
        return level;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
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
        return new LinkedList<>(playerProjectiles);
    }

    public void removePlayerProjectile(Projectile projectile){
        playerProjectiles.remove(projectile);
    }

    public void addPlayerProjectile(Projectile projectile){
        playerProjectiles.add(projectile);
    }


    public LinkedList<Projectile> getCannonProjectiles(){
        return new LinkedList<>(cannonProjectiles);
    }

    public void removeCannon(Cannon cannon){
        cannons.remove(cannon);
    }

    public void removeCannonProjectile(Projectile projectile){
        cannonProjectiles.remove(projectile);
    }

    public void addCannonProjectile(Projectile projectile){
        cannonProjectiles.add(projectile);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public enum GameState{
        RUNNING,
        WON,
        LOST
    }

}
