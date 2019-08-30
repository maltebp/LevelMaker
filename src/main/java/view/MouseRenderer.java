package view;

import model.PointD;

import java.awt.*;

import static settings.VisualSettings.EDITOR_MOUSE_COLOR;

public class MouseRenderer {


    public void renderMouse(Graphics2D graphics, PointD pos, double scale){

        GraphicsDrawer drawer = new GraphicsDrawer(graphics);

        drawer.setColor(EDITOR_MOUSE_COLOR);

        double angle = Math.PI*0.25;

        int x[] = {
                (int) pos.x,
                (int) (pos.x + scale * Math.cos(angle)),
                (int) pos.x
        };

        int y[] = {
                (int) pos.y,
                (int) (pos.y + scale * Math.sin(angle)),
                (int) (pos.y+scale)
        };

        graphics.fillPolygon(new Polygon(x,y,3));
    }

}
