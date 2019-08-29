package view;

import model.Game;
import model.Player;
import model.Wall;

import static view.Settings.X_CELLS;
import static view.Settings.Y_CELLS;
import static view.VisualSettings.*;

import java.awt.*;

public class GameRenderer {


    // TODO: REMOVE THESE (TEST VAR)
    private static final Color COLL_COLOR = Color.RED;
    // ---




    private Game game;
    private double scale;
    private Dimension screen;
    private Rectangle gameField;


    public GameRenderer(Game game){
        this.game = game;

        // Getting screen size
        screen = Toolkit.getDefaultToolkit().getScreenSize();

        scale = screen.height / (double) Y_CELLS;
        gameField = new Rectangle( (int) ((screen.width - scale * X_CELLS) / 2.), 0, (int) (scale*X_CELLS), screen.height );
    }

    public void renderWalls(Graphics2D graphics){
        for(Wall wall : game.getWalls() ){
            if(wall.isIntersected())
                graphics.setColor(COLL_COLOR);
            else
                graphics.setColor(WALL_COLOR);

            graphics.fillRect(
                    (int) (gameField.x + wall.getX()*scale ),
                    (int) (wall.getY()*scale),
                    (int) scale,
                    (int) scale);
        }
    }

    public void renderPlayerPoints(Graphics2D graphics){
        Player player = game.getPlayer();

        graphics.setColor(Color.GREEN);

        /*for( Player.CollisionPoint p : player.getPoints()){
            fillCenteredCircle(graphics, p.x*scale+gameField.x, p.y*scale+gameField.y, 0.25*scale );
        }*/
    }


    //TODO: REMOVE THIS
    public void fillCenteredCircle(Graphics g, double x, double y, double diameter){
        double radius = diameter/2.;
        int drawX = (int) (x - radius);
        int drawY = (int) (y - radius);
        g.fillOval(drawX,drawY, (int) diameter, (int) diameter);
    }


}
