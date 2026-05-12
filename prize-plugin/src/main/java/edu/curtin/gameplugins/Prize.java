package edu.curtin.gameplugins;

import edu.curtin.game.api.GameAPI;
import edu.curtin.game.api.Plugin;

import java.util.ResourceBundle;

/**
 * Prize plugin:
 * - Counts number of items acquired and obstacles successfully traversed.
 * - When the total reaches 5, adds a special item to the player's inventory.
 * - Provides a manual menu command "prize" to check status.
 */
public class Prize implements Plugin {

    private GameAPI api;
    private ResourceBundle messages; // ✅ use localeHolder.messages
    private int itemsAcquired = 0;
    private int obstaclesPassed = 0;
    private boolean prizeGiven = false;
    private static final String PRIZE_ITEM_NAME = "Golden Trophy";

    // Pass messages when initializing
    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }

    @Override
    public void initialize(GameAPI api) {
        this.api = api;

        // Item acquisition callback
        api.registerItemPickupCallback(itemName -> {
            if (itemName != null) {
                itemsAcquired++;
                checkPrizeCondition();
            }
        });

        // Obstacle traversal callback
        api.registerObstacleTraverseCallback(() -> {
            obstaclesPassed++;
            checkPrizeCondition();
        });

        System.out.println("Prize plugin initialized.");
    }

    private void checkPrizeCondition() {
        if (!prizeGiven && (itemsAcquired + obstaclesPassed) >= 5) {
            api.addItemToInventory(PRIZE_ITEM_NAME);
            System.out.println(messages.getString("prize_awarded") + ": " + PRIZE_ITEM_NAME + "!");
            prizeGiven = true;
        }
    }

    @Override
    public String getName() {
        return "prize"; // command users can type
    }


    @Override
    public void onAction() {
        if (messages != null) {
            System.out.println(messages.getString("prize_manual_message"));
        } else {
            System.out.println("The Golden Trophy will be awarded automatically once you collect enough items or pass enough obstacles.");
        }
    }

}
