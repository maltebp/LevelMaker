package controller.scenes;

import view.VisualSettings;

import java.awt.*;

public class MainMenu implements Scene{

    private static final Color COLOR_BACKGROUND = Color.gray;
    
    @Override
    public void render(Graphics2D graphics, Dimension screen) {

        graphics.setColor(COLOR_BACKGROUND);
        graphics.fillRect(0,0, screen.width, screen.height);

        graphics.setFont( new Font(VisualSettings.FONT, Font.BOLD, 10) );
        graphics.setColor(Color.black);
        graphics.drawString("Level Maker", 300,300);

    }

    @Override
    public void keypress() {

    }

    @Override
    public void simulate() {

    }
}
