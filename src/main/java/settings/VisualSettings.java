package settings;


import java.awt.*;

/** Global and generic visual settings throughout the program */
public class VisualSettings {
    public static final String FONT = "Verdana";

    public static final Color WON_SCREEN_BG_COLOR = new Color(0,150,0, 175);
    public static final Color WON_SCREEN_TEXT_COLOR = new Color(255,255,255, 230);
    public static final double WON_SCREEN_TITLE_SCALE = 0.08;
    public static final double WON_SCREEN_TEXT_SCALE = 0.02;

    public static final Color LOST_SCREEN_BG_COLOR = new Color(150, 0, 0, 175);
    public static final Color LOST_SCREEN_TEXT_COLOR = new Color(255,255,255, 230);
    public static final double LOST_SCREEN_TITLE_SCALE = 0.08;
    public static final double LOST_SCREEN_TEXT_SCALE = 0.02;

    public static final Color WALL_COLOR = Color.GRAY;
    public static final double PLAYER_SCALE = 0.8;
    public static final Color AIM_COLOR = Color.WHITE;
    public static final Color MOUSE_COLOR = new Color(110,180,63, 127);
    public static final Color GAMEFIELD_BORDER_COLOR = Color.GRAY;
    public static final double GAMEFIELD_BORDER_WIDTH = 0.05;
    public static final Color PLAYER_PROJECTILE_COLOR = Color.BLUE;
    public static final double PLAYER_PROJECTILE_SCALE = 0.2;
    public static final double PLAYER_PROJECTILE_VELOCITY = 4;
    public static final Color CANNON_COLOR = new Color(150,50,50);
    public static final Color CANNON_AIM_COLOR = Color.white;
    public static final Color CANNON_PROJECTILE_COLOR = CANNON_COLOR;
    public static final double CANNON_AIM_SCALE = 0.3; //Decimal percentage of the Cannon's scale
    public static final double CANNON_AIM_DISTANCE = 6;



    public static final double AIM_SCALE = 0.3;

}
