package controller;

import controller.editor.EditorScene;
import controller.game.GameScene;
import model.editor.Level;
import view.GraphicsWriter;

import static settings.VisualSettings.*;
import static settings.Settings.*;
import static model.editor.Cell.*;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MainMenuScene extends Scene {

    private static final Color BACKGROUND_COLOR = Color.gray;
    private static final double TITLE_SCALE = 6;
    private static final Color TITLE_COLOR = Color.black;

    private static final double MENU_SCALE = 3;
    private static final double DETAILS_SCALE = 1;

    private MenuList menu;

    public MainMenuScene(  ){
        menu = new MenuList();

        menu.addOption("Play", () -> {
            System.out.println("Option chosen: Play");
            manager.setScene(new LoadLevelScene());
        });

        menu.addOption("Editor", () -> {
            System.out.println("Option chosen: Editor");
            manager.setScene(new EditorScene());
        });

        menu.addOption("Settings", () ->
                System.out.println("Option chosen: settings.Settings")
        );

    }

    
    @Override
    public void render(Graphics2D graphics, Dimension screen) {

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);

        GraphicsWriter drawer = new GraphicsWriter(graphics, new Font(FONT, Font.BOLD, (int) (screen.width/100 * TITLE_SCALE) ) );

        // Title
        drawer.setColor(TITLE_COLOR);
        drawer.drawString( "Level Maker", screen.width/2., screen.height*0.2);

        // Detail
        drawer.setFontSize(screen.width/100.*DETAILS_SCALE);
        drawer.setColor(MENU_COLOR);
        drawer.drawString("Press escape to exit", screen.width/2., screen.height*0.9);

        // Menu
        drawer.setFontSize(screen.width/100.*MENU_SCALE);
        double y = screen.height * 0.3;
        double optionHeight = 0.1;

        for( MenuList.Option option : menu.getOptions()){
            y += screen.height * optionHeight;
            drawer.setColor( option.isHovered() ? MENU_COLOR_SELECTED : MENU_COLOR );
            drawer.drawString(option.getTitle(), screen.width/2., y);
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch(keyCode){
            case KeyEvent.VK_DOWN:
                menu.nextOption();
                break;
            case KeyEvent.VK_UP:
                menu.previousOption();
                break;
            case KeyEvent.VK_ENTER:
                menu.optionIsChosen();
                break;
            case KeyEvent.VK_ESCAPE:
                manager.exitProgram();
                break;
        }
    }

}
