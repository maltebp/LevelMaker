package view;

import model.*;

import static view.Settings.X_CELLS;
import static view.Settings.Y_CELLS;
import static view.VisualSettings.*;

import java.awt.*;

public class GameRenderer {


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

            graphics.setColor(WALL_COLOR);
            graphics.fillRect(
                    (int) (gameField.x + wall.getX()*scale ),
                    (int) (wall.getY()*scale),
                    (int) scale,
                    (int) scale);
        }
    }

    public void renderCannons(Graphics2D graphics){


        for( Cannon cannon : game.getCannons() ){
            PointD translatedPos = translate(cannon.getPos());
            double translatedScale = cannon.getScale()*scale;


            graphics.setColor(CANNON_COLOR);
            fillCenteredCircle(graphics, translatedPos, translatedScale);

            double aimScale = translatedScale * CANNON_AIM_SCALE;

            double aimX = translatedPos.x + (translatedScale/2-aimScale/2) * Math.cos(cannon.getFacing());
            double aimY = translatedPos.y + (translatedScale/2 -aimScale/2) * Math.sin(cannon.getFacing());

            graphics.setColor(CANNON_AIM_COLOR);
            fillCenteredCircle(graphics, aimX, aimY, aimScale);
        }

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


    public void renderProjectiles(Graphics2D graphics) {
        for( Projectile projectile : game.getPlayerProjectiles() ){
            if( projectile.hasHit() ) graphics.setColor(Color.RED);
            else graphics.setColor(PLAYER_PROJECTILE_COLOR);
            fillCenteredCircle(graphics, translate(projectile.getPos()), projectile.getScale()*scale );
        }

        for( Projectile projectile : game.getCannonProjectiles() ){
            graphics.setColor(CANNON_PROJECTILE_COLOR);
            fillCenteredCircle(graphics, translate(projectile.getPos()), projectile.getScale()*scale );
        }
    }


    public PointD translate(PointD point){
        point.x = point.x*scale + gameField.x;
        point.y = point.y*scale + gameField.y;
        return point;
    }

    //TODO: REMOVE THIS

    public void fillCenteredCircle(Graphics2D g, PointD point, double diameter){
        double radius = diameter/2.;
        int drawX = (int) (point.x - radius);
        int drawY = (int) (point.y - radius);
        g.fillOval(drawX, drawY, (int) diameter, (int) diameter);
    }

    public void fillCenteredCircle(Graphics2D g, double x, double y, double diameter){
        fillCenteredCircle(g, new PointD(x,y), diameter
        );
    }


    public void renderLostScreen(Graphics2D graphics) {

        graphics.setColor(LOST_SCREEN_BG_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);

        double x = screen.width/2.;

        Drawer drawer = new Drawer(graphics, new Font( VisualSettings.FONT, Font.BOLD, (int) (screen.width*LOST_SCREEN_TITLE_SCALE) ) );
        drawer.setColor(LOST_SCREEN_TEXT_COLOR);

        drawer.drawString("YOU LOST", x, screen.height*.4);

        drawer.setFontSize( screen.width* LOST_SCREEN_TEXT_SCALE );

        drawer.drawString("Press R to retry", screen.width/2., screen.height*0.6);
        drawer.drawString( "Press ENTER to go back to main menu", x, screen.height*0.7);
        drawer.drawString( String.format("Time: %.3f seconds", game.getTime()/1000.), x, screen.height*0.3);
    }

    public void renderWonScreen(Graphics2D graphics) {
        graphics.setColor(WON_SCREEN_BG_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);

        double x = screen.width/2.;

        Drawer drawer = new Drawer(graphics, new Font( VisualSettings.FONT, Font.BOLD, (int) (screen.width*WON_SCREEN_TITLE_SCALE) ) );
        drawer.setColor(WON_SCREEN_TEXT_COLOR);

        drawer.drawString("YOU WON", x, screen.height*.4);

        drawer.setFontSize( screen.width* WON_SCREEN_TEXT_SCALE );

        drawer.drawString( "Press ENTER to go back to main menu", x, screen.height*0.6);
        drawer.drawString( String.format("Time: %.3f seconds", game.getTime()/1000.), x, screen.height*0.3);
    }

}
