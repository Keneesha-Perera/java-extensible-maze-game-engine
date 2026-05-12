package edu.curtin.gameplugins;

import edu.curtin.game.api.Plugin;
import edu.curtin.game.api.GameAPI;
import java.util.ResourceBundle;

/**
 * Reveal plugin:
 * When the player acquires an item whose name contains "map",
 * the plugin reveals the goal and all remaining hidden items.
 * Messages are localized using ResourceBundle.
 */
public class Reveal implements Plugin
{
    private GameAPI api;
    private ResourceBundle messages;

    // Pass messages when initializing
    public void setMessages(ResourceBundle messages)
    {
        this.messages = messages;
    }

    @Override
    public void initialize(GameAPI api)
    {
        this.api = api;

        // Register callback for item acquisition
        api.registerItemPickupCallback(itemName -> {
            if (itemName != null && itemName.toLowerCase().contains("map"))
            {
                revealGoalAndHiddenItems();
            }
        });

        // Internationalized initialization message
        if (messages != null)
        {
            System.out.println(messages.getString("reveal_initialized"));
        }
        else
        {
            System.out.println("Reveal plugin initialized.");
        }
    }

    private void revealGoalAndHiddenItems()
    {
        int rows = api.getRows();
        int cols = api.getCols();

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                String content = api.getCellContent(r, c).toLowerCase();
                if (!api.isVisible(r, c) && (content.equals("goal") || content.startsWith("item:")))
                {
                    api.setVisible(r, c, true);
                    if (messages != null)
                    {
                        System.out.println(
                            String.format(messages.getString("reveal_cell"), content, r, c)
                        );
                    }
                    else
                    {
                        System.out.println("Revealed " + content + " at [" + r + "," + c + "]");
                    }
                }
            }
        }

        /*if (messages != null)
        {
            System.out.println(messages.getString("reveal_finished"));
        }
        else
        {
            System.out.println("Reveal plugin finished updating visibility.");
        }*/  //Cuz on action code is runnign and that message is being printed always
    }

    @Override
    public String getName() {
        return "reveal";
    }

    @Override
    public void onAction() {
        if (messages != null) {
            System.out.println(messages.getString("reveal_manual_message"));
        } else {
            System.out.println("The goal and hidden items are revealed automatically when you pick up a map item.");
        }
    }

}
