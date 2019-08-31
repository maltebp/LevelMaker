package controller;

import controller.game.GameScene;
import model.editor.Level;
import view.LevelMenuRenderer;

import java.awt.*;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class LoadLevelScene extends Scene {

    private List<Level> levels;
    private MenuList levelMenu = new MenuList();
    private LevelMenuRenderer renderer = new LevelMenuRenderer();

    public LoadLevelScene(){
        LevelDAO levelLoader = new LevelDAO();
        levels = levelLoader.loadAllLevels();

        if(levels.size() > 0 ){
            for(Level level : levels){
                levelMenu.addOption(level.getName(), ()->{
                    this.manager.setScene(new GameScene(level));
                });
            }
        }
    }




    @Override
    public void render(Graphics2D graphics, Dimension dimension) {
        renderer.updateScreen(dimension);
        renderer.renderLevelList(graphics, levelMenu.getOptions());
    }



    @Override
    public void keyPressed(int keyCode) {
        switch(keyCode){

            case VK_DOWN:
                if(levels.size() > 0){
                    levelMenu.nextOption();
                }
                break;

            case VK_UP:
                if(levels.size() > 0){
                    levelMenu.previousOption();
                }
                break;

            case VK_ENTER:
                if(levels.size() > 0 ) {
                    levelMenu.optionIsChosen();
                }
                break;

            case VK_ESCAPE:
                manager.setScene(new MainMenuScene());
                break;
        }
    }
}
