package controller.scenes;

import controller.Keyboard;
import controller.Mouse;
import controller.SceneController;
import javafx.scene.input.KeyCode;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.KeyListener;

public abstract class Scene{

    protected SceneController manager = null;
    protected Keyboard keyboard = null;
    protected Mouse mouse = null;


    public void setManager(SceneController manager){
        this.manager = manager;
    }

    public abstract void render(Graphics2D graphics, Dimension dimension);
    public abstract void simulate();




    /**
     * Drawer a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     *
     * Source: https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Drawer the String
        g.drawString(text, x, y);
    }

    public void fillCenteredCircle(Graphics g, double x, double y, double diameter){
        double radius = diameter/2.;
        int drawX = (int) (x - radius);
        int drawY = (int) (y - radius);
        g.fillOval(drawX,drawY, (int) diameter, (int) diameter);
    }

    public void fillCenteredCircle(Graphics g, double diameter, Rectangle rect){
        fillCenteredCircle(g, rect.getCenterX(), rect.getCenterY(), diameter);
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setMouse(Mouse mouse){
        this.mouse = mouse;
    }

    public void keyPressed(int keyCode){}

    public void mousePressed(int button){}
}
