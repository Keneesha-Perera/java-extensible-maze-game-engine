package edu.curtin.game;

import java.util.ArrayList;
import java.util.List;

//Represents the player in the game world
public class Player {
    private int row;
    private int col;
    private final List<Item> inventory;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.inventory = new ArrayList<>();
    }

    //Returns the player's current row position
    public int getRow() {
        return row;
    }

    //Sets the player's current row position
    public void setRow(int row) {
        this.row = row;
    }

    //Returns the player's current column position
    public int getCol() {
        return col;
    }

    //Sets the player's current column position
    public void setCol(int col) {
        this.col = col;
    }

    //Return player's inventory
    public List<Item> getInventory() {
        return inventory;
    }

    //Adds an item to the player's inventory
    public void addItem(final Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    //Returns the most recently added item in the player's inventory
    public Item getLastItem() {
        if (inventory.isEmpty()) {
            return null;
        }
        return inventory.get(inventory.size() - 1);
    }
}
