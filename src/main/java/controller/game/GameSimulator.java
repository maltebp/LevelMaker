package controller.game;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.shape.Line;
import model.Game;
import model.Player;
import model.Wall;

import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

import static view.Settings.*;
import static view.VisualSettings.PLAYER_SCALE;

public class GameSimulator {

    private Game game;

    public GameSimulator(Game game){
        this.game = game;
    }


    public void detectCollision() {
        Player player = game.getPlayer();
        LinkedList<Wall> walls = game.getWalls();

        for (Wall wall : walls) {
            wall.setIntersected(detectWallCollision(player, wall));
        }
    }


    public boolean detectWallCollision(Player player, Wall wall){
        double wallX = wall.getX()+0.5;
        double wallY = wall.getY()+0.5;

        double angle = Math.atan2(wallY-player.getY(), wallX-player.getX());


        System.out.println("Angle: "+angle);

        double collX = player.getX() + PLAYER_SCALE/2 * Math.cos(angle);
        double collY = player.getY() + PLAYER_SCALE/2 * Math.sin(angle);

        player.addPoint(collX, collY);

        //System.out.println("("+player.getX() + ", "+player.getY()+") -> "+"("+collX + ", "+collY+")" + "\t Wall: ("+wall.getX()+", "+wall.getY()+")");

        Rectangle wallRect = new Rectangle((int) wall.getX(), (int) wall.getY(), 1, 1);

        return wallRect.contains(collX, collY);
    }



    public void updatePlayerMovement(boolean up, boolean down, boolean left, boolean right){
        Player player = game.getPlayer();

        double adjustedMoveSpeed = SIMULATION_FREQ/1000. * PLAYER_MOVESPEED;

        if(up) player.adjustY(-adjustedMoveSpeed);
        if(down) player.adjustY(adjustedMoveSpeed);
        if(left) player.adjustX(-adjustedMoveSpeed);
        if(right) player.adjustX(adjustedMoveSpeed);
    }

}
