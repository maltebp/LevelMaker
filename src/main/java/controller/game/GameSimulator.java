package controller.game;

import model.Game;
import model.Player;
import static view.Settings.*;

public class GameSimulator {

    private Game game;

    public GameSimulator(Game game){
        this.game = game;
    }


    public void updatePlayerMovement(boolean up, boolean down, boolean left, boolean right){
        Player player = game.getPlayer();

        System.out.println("Move bools: " + up + down + left + right);

        double adjustedMoveSpeed = SIMULATION_FREQ/1000. * PLAYER_MOVESPEED;

        if(up) player.adjustY(-adjustedMoveSpeed);
        if(down) player.adjustY(adjustedMoveSpeed);
        if(left) player.adjustX(-adjustedMoveSpeed);
        if(right) player.adjustX(adjustedMoveSpeed);
    }

}
