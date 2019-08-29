package controller;

import static java.lang.Thread.sleep;

public class Repeater {

    private boolean run = true;

    public Repeater(long frequency, Action action ){
        new Thread( () -> {
            try{
                while(run){
                    action.run();
                    sleep(frequency);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void stop(){
        run = false;
    }

    public interface Action{
        void run();
    }
}
