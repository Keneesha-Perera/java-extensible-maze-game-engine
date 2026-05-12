package edu.curtin.game;

import java.util.*;
import java.time.LocalDate;
import java.text.Normalizer;
import edu.curtin.game.api.*;

/*Represnts the main game world, including the map grid, player, items,
  obstacles, and realted game logic such as movement, visibility, and interactions*/
public class GameMap implements GameAPI
{
    //Core game fields
    private final int rows, cols;
    private final Cell[][] cells;
    private final Player player;
    private final GameConfig config;

    //Callback lists for plugin interaction
    private final List<PlayerMoveCallback> moveCallbacks = new ArrayList<>();
    private final List<ItemPickupCallback> pickupCallbacks = new ArrayList<>();
    private final List<ActionCallback> actionCallbacks = new ArrayList<>();
    private final List<ObstacleTraverseCallback> obstacleCallbacks = new ArrayList<>();

    //Localization and date tracking
    private ResourceBundle messages;
    private LocalDate currentDate;

    //Messages to display after the map updates
    private final List<String> postMapMessages = new ArrayList<>();

    //Getters and Setters for localization and time
    public void setMessages(ResourceBundle messages) { this.messages = messages; }
    public ResourceBundle getMessages() { return messages; }

    public void setCurrentDate(LocalDate date) { this.currentDate = date; }
    public LocalDate getCurrentDate() { return currentDate; }

    //Advances the in-game date by one day
    public void advanceDay() {
        if (currentDate != null) {
            currentDate = currentDate.plusDays(1);
        }
    }

