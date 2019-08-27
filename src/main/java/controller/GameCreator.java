package controller;

import model.*;

import static model.Cell.*;

public class GameCreator {

    public Game createGame(Level level){

        Game game = new Game(level);
        Cell[][] cells = level.getCells();

        for(int x=0; x<cells.length; x++){
            for( int y=0; y<cells[x].length; y++){

                switch(cells[x][y]){

                    case WALL:
                        game.addWall(new Wall(x,y));
                        break;
                    case PLAYER:

                        game.setPlayer(new Player(x+0.5,y+0.5));

                        break;
                }
            }
        }

        return game;
    }

}
