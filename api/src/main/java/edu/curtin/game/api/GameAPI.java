package edu.curtin.game.api;

import java.util.List;

//The main interface between the core game and any external plugins or scripts.
public interface GameAPI
{
    //Player information
    int[] getPlayerLocation();
    void setPlayerLocation(int row, int col);
    List<String> getInventory();
    void addItemToInventory(String itemName);

    // Game Map information
    int getRows();
    int getCols();

    default int getMapHeight() { return getRows(); }
    default int getMapWidth() { return getCols(); }

    boolean isVisible(int row, int col);
    void setVisible(int row, int col, boolean visible);
    String getCellContent(int row, int col);
    void addObstacle(int row, int col, List<String> requiredItems);
    boolean hasObstacle(int row, int col);
    boolean hasGoal(int row, int col);
    boolean hasInventory(int row, int col);

    //Movement API 
    boolean movePlayerTo(int row, int col);

    //Callback registration
    void registerPlayerMoveCallback(PlayerMoveCallback callback);
    void registerItemPickupCallback(ItemPickupCallback callback);
    void registerActionCallback(ActionCallback callback);
    void registerObstacleTraverseCallback(ObstacleTraverseCallback callback);

    //Helper method to get the most recently acquired inventory item
    default String getLastAcquiredItem() {
        List<String> inv = getInventory();
        if (inv == null || inv.isEmpty()) {
            return null;
        }
        return inv.get(inv.size() - 1);
    }
}
