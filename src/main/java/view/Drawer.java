package view;

import model.PointD;

import java.awt.*;

public class Drawer {

    private Graphics2D graphics;
    private Font font;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;

    public Drawer(Graphics2D graphics){
        this.graphics = graphics;
    }

    public void setFont(Font font){
        this.font = font;
    }

    public void setFontSize(float size){
        if(font != null) font = font.deriveFont(size);
    }

    public void setHorizontalAlignment(HorizontalAlignment alignment){
        this.horizontalAlignment = alignment;
    }

    public void setVerticalAlignment(HorizontalAlignment alignment){
        this.horizontalAlignment = alignment;
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
    }

    public void drawString(String text, PointD pointD){
        drawString(text, pointD.x, pointD.y);
    }

    public void drawString(String text, Point point){
        drawString(text, point.x, point.y);
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
