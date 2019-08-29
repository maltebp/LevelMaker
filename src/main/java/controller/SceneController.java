package controller;

import controller.scenes.MainMenuScene;
import controller.scenes.Scene;
import view.RenderingController;

public class SceneController {

    private RenderingController renderingController;
    private SimulationController simulationController;
    private Keyboard keyboard;
    private Mouse mouse;

    public void startProgram(){
        keyboard = new Keyboard();
        mouse = new Mouse();
        renderingController = new RenderingController();
        renderingController.addKeyListener(keyboard);
        renderingController.addMouseListener(mouse);
        simulationController = new SimulationController();
        setScene( new MainMenuScene() );
    }

    public void setScene(Scene scene) {
        scene.setManager(this);
        keyboard.setScene(scene);
        mouse.setScene(scene);
        renderingController.setScene(scene);
        simulationController.setScene(scene);
    }

    public void exitProgram(){
        System.exit(0);
    }

}
