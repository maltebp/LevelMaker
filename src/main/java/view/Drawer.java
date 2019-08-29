package view;

import model.PointD;

import java.awt.*;

public class Drawer {

    private Graphics2D graphics;
    private Font font;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;

    public Drawer(Graphics2D graphics, Font font){
        this.graphics = graphics;
        this.font = font;
    }

    public void setFont(Font font){
        this.font = font;
    }

    public void setFontSize(double size){
        if(font != null) font = font.deriveFont((float)size);
    }

    public void setHorizontalAlignment(HorizontalAlignment alignment){
        this.horizontalAlignment = alignment;
    }

    public void setVerticalAlignment(HorizontalAlignment alignment){
        this.horizontalAlignment = alignment;
    }

    public void setColor(Color color){
        graphics.setColor(color);
    }


    public static void drawCenteredString(Graphics g, String text, PointD pos, Font font) {
        drawCenteredString(g, text, pos.x, pos.y, font);
    }

    public static void drawCenteredString(Graphics g, String text, double x, double y, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int drawX = (int) (x - (metrics.stringWidth(text))/2.);
        int drawY = (int) (y - (metrics.getHeight())/2.)+metrics.getAscent();
        g.setFont(font);
        g.drawString(text, drawX, drawY);
    }



    public void drawString(String text, double x, double y){
        FontMetrics metrics = graphics.getFontMetrics(font);
        double drawX = 0;
        double drawY = 0;

        switch (horizontalAlignment){
            case CENTER:
                drawX = (x - (metrics.stringWidth(text))/2.);
                break;

            case LEFT:
                drawX = x;
                break;

            case RIGHT:
                drawX = x-metrics.stringWidth(text);
                break;
        }

        switch (verticalAlignment){
            case CENTER:
                drawY = (y - (metrics.getHeight())/2.)+metrics.getAscent();
                break;
            case TOP:
                drawY = y;
                break;
            case BOTTOM:
                // Not tested
                drawY = y - metrics.getHeight() + metrics.getAscent();
                break;
        }

        graphics.setFont(font);
        graphics.drawString(text, (int) drawX, (int) drawY);
    }

    public void drawString(String text, PointD pointD){
        drawString(text, pointD.x, pointD.y);
    }

    public void drawString(String text, Point point){
        drawString(text, point.x, point.y);
    }


    // TODO: REMOVE THIS!
    public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
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


    public enum HorizontalAlignment {
        CENTER,
        RIGHT,
        LEFT
    }

    public enum VerticalAlignment {
        CENTER,
        TOP,
        BOTTOM,
    }


}
