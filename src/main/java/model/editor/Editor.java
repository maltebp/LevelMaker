package model.editor;

import model.RectD;

import static settings.Settings.*;

public class Editor {

    private RectD playerContainer;
    private RectD cannonContainer;
    private RectD wallContainer;
    private RectD gameField;
    private Level level = new Level(X_CELLS,Y_CELLS);

    public RectD getPlayerContainer() {
        return playerContainer;
    }

    public void setPlayerContainer(RectD playerContainer) {
        this.playerContainer = playerContainer;
    }

    public RectD getCannonContainer() {
        return cannonContainer;
    }

    public void setCannonContainer(RectD cannonContainer) {
        this.cannonContainer = cannonContainer;
    }

    public RectD getWallContainer() {
        return wallContainer;
    }

    public void setWallContainer(RectD wallContainer) {
        this.wallContainer = wallContainer;
    }

    public RectD getGameField() {
        return gameField;
    }

    public void setGameField(RectD gameField) {
        this.gameField = gameField;
    }

    public Level getLevel() {
        return level;
    }
}
