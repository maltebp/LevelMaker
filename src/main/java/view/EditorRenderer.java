package view;

import model.PointD;
import model.RectD;
import model.editor.Editor;

import java.awt.*;

import static settings.Settings.*;
import static settings.VisualSettings.*;

public class EditorRenderer {

    private Dimension screen;
    private double scale;
    private Editor editor;

    public EditorRenderer(Editor editor){
        this.editor = editor;
    }

    public void updateScale(double scale){
        this.scale = scale;
    }


    public void updateScreenSize(Dimension screen){
        this.screen = screen;
    }


    public void renderBackground(Graphics2D graphics){
        graphics.setColor(EDITOR_BG_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);
    }


    public void renderEntityContainers(Graphics2D graphics){
        EntityRenderer entityRenderer = new EntityRenderer(graphics);

        double entityScale = editor.getPlayerContainer().height*0.8;

        entityRenderer.renderPlayer(editor.getPlayerContainer().getCenter(), entityScale, 0);

        entityRenderer.renderWallCenter(editor.getWallContainer().getCenter(), entityScale*0.8);
        entityRenderer.renderCannon(editor.getCannonContainer().getCenter(), entityScale, 0);

        renderContainerBorder(graphics, editor.getWallContainer());
        renderContainerBorder(graphics, editor.getPlayerContainer());
        renderContainerBorder(graphics, editor.getCannonContainer());
    }

    public void renderContainerBorder(Graphics2D graphics, RectD container){
        graphics.setColor(EDITOR_EC_BORDER_COLOR);
        graphics.setStroke(new BasicStroke( (int) (EDITOR_EC_BORDER_WIDTH*screen.width/100) ));
        graphics.drawRect( (int) container.pos.x, (int) container.pos.y, (int) container.width, (int) container.height);
    }






    public boolean isMouseInRect( RectD rect ){
        return false;
    }

}
