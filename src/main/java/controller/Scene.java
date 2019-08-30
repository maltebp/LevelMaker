package controller;

import view.Keyboard;
import view.Mouse;

import java.awt.*;

public abstract class Scene{

    protected SceneController manager = null;
    protected Keyboard keyboard = null;
    protected Mouse mouse = null;


    public void setManager(SceneController manager){
        this.manager = manager;
    }

    public void render(Graphics2D graphics, Dimension dimension){}
    public void simulate(){}

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setMouse(Mouse mouse){
        this.mouse = mouse;
    }

    public void keyPressed(int keyCode){}

    public void mousePressed(int button){}
}
