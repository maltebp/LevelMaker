package controller.scenes;

import org.w3c.dom.css.Rect;
import view.VisualSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends Scene{

    private static final Color BACKGROUND_COLOR = Color.gray;
    private static final double TITLE_SCALE = 6;
    private static final Color TITLE_COLOR = Color.black;
    private static final Color MENU_COLOR = Color.BLACK;
    private static final Color MENU_COLOR_SELECTED = Color.RED;
    private static final double MENU_SCALE = 3;

    private int selected = 0;
    private MenuList menu;

    public MainMenu(  ){
        menu = new MenuList();


        for(int i=0; i<5; i++){
            final String label = "Option "+(i+1);
            menu.addOption( label, () ->
                    System.out.println(label + " chosen") );
        }

        /*menu.addOption("Play", () -> {
            System.out.println("Play game!");
        });

        menu.addOption("Editor", () -> {
            System.out.println("Starting editor!");
        });

        menu.addOption("Settings", () ->
                System.out.println("Opening settings!")
        );*/
    }

    
    @Override
    public void render(Graphics2D graphics, Dimension screen) {

        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0,0, screen.width, screen.height);

        graphics.setColor(TITLE_COLOR);
        drawCenteredString( graphics, "Level Maker", new Rectangle(screen.width, (int) (screen.height*0.4)), new Font(VisualSettings.FONT, Font.BOLD, (int) (screen.width/100 * TITLE_SCALE) )  );
        renderMenu(graphics, screen);
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
    public void keypress() {

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


    @Override
    public void keyReleased(KeyEvent e) {

    }

    private enum MENU_OPTION{
        PLAY,
        EDITOR,
        SETTINGS
    }
}
