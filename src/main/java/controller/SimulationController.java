package controller;

import controller.scenes.Scene;
import view.Settings;

public class SimulationController {

    private static final long SIM_FREQ = 16;

    private final Object sceneSwitchLock = new Object();
    private Repeater repeater;
    private Scene scene;

    public void setScene(Scene scene){
        synchronized (sceneSwitchLock){
            boolean start = this.scene == null;
            this.scene = scene;

            if(start) {
                repeater = new Repeater(Settings.SIMULATION_FREQ, () -> {
                    synchronized (sceneSwitchLock) {
                        this.scene.simulate();
                    }
                });
            }
        }
    }

    public void stop(){
        if( repeater != null) {
            repeater.stop();
            repeater = null;
        }
    }
}
