package edu.curtin.game;

import java.util.List;

//Returns an obstacle on the game map
public class Obstacle {
    private List<String> requiredItems;

    public Obstacle(List<String> requiredItems) {
        this.requiredItems = requiredItems;
    }

    //Returns the list of items required to pass this obstacle
    public List<String> getRequiredItems() {
        return requiredItems;
    }

    @Override
    public String toString() {
        return "Requires: " + requiredItems;
    }
}
