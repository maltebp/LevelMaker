package controller;

import controller.scenes.Scene;
import javafx.scene.shape.Line;
import view.Settings;

import java.awt.*;

public class SimulationController {

    private static final long SIM_FREQ = 16;

    private final Object sceneSwitchLock = new Object();
    private Repeater repeater;
    private Scene scene;

    public void test(){
        Rectangle r;
        Line line;

    }

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
