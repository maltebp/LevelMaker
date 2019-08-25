package controller;

import controller.scenes.MainMenu;
import controller.scenes.Scene;
import view.Renderer;

public class Manager {

    private Renderer renderer;
    private Scene scene;


    public void startProgram(){
        scene = new MainMenu();
        renderer = new Renderer(scene);
    }

    public void switchScene(Scene scene){

    }

}
