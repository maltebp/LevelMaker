package controller;

import controller.scenes.Scene;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    private Scene scene;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Button "+e.getButton() + " clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
