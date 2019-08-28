package model;

import controller.Repeater;
import controller.scenes.Scene;

import java.util.LinkedList;

public class Game {
    private Level level;
    private Player player;
    private LinkedList<Wall> walls = new LinkedList<Wall>();
    private boolean[] moveKeysPressed = new boolean[4];

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
}
