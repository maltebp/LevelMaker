package controller.game;

import controller.MainMenuScene;
import controller.Scene;
import model.game.Game;
import model.editor.Level;
import view.GameRenderer;
import settings.VisualSettings;
import static java.awt.event.KeyEvent.*;
import static model.game.Game.GameState.*;

import java.awt.*;

import static settings.Settings.*;

public class GameScene extends Scene {

    private Game game;

    private boolean renderGrid = false;
    private boolean renderCellNumber = false;
    private boolean renderMouse = false;

    private double scale = 1;
    private Rectangle gameField;
    private GameSimulator gameSimulator;
    private GameRenderer gameRenderer;

    public GameScene(Level level){
        game = new GameCreator().createGame(level);
        game.setState(RUNNING);
        game.setStartTime(System.currentTimeMillis());
        gameSimulator = new GameSimulator(game);
        gameRenderer = new GameRenderer(game);
    }


    @Override
    public void render(Graphics2D graphics, Dimension dimension) {
        // Calculating game field
        scale = dimension.height / (double) Y_CELLS;
        gameField = new Rectangle( (int) ((dimension.width - scale * X_CELLS) / 2.), 0, (int) (scale*X_CELLS), dimension.height );

        if(renderGrid) gameRenderer.renderGrid(graphics, dimension);
        if(renderCellNumber) gameRenderer.renderGridNumber(graphics);
        gameRenderer.renderWalls(graphics);
        gameRenderer.renderPlayer(graphics);
        gameRenderer.renderCannons(graphics);
        if(renderMouse) gameRenderer.renderMouse(graphics);
        gameRenderer.renderProjectiles(graphics);
        gameRenderer.renderGameFieldBorder(graphics);

        if( game.getState() == LOST){
            gameRenderer.renderLostScreen(graphics);
        }
        if( game.getState() == WON){
            gameRenderer.renderWonScreen(graphics);
        }
    }



    @Override
    public void simulate() {

        switch(game.getState()){
            case RUNNING:
                gameSimulator.updatePlayerMovement(
                        keyboard.isPressed(VK_W),
                        keyboard.isPressed(VK_S),
                        keyboard.isPressed(VK_A),
                        keyboard.isPressed(VK_D));
                gameSimulator.updatePlayerFacing(gameRenderer.getMousePosition(), game.getPlayer());
                gameSimulator.updateProjectiles();
                gameSimulator.updateCooldowns();
                gameSimulator.updateCannonFacing();
                gameSimulator.fireCannons();
                if(mouse.isButtonPressed(1)) gameSimulator.playerShoots();
                break;
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch(keyCode){
            // Activate Grid
            case VK_C:
                if(!renderGrid) renderGrid = true;
                else if(!renderCellNumber) renderCellNumber = true;
                else{
                    renderGrid = false;
                    renderCellNumber = false;
                }
                break;
            case VK_M:
                renderMouse = !renderMouse;
                break;
            case VK_R:
                if(game.getState() == LOST) manager.setScene(new GameScene(game.getLevel()));
                break;
            case VK_ENTER:
                if(game.getState() == LOST || game.getState() == WON){
                    manager.setScene(new MainMenuScene());
                }
                break;
        }
    }



    @Override
    public void mousePressed(int button){
        if(button == 1){
            gameSimulator.playerShoots();
        }
    }
}
