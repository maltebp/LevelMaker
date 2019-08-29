import model.Cell;

import model.Level;
import org.junit.jupiter.api.Test;

import java.io.*;

import static model.Cell.*;
import static org.junit.jupiter.api.Assertions.*;

public class SerializeLevel {

    private static final String FILENAME = "test.ser";

    @Test
    void serializeDeserialize(){
        Level outputLevel = new Level();

        outputLevel.setCell(0,0, PLAYER);
        outputLevel.setCell(15,4, WALL);
        outputLevel.setCell(2,3, GATE);
        outputLevel.setCell(4,4, CANNON);
        outputLevel.setCell(2,2, LAZER);

        Level inputLevel;


        try {

            File file = new File(FILENAME);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(outputLevel);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            inputLevel = (Level) in.readObject();

            Cell[][] outputCells = outputLevel.getCells();
            Cell[][] inputCells = inputLevel.getCells();

            for(int x=0; x<outputCells.length; x++){
                for(int y=0; y<outputCells[x].length; y++){
                    assertSame( outputCells[x][y], inputCells[x][y] );
                }
            }

            out.close();
            in.close();

            file.delete();

        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }


}
