package edu.curtin.game.api;

public interface Plugin
{
    
    //Called when the plugin is initialized.
    void initialize(GameAPI api);

    //Method to return a name for the plugin to show in a menu.
    default String getName() {
        return this.getClass().getSimpleName();
    }

    // Called when the plugin's menu option is selected (if it has one).
    default void onAction() {
        // default does nothing
    }

}
