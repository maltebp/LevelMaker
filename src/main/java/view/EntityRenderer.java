package view;

import javafx.geometry.Pos;
import model.PointD;

import java.awt.*;

import static settings.Settings.*;
import static settings.VisualSettings.*;

public class EntityRenderer {

    private GraphicsDrawer drawer;
    private Graphics2D graphics;

    public EntityRenderer(Graphics2D graphics){
        this.graphics = graphics;
        this.drawer = new GraphicsDrawer(graphics);
    }

    public void renderPlayer( PointD pos, double scale, double facing) {

        double adjustedScale = scale * PLAYER_SCALE;

        drawer.setColor(PLAYER_COLOR);
        drawer.fillCenteredCircle(pos, adjustedScale / 2);

        double aimScale = adjustedScale * PLAYER_AIM_SCALE;

        double aimX = pos.x + (adjustedScale / 2 - aimScale / 2) * Math.cos(facing);
        double aimY = pos.y + (adjustedScale / 2 - aimScale / 2) * Math.sin(facing);

        drawer.setColor(PLAYER_AIM_COLOR);
        drawer.fillCenteredCircle(new PointD(aimX, aimY), aimScale/2);
    }


    public void renderCannon(PointD pos, double scale, double facing ){

        double adjustedScale = scale*CANNON_SCALE;

        drawer.setColor(CANNON_COLOR);
        drawer.fillCenteredCircle(pos, adjustedScale/2);

        double aimScale = adjustedScale * CANNON_AIM_SCALE;

        double aimX = pos.x + (adjustedScale/2-aimScale/2) * Math.cos(facing);
        double aimY = pos.y + (adjustedScale/2 -aimScale/2) * Math.sin(facing);

        drawer.setColor(CANNON_AIM_COLOR);
        drawer.fillCenteredCircle(new PointD(aimX, aimY), aimScale/2);
    }


    public void renderWall(PointD pos, double scale){
        graphics.setColor(WALL_COLOR);
        graphics.fillRect(
                (int) pos.x,
                (int) pos.y,
                (int) scale,
                (int) scale);
    }

    public void renderWallCenter(PointD pos, double scale){
        renderWall(new PointD(pos.x-scale/2, pos.y-scale/2), scale);
    }
}
