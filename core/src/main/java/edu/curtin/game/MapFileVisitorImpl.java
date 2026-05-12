package edu.curtin.game;

import java.util.*;
import org.antlr.v4.runtime.tree.TerminalNode;

//Class to process the parsed map file and to extract game configuration data
public class MapFileVisitorImpl extends MapFileBaseVisitor<Object> {
    private GameConfig config = new GameConfig();

    //Process the 'size' declaration from the map file
    @Override
    public Object visitSizeDecl(MapFileParser.SizeDeclContext ctx) {
        int rows = Integer.parseInt(ctx.location().INT(0).getText());
        int cols = Integer.parseInt(ctx.location().INT(1).getText());
        config.setSize(rows, cols);
        return null;
    }

    //Process the 'start' declaration from the map file
    @Override
    public Object visitStartDecl(MapFileParser.StartDeclContext ctx) {
        int r = Integer.parseInt(ctx.location().INT(0).getText());
        int c = Integer.parseInt(ctx.location().INT(1).getText());
        config.setStart(r, c);
        return null;
    }

    //Process the 'goal' declaration from the map file
    @Override
    public Object visitGoalDecl(MapFileParser.GoalDeclContext ctx) {
        int r = Integer.parseInt(ctx.location().INT(0).getText());
        int c = Integer.parseInt(ctx.location().INT(1).getText());
        config.setGoal(r, c);
        return null;
    }

    //Process the 'item' declaration from the map file
    @Override
    public Object visitItemDecl(MapFileParser.ItemDeclContext ctx) {
        String name = stripQuotes(ctx.STRING(0).getText());
        String message = stripQuotes(ctx.STRING(1).getText());

        List<int[]> positions = new ArrayList<>();
        for (MapFileParser.LocationContext loc : ctx.locationList().location()) {
            int r = Integer.parseInt(loc.INT(0).getText());
            int c = Integer.parseInt(loc.INT(1).getText());
            positions.add(new int[]{r, c});
        }

        config.addItem(name, positions, message);
        return null;
    }

    //Process the 'obstacle' declaration from the map file
    @Override
    public Object visitObstacleDecl(MapFileParser.ObstacleDeclContext ctx) {
        List<int[]> positions = new ArrayList<>();
        for (MapFileParser.LocationContext loc : ctx.locationList().location()) {
            int r = Integer.parseInt(loc.INT(0).getText());
            int c = Integer.parseInt(loc.INT(1).getText());
            positions.add(new int[]{r, c});
        }

        List<String> requires = new ArrayList<>();
        for (TerminalNode str : ctx.stringList().STRING()) {
            requires.add(stripQuotes(str.getText()));
        }

        config.addObstacle(positions, requires);
        return null;
    }

    //Process the 'plugin' declaration from the map file
    @Override
    public Object visitPluginDecl(MapFileParser.PluginDeclContext ctx) {
        config.addPlugin(ctx.qualifiedName().getText());
        return null;
    }

    //Process the 'script' declaration from the map file
    @Override
    public Object visitScriptDecl(MapFileParser.ScriptDeclContext ctx) {
        config.addScript(ctx.SCRIPTBLOCK().getText());
        return null;
    }

    //Utility method that remoces surrounding quotes from a string
    private String stripQuotes(String s) {
        return s.substring(1, s.length()-1);
    }

    //Returns the fully constrcuted GameConfig after the map file has been parsed
    public GameConfig getConfig() {
        return config;
    }
}
