package controller;

import model.*;

import static view.Settings.*;

public class GameCreator {

    public Game createGame(Level level){

        Game game = new Game(level);
        Cell[][] cells = level.getCells();
        game.setWidth(cells.length);
        game.setHeight(cells[0].length);

        for(int x=0; x<cells.length; x++){
            for( int y=0; y<cells[x].length; y++){

                switch(cells[x][y]){

                    case WALL:
                        game.addWall(new Wall(x,y));
                        break;
                    case PLAYER:
                        game.setPlayer(new Player(x+0.5,y+0.5));
                        break;
                    case CANNON:
                        Cannon cannon = new Cannon( x+0.5,y+0.5, CANNON_SCALE, CANNON_FACING_VEL, CANNON_COOLDOWN);
                        cannon.setHealth(CANNON_HEALTH);
                        game.addCannon(cannon);

                        break;

                }
            }
        }

        return game;
    }

}
