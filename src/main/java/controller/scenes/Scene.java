package controller.scenes;

import controller.Manager;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class Scene implements KeyListener {

    protected Manager manager = null;

    public void setManager(Manager manager){
        this.manager = manager;
    }

    public abstract void render(Graphics2D graphics, Dimension dimension);
    public abstract void simulate();




    /**
     * Draw a String centered in the middle of a Rectangle.
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
        // Draw the String
        g.drawString(text, x, y);
    }
}
