package view;

import controller.Repeater;
import controller.scenes.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class RenderingController extends JPanel {

    private static final long RENDERING_FREQ = 16;

    private final Object sceneSwitchLock = new Object();

    private Repeater repeater;
    private Scene scene = null;

    private Dimension screen;
    private JFrame frame;

    public RenderingController(){

        screen = Toolkit.getDefaultToolkit().getScreenSize();

        // Setup JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Removes the task bar
        frame.getContentPane().add(this);

        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        frame.getContentPane().setCursor(blankCursor);

        frame.pack(); // Not sure why this is necessary
        frame.setVisible(true);
    }

    public void setScene(Scene scene){
        synchronized(sceneSwitchLock){
            boolean start = this.scene == null;
            this.scene = scene;
            if(start) repeater = new Repeater(RENDERING_FREQ, this::repaint);
        }
    }

    public void addKeyListener(KeyListener keyListener){
        frame.addKeyListener(keyListener);
    }

    @Override
    public void paintComponent(Graphics graphics1d){
        super.paintComponent(graphics1d); // Don't remember why this is necessary

        Graphics2D graphics = (Graphics2D) graphics1d;

        // Enable anti-alias
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        synchronized (sceneSwitchLock){
            if(scene != null) scene.render(graphics, screen);
        }
    }


    @Override
    public Dimension getPreferredSize(){
        return screen;
    }
}
