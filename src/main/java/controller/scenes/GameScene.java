package controller.scenes;

import controller.GameCreator;
import model.Game;
import model.Level;
import model.Player;
import model.Wall;
import view.VisualSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

import static view.Settings.X_CELLS;
import static view.Settings.Y_CELLS;

public class GameScene extends Scene {

    private static final Color BACKGROUND_COLOR = Color.lightGray;
    private static final Color GRID_COLOR = Color.GRAY;
    private static final Color GRID_NUMBER_COLOR = GRID_COLOR;
    private static final double GRID_NUMBER_SCALE = 0.6;
    private static final Color WALL_COLOR = Color.GRAY;
    private static final Color PLAYER_COLOR = Color.blue;
    private static final double PLAYER_SCALE = 0.8;

    private Game game;
    private Level level;
    private boolean renderGrid = false;
    private boolean renderCellNumber = false;
    private double scale = 1;
    private Rectangle gameField;

    public GameScene(Level level){
        this.level = level;
        game = new GameCreator().createGame(level);
    }


    @Override
    public void render(Graphics2D graphics, Dimension dimension) {

        // Calculating game field
        scale = dimension.height / (double) Y_CELLS;
        gameField = new Rectangle( (int) ((dimension.width - scale * X_CELLS) / 2.), 0, (int) (scale*X_CELLS), dimension.height );

        if(renderGrid) renderGrid(graphics, dimension);
        renderWalls(graphics, dimension);
        renderPlayer(graphics);
    }

    public void renderPlayer(Graphics2D graphics){
        Player player = game.getPlayer();
        graphics.setColor(PLAYER_COLOR);
        fillCenteredCircle(graphics,gameField.x+player.getX()*scale, gameField.y+player.getY()*scale, PLAYER_SCALE*scale  );
    }

    public void renderWalls(Graphics2D graphics, Dimension screen){
        for(Wall wall : game.getWalls() ){
            graphics.setColor(WALL_COLOR);
            graphics.fillRect((int) (gameField.x + wall.getX()*scale ),
                                (int) (wall.getY()*scale),
                                (int) scale,
                                (int) scale);
        }
    }

    public void renderGrid(Graphics2D graphics, Dimension screen){

        graphics.setColor(GRID_COLOR);

        // Render vertical lines
        for( int x=0; x<=X_CELLS; x++ ){
            graphics.drawLine((int) (x*scale+gameField.x), 0, (int) (x*scale+gameField.x), screen.height );
        }

        // Render horizontal lines
        for( int y=1; y<=X_CELLS; y++ ) {
            graphics.drawLine(gameField.x, (int) (y * scale), gameField.x+gameField.width, (int) (y * scale) );
        }

        if( renderCellNumber ){
            Font font = new Font(VisualSettings.FONT, Font.PLAIN, (int) (screen.width/100 * GRID_NUMBER_SCALE) );

            for(int x=0; x<X_CELLS; x++){
                for( int y=0; y<Y_CELLS; y++){

                    drawCenteredString(
                            graphics,
                            "("+x+","+y+")",
                            new Rectangle( (int) (x*scale+gameField.x), (int) (y*scale), (int) scale, (int) scale),
                            font
                    );
                }
            }
        }
    }



    @Override
    public void simulate() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){


            // Activate Grid
            case KeyEvent.VK_C:
                if(!renderGrid) renderGrid = true;
                else if(!renderCellNumber) renderCellNumber = true;
                else{
                    renderGrid = false;
                    renderCellNumber = false;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
