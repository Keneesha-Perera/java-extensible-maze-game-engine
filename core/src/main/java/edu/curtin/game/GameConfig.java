package edu.curtin.game;

import java.util.*;

/*Represents the configuration of the game, including the map size, 
player start and goal positions,items, obstacles, plugins, and scripts*/
public class GameConfig {
    private int rows;
    private int cols;
    private int[] start = new int[2];
    private int[] goal = new int[2];

    private final List<Item> items = new ArrayList<>();
    private final List<Obstacle> obstacles = new ArrayList<>();
    private final List<String> plugins = new ArrayList<>();
    private final List<String> scripts = new ArrayList<>();

    //Sets the size of the game map
    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    //Sets the starting position of the player
    public void setStart(int r, int c) {
        start[0] = r;
        start[1] = c;
    }

    //Sets the goal position that the player needs to reach
    public void setGoal(int r, int c) {
        goal[0] = r;
        goal[1] = c;
    }

    //Add a new item to the game configuration
    public void addItem(String name, List<int[]> positions, String message) {
        items.add(new Item(name, positions, message));
    }

    //Add a new obstacle to the game configuration
    public void addObstacle(List<int[]> positions, List<String> requires) {
        obstacles.add(new Obstacle(positions, requires));
    }

    //Add a plugin file name to the configuration
    public void addPlugin(String plugin) {
        plugins.add(plugin);
    }

    //Add a scripts file name to the configuration
    public void addScript(String script) {
        scripts.add(script);
    }

    //The number of rows and columns in the map
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    /*Return player's staeting position and goal position
    (A defensive copy is returned to prevent external modification)*/
    public int[] getStart() { return start.clone(); } 
    public int[] getGoal() { return goal.clone(); } 
    
    //Return an unmodifiable list of all configured items, obstacles, plugin and script file names
    public List<Item> getItems() { return Collections.unmodifiableList(items); }
    public List<Obstacle> getObstacles() { return Collections.unmodifiableList(obstacles); }
    public List<String> getPlugins() { return Collections.unmodifiableList(plugins); }
    public List<String> getScripts() { return Collections.unmodifiableList(scripts); }

    /*FOR TESTING PURPOSES ONLY
     * Returns a formatted string representation of the game configuration*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameConfig{\n");
        sb.append("  rows=").append(rows).append(", cols=").append(cols).append("\n");
        sb.append("  start=").append(Arrays.toString(start)).append("\n");
        sb.append("  goal=").append(Arrays.toString(goal)).append("\n");

        sb.append("  items=[\n");
        for (Item item : items) {
            sb.append("    ").append(item).append("\n");
        }
        sb.append("  ]\n");

        sb.append("  obstacles=[\n");
        for (Obstacle obs : obstacles) {
            sb.append("    ").append(obs).append("\n");
        }
        sb.append("  ]\n");

        sb.append("  plugins=").append(plugins).append("\n");
        sb.append("  scripts=").append(scripts).append("\n");
        sb.append("}");
        return sb.toString();
    }

    //Inner class representing an item that can appear in the game world
    public static class Item {
        private final String name;
        private final List<int[]> positions;
        private final String message;

        public Item(String name, List<int[]> positions, String message) {
            this.name = name;
            this.positions = new ArrayList<>(positions); 
            this.message = message;
        }

        public String getName() { return name; }
        public List<int[]> getPositions() { return Collections.unmodifiableList(positions); }
        public String getMessage() { return message; }

        @Override
        public String toString() {
            StringBuilder posStr = new StringBuilder();
            for (int[] p : positions) {
                posStr.append(Arrays.toString(p)).append(" ");
            }
            return name + " at " + posStr.toString().trim() + " : " + message;
        }
    }

    //Inner class representing an obstacle on the game map
    public static class Obstacle {
        private final List<int[]> positions;
        private final List<String> requires;

        public Obstacle(List<int[]> positions, List<String> requires) {
            this.positions = new ArrayList<>(positions); 
            this.requires = new ArrayList<>(requires);   
        }

        public List<int[]> getPositions() { return Collections.unmodifiableList(positions); }
        public List<String> getRequires() { return Collections.unmodifiableList(requires); }

        @Override
        public String toString() {
            StringBuilder posStr = new StringBuilder();
            for (int[] p : positions) {
                posStr.append(Arrays.toString(p)).append(" ");
            }
            return "Obstacle at " + posStr.toString().trim() + " requires " + requires;
        }
    }
}
