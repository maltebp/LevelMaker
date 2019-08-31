package view;

import model.PointD;
import model.RectD;

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

    public void fillCenteredRect(PointD point, double width, double height){
        int drawX = (int) (point.x-width/2);
        int drawY = (int) (point.y-height/2);
        graphics.fillRect(drawX,drawY, (int) width, (int) height);
    }

    public void fillRect(RectD rect){
        graphics.fillRect((int) rect.pos.x, (int) rect.pos.y, (int) rect.width, (int) rect.height );
    }

}
