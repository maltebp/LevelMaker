package controller.game;

import model.Game;
import model.Player;
import model.Wall;

import java.awt.*;
import java.util.LinkedList;

import static view.Settings.*;
import static view.VisualSettings.PLAYER_SCALE;

public class GameSimulator {

    private Game game;

    public GameSimulator(Game game){
        this.game = game;
    }


    public boolean checkCollision() {
        Player player = game.getPlayer();
        LinkedList<Wall> walls = game.getWalls();

        boolean collision = false;

        for (Wall wall : walls) {
            boolean wallCollision = checkWallCollision(player, wall);
            wall.setIntersected(wallCollision);
            if(wallCollision){
                collision = true;
            }
        }

        if( checkFieldCollision(player)) collision = true;

        return collision;
    }

    public boolean checkFieldCollision(Player player){
        double radius = PLAYER_SCALE/2;

        if( player.getY()-radius < 0  ) return true;
        if( player.getY()+radius > game.getHeight()) return true;
        if( player.getX()-radius < 0 ) return true;
        if( player.getX()+radius > game.getWidth()) return true;

        return false;
    }


    public boolean checkWallCollision(Player player, Wall wall){
        double wallX = wall.getX()+0.5;
        double wallY = wall.getY()+0.5;

        double angle = Math.atan2(wallY-player.getY(), wallX-player.getX());


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

        if(up){
            double startY = player.getY();
            player.adjustY(-adjustedMoveSpeed);
            if( checkCollision() ){
                player.setY(startY);
            }
        }
        if(down){
            double startY = player.getY();
            player.adjustY(adjustedMoveSpeed);
            if( checkCollision() ){
                player.setY(startY);
            }
        }
        if(left){
            double startX = player.getX();
            player.adjustX(-adjustedMoveSpeed);
            if( checkCollision() ){
                player.setX(startX);
            }
        }
        if(right){
            double startX = player.getX();
            player.adjustX(adjustedMoveSpeed);
            if( checkCollision() ){
                player.setX(startX);
            }
        }
    }

}
