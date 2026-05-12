package edu.curtin.game.test;

import edu.curtin.game.api.PlayerMoveCallback;
import edu.curtin.game.api.ItemPickupCallback;
import edu.curtin.game.api.ActionCallback;

public class APITest {
    public static void main(String[] args) {
        // Implement dummy callbacks to see if they compile
        PlayerMoveCallback pmc = new PlayerMoveCallback() {
            @Override
            public void onPlayerMove(int row, int col) {
                System.out.println("Player moved to " + row + "," + col);
            }
        };

        // Correct method name according to your interface
        ItemPickupCallback ipc = new ItemPickupCallback() {
            @Override
            public void onItemAcquired(String itemName) {
                System.out.println("Picked up " + itemName);
            }
        };

        ActionCallback ac = new ActionCallback() {
            @Override
            public void onAction() {
                System.out.println("Action triggered!");
            }
        };

        // Test calls
        pmc.onPlayerMove(1,2);
        ipc.onItemAcquired("Map");
        ac.onAction();
    }
}
