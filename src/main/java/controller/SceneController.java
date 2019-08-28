package controller;

import controller.scenes.MainMenuScene;
import controller.scenes.Scene;
import view.RenderingController;

public class SceneController {

    private RenderingController renderingController;
    private SimulationController simulationController;
    private Keyboard keyboard;

    public void startProgram(){
        keyboard = new Keyboard();
        renderingController = new RenderingController();
        renderingController.addKeyListener(keyboard);
        simulationController = new SimulationController();
        setScene( new MainMenuScene() );
    }

    public void setScene(Scene scene) {
        scene.setManager(this);
        keyboard.setScene(scene);
        renderingController.setScene(scene);
        simulationController.setScene(scene);
    }

    public void exitProgram(){
        System.exit(0);
    }

}
