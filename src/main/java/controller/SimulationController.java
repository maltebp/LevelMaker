package controller;

import static settings.Settings.*;


public class SimulationController {


    private final Object sceneSwitchLock = new Object();
    private Repeater repeater;
    private Scene scene;

    public void setScene(Scene scene){
        synchronized (sceneSwitchLock){
            boolean start = this.scene == null;
            this.scene = scene;

            if(start) {
                repeater = new Repeater(SIMULATION_FREQ, () -> {
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
