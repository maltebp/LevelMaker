package model;

import java.io.Serializable;

import static model.Cell.EMPTY;

public class Level implements Serializable {

    private Cell[][] cells = new Cell[16][5];

    public Level(){
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
