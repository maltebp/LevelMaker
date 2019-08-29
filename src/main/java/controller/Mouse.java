package controller;

import controller.scenes.Scene;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private boolean buttonsPressed[] = new boolean[100];
    private Scene scene;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonsPressed[e.getButton()] = true;
        scene.mousePressed(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttonsPressed[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean isButtonPressed(int button){
        return buttonsPressed[button];
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        scene.setMouse(this);
    }
}
