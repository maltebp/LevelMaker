package controller;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import model.editor.Level;

import java.io.*;
import java.util.LinkedList;

import static settings.Settings.*;

public class LevelDAO {


    public LinkedList<Level> loadAllLevels(){
        File folder = getFolder();


        if(folder == null) return null;

        LinkedList<Level> levels = new LinkedList<>();

        File[] files = getFolder().listFiles();

        if( files == null ) return null;

        for( File file : files ){
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
                levels.add((Level) input.readObject());
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                System.out.println("Couldn't convert file to Level object");
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error occured while reading file: "+file);
            }
        }
        return levels;
    }



    public boolean saveLevel(Level level){
        File folder = getFolder();
        if( folder != null ){
            try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream( folder.getPath()+ File.separator + level.getName()))){
                output.writeObject(level);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private File getFolder(){
        String path = System.getProperty("user.home");
        path += File.separator + GAME_FOLDER + File.separator + LEVEL_FOLDER;
        File dir = new File(path);


        if (dir.exists() || dir.mkdirs()) {
            return dir;
        } else {
            System.out.println("Could not make directory");
        }
        return null;
    }

}
