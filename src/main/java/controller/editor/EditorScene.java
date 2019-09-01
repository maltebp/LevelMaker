package controller.editor;

import controller.LevelDAO;
import controller.MainMenuScene;
import controller.Scene;
import model.PointD;
import model.RectD;
import model.editor.Cell;
import model.editor.Editor;
import view.*;

import static java.awt.event.KeyEvent.*;
import static model.editor.Cell.*;

import java.awt.*;

import static settings.Settings.X_CELLS;
import static settings.Settings.Y_CELLS;

public class EditorScene extends Scene {

    private double scale;
    private Editor editor = new Editor();
    private EditorRenderer renderer = new EditorRenderer(editor);
    private GameFieldRenderer gameFieldRenderer = new GameFieldRenderer();
    private MouseRenderer mouseRenderer = new MouseRenderer();
    private TextInputRenderer saveWindowRenderer = new TextInputRenderer(20+4);

    private FileNameBuilder nameBuilder = new FileNameBuilder(".ser", 20);

    private boolean renderMouse = true;
    private boolean renderSaveWindow = false;

    private Cell selectedEntity = EMPTY;

    public EditorScene(){
    }


    @Override
    public void render(Graphics2D graphics, Dimension screen){

        scale = screen.height / (double) Y_CELLS;
        RectD gameField = new RectD( new PointD((screen.width - scale * X_CELLS) / 2., 0), (scale*X_CELLS), screen.height );
        gameFieldRenderer.setField(gameField);
        gameFieldRenderer.setScale(scale);

        renderer.updateScreenSize(screen);
        renderer.updateScale(scale);

        // Creating entity containers
        PointD pos = new PointD();

        double size = gameField.pos.x * 0.4;
        double spacing = screen.height*0.1;

        pos.x = gameField.pos.x*0.3;
        pos.y = (screen.height-size*3-spacing*2) / 2;

        editor.setPlayerContainer( new RectD( pos, size, size));
        pos.y += size+spacing;
        editor.setCannonContainer(new RectD( pos, size, size));
        pos.y += size+spacing;
        editor.setWallContainer( new RectD( pos, size, size));

        renderer.renderBackground(graphics);
        renderer.renderEntityContainers(graphics);
        gameFieldRenderer.renderField(graphics);
        gameFieldRenderer.renderGrid(graphics);
        gameFieldRenderer.renderCells(graphics, editor.getLevel().getCells());


        if( renderMouse ){
            EntityRenderer entityRenderer = new EntityRenderer(graphics);
            PointD mousePos = new PointD(MouseInfo.getPointerInfo().getLocation());

            switch(selectedEntity){
                case EMPTY:
                    mouseRenderer.renderMouse( graphics, mousePos, scale*0.5);
                    break;
                case PLAYER:
                    entityRenderer.renderPlayer( mousePos, scale*0.5, 0);
                    break;
                case WALL:
                    entityRenderer.renderWallCenter( mousePos, scale*0.5);
                    break;
                case CANNON:
                    entityRenderer.renderCannon( mousePos, scale*0.5, 0);
                    break;
            }
        }

        if( renderSaveWindow ){
            saveWindowRenderer.setScreenSize(screen);
            saveWindowRenderer.render(graphics, nameBuilder.getName());
        }

    }


    @Override
    public void mousePressed(int button) {
        if( renderMouse ){
            PointD mousePos = new PointD(MouseInfo.getPointerInfo().getLocation());
            if( gameFieldRenderer.isInGameField(mousePos)){
                PointD translatedPos = gameFieldRenderer.translateToField(mousePos);
                placeEntity(selectedEntity, (int) translatedPos.x, (int) translatedPos.y );
            }else if( editor.getPlayerContainer().contains(mousePos)){
                selectedEntity = PLAYER;
            }else if( editor.getWallContainer().contains(mousePos)){
                selectedEntity = WALL;
            }else if( editor.getCannonContainer().contains(mousePos)){
                selectedEntity = CANNON;
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {

        if( renderSaveWindow ){
            switch(keyCode){
                case VK_ESCAPE:
                    renderSaveWindow = false;
                    renderMouse = true;
                    break;
                case VK_ENTER:
                    if( nameBuilder.getNameLength() > 0 ){
                        LevelDAO levelSaver = new LevelDAO();
                        editor.getLevel().setName(nameBuilder.getName());
                        levelSaver.saveLevel(editor.getLevel());
                        manager.setScene(new MainMenuScene());
                    }
                    break;
                case VK_BACK_SPACE:
                    nameBuilder.removeLastCharacter();
                    break;
                default:
                    if( keyCode >= VK_A && keyCode <= VK_Z ){
                        if(!keyboard.isPressed(VK_SHIFT)){
                            keyCode += 32; // Changing to small letter
                        }
                    }
                    nameBuilder.addCharacter((char) keyCode);
                    break;
            }


        }else{
            switch(keyCode){
                case VK_ESCAPE:
                    selectedEntity = EMPTY;
                    break;
                case VK_ENTER:
                    if( editor.getLevel().spawnIsPlaced() ){
                        renderSaveWindow = true;
                        renderMouse = false;
                        nameBuilder.clearName();
                    }
                    break;
            }
        }
    }


    private void placeEntity(Cell cell, int x, int y){
        editor.getLevel().setCell(x,y, cell);
    }
}
