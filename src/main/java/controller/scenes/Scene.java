package controller.scenes;

import java.awt.*;

public interface Scene {

    void render(Graphics2D graphics, Dimension dimension);
    void keypress();
    void simulate();

}
