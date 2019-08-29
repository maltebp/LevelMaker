package view;

import controller.Scene;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private final Object sceneSwitchLock = new Object();
    private Scene scene;
    private boolean[] keysPressed = new boolean[1000];

    public boolean isPressed(int keyCode){
        return keysPressed[keyCode];
    }

    public void setScene(Scene scene){
        synchronized (sceneSwitchLock){
            this.scene = scene;
            scene.setKeyboard(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed[e.getKeyCode()] = true;
        synchronized(sceneSwitchLock){
            scene.keyPressed(e.getKeyCode());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
    }

}
