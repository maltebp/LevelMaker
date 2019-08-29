package model.editor;

import model.editor.Cell;

import java.io.Serializable;

import static model.editor.Cell.EMPTY;

public class Level implements Serializable {

    private Cell[][] cells;

    public Level(int width, int height){
        cells = new Cell[width][height];
        for(int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                cells[x][y] = EMPTY;
            }
        }
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    public Cell[][] getCells(){
        return cells;
    }

}
