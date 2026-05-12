package edu.curtin.gameplugins;

import java.util.Random;
import java.util.ResourceBundle;
import edu.curtin.game.api.GameAPI;
import edu.curtin.game.api.Plugin;

public class Teleport implements Plugin {
    private boolean used = false; // only one teleport allowed
    private Random rand = new Random();
    private GameAPI api;          // reference to the API
    private ResourceBundle messages; // localized messages

    // ✅ Optional: inject messages from Game.java
    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }

    @Override
    public void initialize(GameAPI api) {
        this.api = api;
        if (messages != null) {
            System.out.println(messages.getString("teleport_initialized"));
        } else {
            System.out.println("Teleport plugin initialized.");
        }
    }

    @Override
    public String getName() {
        return "teleport"; // command users type
    }

    @Override
    public void onAction() {
        if (!used && api != null) {
            int newRow, newCol;
            do {
                newRow = rand.nextInt(api.getMapHeight());
                newCol = rand.nextInt(api.getMapWidth());
            } while (!api.movePlayerTo(newRow, newCol));

            if (messages != null) {
                System.out.println(messages.getString("teleported_to") + " [" + newRow + "," + newCol + "]");
            } else {
                System.out.println("Teleported to [" + newRow + "," + newCol + "]");
            }
            used = true;
        } else {
            if (messages != null) {
                System.out.println(messages.getString("teleport_used"));
            } else {
                System.out.println("Teleport already used or API not initialized.");
            }
        }
    }
}
