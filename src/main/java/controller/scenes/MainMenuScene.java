package controller.scenes;

import controller.game.GameScene;
import model.Level;
import model.MenuList;
import view.VisualSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

import static model.Cell.PLAYER;
import static model.Cell.WALL;

public class MainMenuScene extends Scene{

    private static final Color BACKGROUND_COLOR = Color.gray;
    private static final double TITLE_SCALE = 6;
    private static final Color TITLE_COLOR = Color.black;
    private static final Color MENU_COLOR = Color.BLACK;
    private static final Color MENU_COLOR_SELECTED = Color.RED;
    private static final double MENU_SCALE = 3;
    private static final double DETAILS_SCALE = 1;

    private MenuList menu;

    public MainMenuScene(  ){
        menu = new MenuList();

        menu.addOption("Play", () -> {
            System.out.println("Option chosen: Play");

            Level level = new Level();
            level.setCell(2,2, PLAYER);
            level.setCell(4,4, WALL);
            level.setCell( 12, 2, WALL);

            manager.setScene(new GameScene(level));
        });

        menu.addOption("Editor", () -> {
            System.out.println("Option chosen: Editor");
        });

        menu.addOption("Settings", () ->
                System.out.println("Option chosen: Settings")
        );

    }

    
    @Override
    public void render(Graphics2D graphics, Dimension screen) {

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);

        graphics.setColor(TITLE_COLOR);
        drawCenteredString( graphics, "Level Maker", new Rectangle(screen.width, (int) (screen.height*0.4)), new Font(VisualSettings.FONT, Font.BOLD, (int) (screen.width/100 * TITLE_SCALE) )  );
        renderMenu(graphics, screen);

        graphics.setColor(MENU_COLOR);
        drawCenteredString( graphics, "Press escape to exit", new Rectangle(0, (int) (screen.height*0.9), screen.width, (int) (screen.height*0.1)), new Font(VisualSettings.FONT, Font.BOLD, (int) (screen.width/100 * DETAILS_SCALE) )  );
    }

    public void renderMenu(Graphics2D graphics, Dimension screen){
        double optionHeight = 0.1;

        Rectangle rectangle = new Rectangle(0, (int) (screen.height * 0.3), screen.width, (int) (screen.height*optionHeight) );
        Font font = new Font(VisualSettings.FONT, Font.BOLD, (int) (screen.width/100 * MENU_SCALE));

        for( MenuList.Option option : menu.getOptions()){
            rectangle.translate(0, (int) (optionHeight*screen.height) );
            graphics.setColor( option.isHovered() ? MENU_COLOR_SELECTED : MENU_COLOR );
            drawCenteredString(graphics, option.getTitle(), rectangle, font);
        }
    }

    @Override
    public void simulate() {

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
