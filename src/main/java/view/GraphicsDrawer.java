package view;

import model.PointD;

import java.awt.*;

public class GraphicsDrawer {

    private Graphics2D graphics;

    public GraphicsDrawer(Graphics2D graphics){
        this.graphics = graphics;
    }

    public void setColor(Color color){
        graphics.setColor(color);
    }

    public void fillCenteredCircle(PointD point, double radius) {
        int drawX = (int) (point.x - radius);
        int drawY = (int) (point.y - radius);
        graphics.fillOval(drawX, drawY, (int) radius*2, (int) radius*2);
    }

}
