import controller.Manager;
import model.Level;

import java.io.File;
import java.io.OutputStreamWriter;

import static model.Cell.*;

public class Main {

    /** Just initializes the Manager */
    public static void main(String[] args) {

        Manager manager = new Manager();

        manager.startProgram();
    }
}
