package controller.scenes;

import controller.GameCreator;
import model.Game;
import model.Level;
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

    private Game game;
    private Level level;
    private boolean renderCellNumber = false;

    public GameScene(Level level){
        this.level = level;
        game = new GameCreator().createGame(level);
    }



    @Override
    public void render(Graphics2D graphics, Dimension dimension) {
        renderGrid(graphics, dimension);
    }

    public void renderGrid(Graphics2D graphics, Dimension screen){
        int cellSize = screen.height / Y_CELLS;
        Dimension gameField = new Dimension( cellSize*X_CELLS, cellSize * Y_CELLS );

        int indent = (screen.width-gameField.width)/2;

        graphics.setColor(GRID_COLOR);

        for( int x=0; x<=X_CELLS; x++ ){
            graphics.drawLine(x*cellSize+indent, 0, x*cellSize+indent, screen.height );
        }

        int endX = screen.width-indent;
        for( int y=1; y<=X_CELLS; y++ ) {
            graphics.drawLine(indent, y * cellSize, endX, y * cellSize);
        }

        if( renderCellNumber ){

            Font font = new Font(VisualSettings.FONT, Font.PLAIN, (int) (screen.width/100 * GRID_NUMBER_SCALE) );

            for(int x=0; x<X_CELLS; x++){
                for( int y=0; y<Y_CELLS; y++){

                    drawCenteredString(
                            graphics,
                            "("+x+","+y+")",
                            new Rectangle(x*cellSize+indent, y*cellSize, cellSize, cellSize),
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
            case KeyEvent.VK_C:
                renderCellNumber = !renderCellNumber;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
