package view;

import controller.scenes.Scene;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Renderer extends JPanel {

    private static final long RENDER_FREQ = 16; // Milliseconds (equal to 60 fps)

    private boolean render = false;

    private Scene scene;
    private Dimension screen;
    private JFrame frame;

    public Renderer(Scene scene) {


        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Removes the task bar

        // Getting screen size
        screen = Toolkit.getDefaultToolkit().getScreenSize();

        //frame.setLocation(  (int) ((screen.width-(fieldWidth*scale))/2),(int) ((screen.height-((fieldHeight+STATS_HEIGHT)*scale))/2)) ; // Center screen
        frame.getContentPane().add(this);
        frame.pack(); // Not sure why this is necessary
        frame.setVisible(true);

        setScene(scene);

        startRender();
    }


    public void setScene(Scene scene){

        if(this.scene != null){
            frame.removeKeyListener(this.scene);
        }

        this.scene = scene;
        frame.addKeyListener(scene);
    }

    private void startRender(){
        new Thread( () -> {
            render = true;
            while(render){
                try{
                    repaint();
                    sleep(RENDER_FREQ);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void paintComponent(Graphics graphics1d){
        super.paintComponent(graphics1d); // Don't remember why this is necessary

        Graphics2D graphics = (Graphics2D) graphics1d;

        // Enable anti-alias
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        scene.render(graphics, screen);
    }

    @Override
    public Dimension getPreferredSize(){
        return screen;
    }
}
