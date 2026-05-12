package edu.curtin.game.api;

//This interface can be used by plugins/scripts to define custom behaviour that should occur when the player successfully traverse an obstacle on the game map.
public interface ObstacleTraverseCallback {
    void onObstacleTraverse();
}
