package controller;

import controller.scenes.MainMenu;
import controller.scenes.Scene;
import view.Renderer;

public class Manager {

    private Renderer renderer;
    private Scene scene;


    public void startProgram(){
        renderer = new Renderer();

        switchScene( new MainMenu());
    }

    public void switchScene(Scene scene){
        this.scene = scene;
        scene.setManager(this);

        renderer.setScene(scene);
    }

    public void exitProgram(){
        System.exit(0);
    }

}
