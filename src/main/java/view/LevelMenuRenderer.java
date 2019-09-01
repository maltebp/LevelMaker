package view;

import controller.MenuList;
import model.PointD;

import java.awt.*;
import java.util.List;
import static settings.VisualSettings.*;

public class LevelMenuRenderer {


    private Dimension screen;

    public void updateScreen(Dimension screen){
        this.screen = screen;
    }



    public void renderLevelList(Graphics2D graphics, List<MenuList.Option> options){
        GraphicsWriter writer = new GraphicsWriter(graphics, new Font(FONT, Font.PLAIN, (int) (screen.width*LEVEL_MENU_SCALE) ));

        PointD pos = new PointD(screen.width/2., screen.height * 0.2);

        for( MenuList.Option option : options ){
            if(option.isHovered()) writer.setColor(MENU_COLOR_SELECTED);
            else writer.setColor(MENU_COLOR);
            writer.drawString(option.getTitle(), pos);
            pos.y += screen.height*0.1;
        }
    }

}
