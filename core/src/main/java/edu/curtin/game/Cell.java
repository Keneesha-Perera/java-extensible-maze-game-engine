package edu.curtin.game;

//The class represnting a single square on the game grid
public class Cell {
    private boolean visible;
    private Item item;
    private Obstacle obstacle;
    private boolean isGoal;

    public Cell() {
        this.visible = false;
        this.item = null;
        this.obstacle = null;
        this.isGoal = false;
    }

    //Check if cell is visible to the player
    public boolean isVisible() {
        return visible;
    }

    //Set whether this cell should be visible to the player
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //Retrieves the item currently located in this cell
    public Item getItem() {
        return item;
    }

    //Places an item in this call or removes it by setting
    public void setItem(Item item) {
        this.item = item;
    }

    //Retrieves the obstacle currently occupying this cell
    public Obstacle getObstacle() {
        return obstacle;
    }

    //Set an obstacle for this cell or removes it by setting
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    //Check if this cell represents the gaol location
    public boolean isGoal() {
        return isGoal;
    }

    //Marks or unmarks this cell as the goal location
    public void setGoal(boolean isGoal) {
        this.isGoal = isGoal;
    }
}
