package edu.curtin.game.api;

//This interface can be used by plugins/scripts to define custom behaviour that should occur when the player moves to a new location on the game map.
public interface PlayerMoveCallback {
    void onPlayerMove(int row, int col);
}
