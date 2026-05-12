package edu.curtin.game;

//Represents an item that can be found and picked by the player
public class Item {
    private String name;
    private String message;

    public Item(String name, String message) {
        this.name = name;
        this.message = message;
    }

    //Returns the name of item
    public String getName() {
        return name;
    }

    //Returns the message associated with the item
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return name;
    }
}
