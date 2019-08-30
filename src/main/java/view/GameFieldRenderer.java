package view;

import model.PointD;
import model.RectD;
import model.editor.Cell;

import java.awt.*;
import static settings.Settings.*;
import static settings.VisualSettings.*;

public class GameFieldRenderer {

    private RectD field;
    private double scale;

    public void setScale(double scale){
        this.scale = scale;
    }

    public void setField(RectD field){
        this.field = field;
    }

    public void renderField(Graphics2D graphics){
        graphics.setColor(GAMEFIELD_BG_COLOR);
        graphics.fillRect( (int) field.pos.x, (int) field.pos.y, (int) field.width, (int) field.height);

        graphics.setColor(GAMEFIELD_BORDER_COLOR);
        graphics.setStroke(new BasicStroke((int) (scale*GAMEFIELD_BORDER_WIDTH)));
        graphics.drawRect( (int) field.pos.x, (int) field.pos.y, (int) field.width, (int) field.height);
    }


    public void renderGrid(Graphics2D graphics){

        graphics.setStroke(new BasicStroke((int) (scale*GRID_WIDTH)));
        graphics.setColor(GRID_COLOR);

        // Render vertical lines
        for( int x=0; x<=X_CELLS; x++ ){
            graphics.drawLine((int) (x*scale+field.pos.x), 0, (int) (x*scale+field.pos.x), (int) field.height );
        }

        // Render horizontal lines
        for( int y=1; y<=X_CELLS; y++ ) {
            graphics.drawLine( (int) field.pos.x, (int) (y * scale), (int) (field.pos.x+field.width), (int) (y * scale) );
        }
    }

    public void renderCells(Graphics2D graphics, Cell[][] cells){
        EntityRenderer entityRenderer = new EntityRenderer(graphics);

        for( int x=0; x<cells.length; x++){
            for(int y=0; y<cells[x].length; y++){
                switch(cells[x][y]){
                    case PLAYER:
                        entityRenderer.renderPlayer(translateFromField(new PointD(x+0.5,y+0.5)), scale, 0 );
                        break;
                    case CANNON:
                        entityRenderer.renderCannon(translateFromField(new PointD(x+0.5,y+0.5)), scale, 0 );
                        break;
                    case WALL:
                        entityRenderer.renderWall(translateFromField(new PointD(x,y)), scale);
                        break;
                }
            }
        }
    }


    public boolean isInGameField(PointD point){
        return field.contains(point);
    }

    public PointD translateFromField(PointD point){
        return new PointD(
                (point.x*scale+field.pos.x),
                (point.y*scale+field.pos.y)
        );
    }

    public PointD translateToField(PointD point){
        return new PointD(
                (point.x-field.pos.x)/scale,
                (point.y-field.pos.y)/scale
        );
    }

    public void renderGridNumbers(){}




}