    //Constructs the game map based on the provided GameConfig
    public GameMap(GameConfig config)
    {
        this.config = config;
        this.rows = config.getRows();
        this.cols = config.getCols();
        this.cells = new Cell[rows][cols];

        //Initialize the empty cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell();
            }
        }

        //Place the Goal Cell
        int[] goal = config.getGoal();
        if (!inBounds(goal[0], goal[1])) {
            throw new IllegalArgumentException("Goal out of bounds");
        }
        cells[goal[0]][goal[1]].setGoal(true);

        //Place items on the map
        for (GameConfig.Item cfgItem : config.getItems()) {
            for (int[] pos : cfgItem.getPositions()) {
                if (!inBounds(pos[0], pos[1])) {
                    continue;
                }
                cells[pos[0]][pos[1]].setItem(new Item(cfgItem.getName(), cfgItem.getMessage()));
            }
        }

        //Place obstacles on the map
        for (GameConfig.Obstacle cfgObs : config.getObstacles()) {
            for (int[] pos : cfgObs.getPositions()) {
                if (!inBounds(pos[0], pos[1])) {
                    continue;
                }
                cells[pos[0]][pos[1]].setObstacle(new Obstacle(cfgObs.getRequires()));
            }
        }

        //Set player start position
        int[] start = config.getStart();
        if (!inBounds(start[0], start[1])) {
            throw new IllegalArgumentException("Start out of bounds");
        }
        this.player = new Player(start[0], start[1]);

        //Reveal initial visible cells
        revealAdjacent(start[0], start[1]);
        this.currentDate = LocalDate.now();
    }

    //Checks if a given position is within the boundaries of the map
    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    //Make the specified cell and its adjacent cells visible ot the player
    private void revealAdjacent(int r, int c)
    {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        cells[r][c].setVisible(true);
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (inBounds(nr, nc)) {
                cells[nr][nc].setVisible(true);
            }
        }
    }

    //Checks whether the palyer has all required items to pass an obstacle
    private boolean playerHasAll(Collection<String> required)
    {
        if (required == null || required.isEmpty()) {
            return true;
        }

        Set<String> normalizedInventory = new HashSet<>();
        for (Item it : player.getInventory()) {
            String normName = Normalizer.normalize(it.getName(), Normalizer.Form.NFC);
            normalizedInventory.add(normName);
        }

        for (String req : required) {
            String normReq = Normalizer.normalize(req, Normalizer.Form.NFC);
            if (!normalizedInventory.contains(normReq)) {
                return false;
            }
        }

        return true;
    }

    /*GameAPI Implementations*/

    @Override
    public int[] getPlayerLocation() {
        return new int[]{ player.getRow(), player.getCol() };
    }

    @Override
    public void setPlayerLocation(int row, int col) {
        player.setRow(row);
        player.setCol(col);
    }

    @Override
    public List<String> getInventory()
    {
        List<String> names = new ArrayList<>();
        for (Item it : player.getInventory()) {
            names.add(it.getName());
        }
        return names;
    }

    @Override
    public void addItemToInventory(String itemName) {
        player.addItem(new Item(itemName, ""));
    }

    @Override
    public int getRows() { return rows; }

    @Override
    public int getCols() { return cols; }

    @Override
    public boolean isVisible(int row, int col) { return cells[row][col].isVisible(); }

    @Override
    public void setVisible(int row, int col, boolean visible) { cells[row][col].setVisible(visible); }

    @Override
    public String getCellContent(int row, int col)
    {
        Cell cell = cells[row][col];
        if (cell.isGoal()) {
            return "goal";
        } else if (cell.getItem() != null) {
            return "item:" + cell.getItem().getName();
        } else if (cell.getObstacle() != null) {
            return "obstacle";
        } else {
            return "empty";
        }
    }

    //Moves the player to a new location on the map
    @Override
    public boolean movePlayerTo(int row, int col)
    {
        if (!inBounds(row, col)) {
            return false;
        }
        Cell target = cells[row][col];
        boolean obstaclePassed = false;

        //Handles obstacles
        if (target.getObstacle() != null) {
            if (playerHasAll(target.getObstacle().getRequiredItems()) == false) {
                return false;
            }
            obstaclePassed = true;
        }

        //Move player
        player.setRow(row);
        player.setCol(col);
        revealAdjacent(row, col);

        //Handle item pickup
        if (target.getItem() != null) {
            Item picked = target.getItem();
            player.addItem(picked);
            target.setItem(null);
            for (ItemPickupCallback cb : pickupCallbacks) {
                cb.onItemAcquired(picked.getName());
            }
        }

        //Notify callbacks
        if (obstaclePassed) {
            for (ObstacleTraverseCallback cb : obstacleCallbacks) {
                cb.onObstacleTraverse();
            }
        }

        for (PlayerMoveCallback cb : moveCallbacks) {
            cb.onPlayerMove(row, col);
        }

        return true;
    }

    //Moves the player in the direction given by a command string
    public String movePlayer(String cmd) {
        int row = player.getRow();
        int col = player.getCol();

        switch (cmd.toUpperCase()) {
            case "UP":    row--; break;
            case "DOWN":  row++; break;
            case "LEFT":  col--; break;
            case "RIGHT": col++; break;
            default:      return messages.getString("unknown_command");
        }

        //Check map boundaries
        if (row < 0 || row >= cells.length || col < 0 || col >= cells[0].length) {
            return messages.getString("move_blocked") + " " + messages.getString("map_edge");
        }

        Cell target = cells[row][col];

        //Check for obstacles
        if (target.getObstacle() != null) {
            List<String> required = target.getObstacle().getRequiredItems();
            if (!playerHasAll(required)) {
                return messages.getString("move_blocked") +
                    " " + messages.getString("requires_items") + ": " + required;
            }
        }

        boolean success = movePlayerTo(row, col);
        if (success) {
            return messages.getString("move_success");
        } else {
            return messages.getString("move_blocked");
        }
    }

    //Callback registration methods
    @Override
    public void registerPlayerMoveCallback(PlayerMoveCallback callback) {
        moveCallbacks.add(callback);
    }

    @Override
    public void registerItemPickupCallback(ItemPickupCallback callback) {
        pickupCallbacks.add(callback);
    }

    @Override
    public void registerActionCallback(ActionCallback callback) {
        actionCallbacks.add(callback);
    }

    @Override
    public void registerObstacleTraverseCallback(ObstacleTraverseCallback callback) {
        obstacleCallbacks.add(callback);
    }

    //Return true if the player is currenlty standing on the goal cell
    public boolean isAtGoal() {
        return player.getRow() == config.getGoal()[0] && player.getCol() == config.getGoal()[1];
    }

    //Prints the current map to the console
    public void printMap()
    {
        System.out.println(messages.getString("map_title"));
        for (int r = 0; r < rows; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                Cell cell = cells[r][c];
                if (cell.isVisible() == false) {
                    line.append("### ");
                } else if (player.getRow() == r && player.getCol() == c) {
                    line.append(" P  ");
                } else if (cell.isGoal()) {
                    line.append(" G  ");
                } else if (cell.getItem() != null) {
                    line.append(" I  ");
                } else if (cell.getObstacle() != null) {
                    line.append(" O  ");
                } else {
                    line.append(" .  ");
                }

            }
            System.out.println(line);
        }
    }

    //Prints the player's current location and inventory contents
    public void printPlayerStatus()
    {
        System.out.println(messages.getString("player_location") + "[" + player.getRow() + ", " + player.getCol() + "]");
        List<Item> inv = player.getInventory();
        if (inv.isEmpty()) {
            System.out.println(messages.getString("inventory_empty"));
        } else {
            List<String> names = new ArrayList<>();
            for (Item it : inv) {
                names.add(it.getName());
            }
            System.out.println(messages.getString("inventory") + names);
        }
    }

    public int[] getGoal() {
        return config.getGoal();
    }

    //Returns a map of all item names to their postions on the grid
    public Map<String, List<int[]>> getAllItemLocations()
    {
        Map<String, List<int[]>> allItems = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = cells[r][c];
                if (cell.getItem() != null) {
                    String name = cell.getItem().getName();
                    allItems.computeIfAbsent(name, k -> new ArrayList<>()).add(new int[]{r, c});
                }
            }
        }
        return allItems;
    }

    @Override
    public void addObstacle(int row, int col, List<String> requiredItems) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            cells[row][col].setObstacle(new Obstacle(requiredItems));
        }
    }

    @Override
    public boolean hasObstacle(int row, int col) {
        return cells[row][col].getObstacle() != null;
    }

    @Override
    public boolean hasGoal(int row, int col) {
        return cells[row][col].isGoal();
    }

    @Override
    public boolean hasInventory(int row, int col) {
        return cells[row][col].getItem() != null;
    }

    //Adds a message to be displayed after the map is printed
    public void addPostMapMessage(String msg) {
        postMapMessages.add(msg);
    }

    //Retrieves and clears all pending post-map messages
    public List<String> getPostMapMessages() {
        List<String> copy = new ArrayList<>(postMapMessages);
        postMapMessages.clear();
        return copy;
    }
}
