package model;

import java.util.LinkedList;

public class Game {
    private Level level;
    private Player player;
    private LinkedList<Wall> walls = new LinkedList<Wall>();

    public Game(Level level){
        this.level = level;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void getPlayer(Player player){
        this.player = player;
    }

    public void addWall(Wall wall){
        walls.add(wall);
    }

    public LinkedList<Wall> getWalls(){
        return new LinkedList<>(walls);
    }
}
