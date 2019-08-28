package controller;

import controller.scenes.Scene;

public class SimulationController {

    private static final long SIM_FREQ = 16;

    private final Object sceneSwitchLock = new Object();
    private Repeater repeater;
    private Scene scene;


    public void setScene(Scene scene){
        synchronized (sceneSwitchLock){
            this.scene = scene;
        }
    }

    public void start(){
        if(repeater != null){
            repeater.stop();
        }
        repeater = new Repeater(SIM_FREQ, () -> {
           synchronized (sceneSwitchLock){
               scene.simulate();
           }
        });
    }

    public void stop(){
        if( repeater != null) {
            repeater.stop();
            repeater = null;
        }
    }
}
