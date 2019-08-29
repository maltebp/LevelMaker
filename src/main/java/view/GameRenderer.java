package view;

import model.Game;
import model.Player;
import model.Wall;

import static view.Settings.X_CELLS;
import static view.Settings.Y_CELLS;
import static view.VisualSettings.*;

import java.awt.*;
import model.PointD;

public class GameRenderer {


    // TODO: REMOVE THESE (TEST VAR)
    private static final Color COLL_COLOR = Color.RED;
    // ---



    private Game    game;
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

    /** Converts mouse pos into the game field area */
    public PointD getMousePosition(){
        PointD mousePos = new PointD(MouseInfo.getPointerInfo().getLocation());

        mousePos.x -= gameField.x;
        mousePos.y -= gameField.y;

        if(mousePos.x < 0) mousePos.x = 0;
        else if(mousePos.x > gameField.width) mousePos.x =  gameField.width;
        if(mousePos.y < 0) mousePos.y = 0;
        else if(mousePos.y > gameField.height) mousePos.y =  gameField.height;

        mousePos.x /= scale;
        mousePos.y /= scale;

        return mousePos;
    }

    public void renderGameFieldBorder(Graphics2D graphics){
        graphics.setColor(GAMEFIELD_BORDER_COLOR);
        graphics.setStroke(new BasicStroke((int) (GAMEFIELD_BORDER_WIDTH*scale)));
        graphics.drawRect(gameField.x, gameField.y, gameField.width, gameField.height);
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

    public void renderPlayerAim(Graphics2D graphics){
        Player player = game.getPlayer();

        double playerX = player.getX()*scale+gameField.x;
        double playerY = player.getY()*scale+gameField.y;
        double playerRadius = PLAYER_SCALE * scale / 2;
        double aimScale = AIM_SCALE * scale;

        double aimX = playerX + (playerRadius-aimScale/2) * Math.cos(player.getFacing());
        double aimY = playerY + (playerRadius-aimScale/2) * Math.sin(player.getFacing());

        graphics.setColor(AIM_COLOR);
        fillCenteredCircle(graphics, aimX, aimY, aimScale);
    }


    public void renderMouse(Graphics2D g){
        PointD mousePos = getMousePosition();
        mousePos.x = mousePos.x*scale + gameField.x;
        mousePos.y = mousePos.y*scale + gameField.y;
        g.setColor(MOUSE_COLOR);
        fillCenteredCircle(g, mousePos.x, mousePos.y, 0.5*scale);
    }


    //TODO: REMOVE THIS
    public void fillCenteredCircle(Graphics2D g, double x, double y, double diameter){
        double radius = diameter/2.;
        int drawX = (int) (x - radius);
        int drawY = (int) (y - radius);
        g.fillOval(drawX,drawY, (int) diameter, (int) diameter);
    }


}
