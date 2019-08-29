package controller.game;

import model.*;

import java.awt.*;
import java.util.LinkedList;

import static view.Settings.*;
import static view.VisualSettings.PLAYER_PROJECTILE_SCALE;
import static view.VisualSettings.PLAYER_PROJECTILE_VELOCITY;
import static view.VisualSettings.PLAYER_SCALE;

public class GameSimulator {

    private Game game;

    public GameSimulator(Game game){
        this.game = game;
    }


    public boolean checkPlayerCollision() {
        Player player = game.getPlayer();
        PointD playerPos = new PointD(player.getX(), player.getY());

        boolean collision = checkWallCollision(playerPos, PLAYER_SCALE/2);

        if( checkFieldCollision(playerPos, PLAYER_SCALE/2)) collision = true;

        return collision;
    }

    public boolean checkFieldCollision(PointD pos, double radius){

        if( pos.y-radius < 0  ) return true;
        if( pos.y+radius > game.getHeight()) return true;
        if( pos.x-radius < 0 ) return true;
        if( pos.x+radius > game.getWidth()) return true;

        return false;
    }


    public boolean checkWallCollision(PointD point, double radius){

        for (Wall wall : game.getWalls()) {

            double wallX = wall.getX()+0.5;
            double wallY = wall.getY()+0.5;

            double angle = Math.atan2(wallY-point.y, wallX-point.x);


            double collX = point.x + radius * Math.cos(angle);
            double collY = point.y + radius * Math.sin(angle);

            Rectangle wallRect = new Rectangle((int) wall.getX(), (int) wall.getY(), 1, 1);

            if(wallRect.contains(collX, collY)) return true;
        }

        return false;
    }



    public void updatePlayerMovement(boolean up, boolean down, boolean left, boolean right){
        Player player = game.getPlayer();

        double adjustedMoveSpeed = SIMULATION_FREQ/1000. * PLAYER_MOVESPEED;

        if(up){
            double startY = player.getY();
            player.adjustY(-adjustedMoveSpeed);
            if( checkPlayerCollision() ){
                player.setY(startY);
            }
        }
        if(down){
            double startY = player.getY();
            player.adjustY(adjustedMoveSpeed);
            if( checkPlayerCollision() ){
                player.setY(startY);
            }
        }
        if(left){
            double startX = player.getX();
            player.adjustX(-adjustedMoveSpeed);
            if( checkPlayerCollision() ){
                player.setX(startX);
            }
        }
        if(right){
            double startX = player.getX();
            player.adjustX(adjustedMoveSpeed);
            if( checkPlayerCollision() ){
                player.setX(startX);
            }
        }
    }

    public void updatePlayerFacing(PointD mousePos, Player player){
        double angle = Math.atan2(mousePos.y-player.getY(), mousePos.x-player.getX());
        player.setFacing(angle);
    }


    public void updateProjectiles(){

        for( Projectile projectile : game.getPlayerProjectiles() ){
            if(!projectile.hasHit()){
                projectile.adjustX(SIMULATION_FREQ/1000. * projectile.getxVel());
                projectile.adjustY(SIMULATION_FREQ/1000. * projectile.getyVel());

                PointD projectilePos = new PointD(projectile.getX(), projectile.getY());

                if( checkWallCollision(projectilePos, projectile.getScale()/2) ){
                    projectileHit(projectile);
                }
            }
        }
    }

    public void projectileHit(Projectile projectile){
        projectile.setHasHit(true);
    }


    public void updatePlayerShootCooldown(){
        game.getPlayer().adjustShootCooldown(-SIMULATION_FREQ);
    }


    public void playerShoots(){
        Player player = game.getPlayer();

        if( player.getShootCooldown() <= 0){
            double direction = player.getFacing();

            player.setShootCooldown(PLAYER_SHOOT_COOLDOWN);

            game.addPlayerProjectile( new Projectile(
                    player.getX() + PLAYER_SCALE/2 * Math.cos(direction),
                    player.getY() + PLAYER_SCALE/2 * Math.sin(direction),
                    PLAYER_PROJECTILE_SCALE,
                    direction,
                    PLAYER_PROJECTILE_VELOCITY * Math.cos(direction),
                    PLAYER_PROJECTILE_VELOCITY * Math.sin(direction)
            ));
        }
    }
}
