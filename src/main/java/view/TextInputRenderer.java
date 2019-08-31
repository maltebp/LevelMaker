package view;

import model.PointD;
import model.RectD;

import java.awt.*;

import static settings.VisualSettings.*;

public class TextInputRenderer {

    private RectD window = new RectD();
    private Dimension screen;
    private int textMaxLength;


    public TextInputRenderer(int textMaxLength){
        this.textMaxLength = textMaxLength;
    }

    public void setScreenSize(Dimension screen){
        this.screen = screen;
    }



/*
    Font font = new Font(FONT, Font.PLAIN, (int) (screen.width * TEXT_INPUT_SCALE));
    FontMetrics render = graphics.getFontMetrics()

    window.width = width/

    window = new RectD(

    )*/

    public void render(Graphics2D graphics, String text){

        Font font = new Font(FONT, Font.PLAIN, (int) (screen.width * TEXT_INPUT_TEXT_SCALE));
        FontMetrics metrics = graphics.getFontMetrics(font);

        RectD textField = new RectD();
        RectD window = new RectD();

        PointD screenCenter = new PointD( screen.width/2., screen.height/2.);



        textField.width = metrics.stringWidth("W") * textMaxLength * 1.05; // 1.2 is extra margin;
        textField.height = metrics.getHeight()*1.2;
        textField.setCenter(screenCenter);

        window.height = textField.height * 4;
        window.width = textField.width * 1.1;
        window.setCenter(screenCenter);


        graphics.setColor(WINDOW_BACKGROUND_FADE_COLOR);
        graphics.fillRect(0,0,screen.width,screen.height);

        GraphicsDrawer drawer = new GraphicsDrawer(graphics);

        drawer.setColor(WINDOW_COLOR);
        drawer.fillRect(window);

        drawer.setColor(TEXT_INPUT_COLOR);
        drawer.fillRect(textField);

        GraphicsWriter writer = new GraphicsWriter(graphics, font);
        writer.setColor(TEXT_INPUT_TEXT_COLOR);
        writer.drawString(text, textField.getCenter());
    }
}
