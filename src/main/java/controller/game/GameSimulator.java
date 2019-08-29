package controller.game;


import model.*;
import java.awt.*;

import static model.Game.GameState.LOST;
import static model.Game.GameState.RUNNING;
import static model.Game.GameState.WON;
import static view.Settings.*;
import static view.VisualSettings.*;

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


    public Cannon checkCannonCollision(PointD point, double radius){
        for ( Cannon cannon : game.getCannons() ) {

            PointD cannonPos = cannon.getPos();

            double dx = Math.abs(cannonPos.x - point.x);
            double dy = Math.abs(cannonPos.y - point.y);

            double distance = Math.sqrt( Math.pow(dx,2)+Math.pow(dy,2));

            if(distance < (cannon.getScale()+radius)/2) return cannon;
        }

        return null;
    }


    public boolean checkPlayerCollision(PointD point, double radius){
        Player player = game.getPlayer();


        double dx = Math.abs(player.getX() - point.x);
        double dy = Math.abs(player.getY() - point.y);

        double distance = Math.sqrt( Math.pow(dx,2)+Math.pow(dy,2));

        return distance < (PLAYER_SCALE+radius)/2;
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

                if( checkWallCollision(projectilePos, projectile.getScale()/2) || checkFieldCollision(projectilePos, projectile.getScale()/2) ){
                    projectile.hasHit();
                    game.removePlayerProjectile(projectile);
                }

                Cannon collidedCannon = checkCannonCollision(projectilePos, projectile.getScale()/2);

                if( collidedCannon != null){
                    projectile.hasHit();
                    collidedCannon.adjustHealth(-PLAYER_DAMAGE);
                    if(collidedCannon.getHealth() <= 0){
                        game.removeCannon(collidedCannon);
                        if( game.getCannons().size() <= 0 ){
                            gameFinished(true);
                        }
                    }
                    game.removePlayerProjectile(projectile);
                }
            }
        }

        for( Projectile projectile : game.getCannonProjectiles() ){
            if(!projectile.hasHit()){
                projectile.adjustX(SIMULATION_FREQ/1000. * projectile.getxVel());
                projectile.adjustY(SIMULATION_FREQ/1000. * projectile.getyVel());

                PointD projectilePos = new PointD(projectile.getX(), projectile.getY());

                // Cannon hits wall
                if( checkWallCollision(projectilePos, projectile.getScale()/2) || checkFieldCollision(projectilePos, projectile.getScale()/2) ){
                    projectile.hasHit();
                    game.removeCannonProjectile(projectile);
                }

                // Cannon hits player
                if(checkPlayerCollision(projectilePos, projectile.getScale()/2)){
                    projectile.hasHit();

                    if(CANNON_DAMAGE_ENABLED){
                        gameFinished(false);
                    }
                    game.removeCannonProjectile(projectile);
                }
            }
        }
    }


    public void updateCannonFacing(){
        for(Cannon cannon : game.getCannons() ){
            Player player = game.getPlayer();
            if( cannonPlayerDistance(cannon) < CANNON_AIM_DISTANCE ){
                cannon.setFacing(Math.atan2(player.getY()-cannon.getY(), player.getX()-cannon.getX()));
            }
        }
    }

    public double cannonPlayerDistance(Cannon cannon){
        Player player = game.getPlayer();

        double dx = Math.abs(player.getX() - cannon.getX());
        double dy = Math.abs(player.getY() - cannon.getY());

        return Math.sqrt( Math.pow(dx,2)+Math.pow(dy,2));
    }

    public void updateCooldowns(){
        game.getPlayer().adjustShootCooldown(-SIMULATION_FREQ);
        for(Cannon cannon : game.getCannons() ){
            if(cannon.getCooldown()>0 ) cannon.adjustRemainingCooldown(-SIMULATION_FREQ);
        }
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

    public void fireCannons(){

        for( Cannon cannon : game.getCannons() ){
            if(cannon.getRemainingCooldown() <= 0 && cannonPlayerDistance(cannon) < CANNON_AIM_DISTANCE){
                double direction = cannon.getFacing();

                cannon.setRemainingCooldown((cannon.getCooldown()));

                game.addCannonProjectile( new Projectile(
                         cannon.getX()+ cannon.getScale()/2 * Math.cos(direction),
                         cannon.getY() + cannon.getScale()/2 * Math.sin(direction),
                        CANNON_PROJECTILE_SCALE,
                        direction,
                        CANNON_PROJECTILE_VELOCITY * Math.cos(direction),
                        CANNON_PROJECTILE_VELOCITY * Math.sin(direction)
                ));

            }
        }
    }

    public void gameFinished(boolean won){
        if( game.getState() == RUNNING){
            game.setTime(System.currentTimeMillis()-game.getStartTime());
            game.setState( won ? WON : LOST);
        }
    }
}
