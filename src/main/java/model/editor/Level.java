package model.editor;

import model.editor.Cell;

import java.io.Serializable;

import static model.editor.Cell.*;

public class Level implements Serializable {

    private Cell[][] cells;
    private int spawnX = -1;
    private int spawnY = -1;
    private String name = "";

    public Level(int width, int height){
        cells = new Cell[width][height];
        for(int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                cells[x][y] = EMPTY;
            }
        }
    }

    public boolean spawnIsPlaced(){
        return spawnX>-1 && spawnY>-1;
    }

    public void setCell(int x, int y, Cell cell){
        if(cell == PLAYER ){
            if(spawnX != -1) {
                cells[spawnX][spawnY] = EMPTY;
            }
            spawnX = x;
            spawnY = y;
        }else if( cells[x][y] == PLAYER ){
            spawnX = -1;
            spawnY = -1;
        }
        cells[x][y] = cell;
    }

    public Cell[][] getCells(){
        return cells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
