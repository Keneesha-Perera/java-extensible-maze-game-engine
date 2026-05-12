package edu.curtin.game;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.lang.reflect.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

import edu.curtin.game.api.Plugin;
import org.python.util.PythonInterpreter;

//The main Game class that handles the execution of the entire game
public class Game
{
    //Inner class that holds the current locale and resourcebundle
    private static class LocaleHolder
    {
        private Locale locale;
        private ResourceBundle messages;

        private LocaleHolder(Locale locale, ResourceBundle messages)
        {
            this.locale = locale;
            this.messages = messages;
        }
    }

    //Inner class for maintaining the mutable in-game date
    private static class DateHolder
    {
        private LocalDate date;

        private DateHolder(LocalDate date)
        {
            this.date = date;
        }
    }

    //Method to clear the console screen to provide a clean display for each game frame
    private static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.println("\n\n\n"); 
        System.out.flush();
    }

    //The main entry point of the game
    public static void main(String[] args)
    {
        //Ensure that the map file path is porvided
        if (args.length == 0)
        {
            System.err.println("Usage: ./gradlew :core:run --args=\"../input.utf8.map\"");
            System.exit(1);
        }

        try
        {
            //Determine the correct encoding based on file extension
            Path filePath = Path.of(args[0]);
            String fileName = filePath.toString();
            Charset charset;
            if (fileName.endsWith(".utf8.map"))
            {
                charset = StandardCharsets.UTF_8;
            }
            else if (fileName.endsWith(".utf16.map"))
            {
                charset = StandardCharsets.UTF_16;
            }
            else if (fileName.endsWith(".utf32.map"))
            {
                charset = Charset.forName("UTF-32");
            }
            else
            {
                throw new IllegalArgumentException("Unknown encoding: file must end with .utf8.map, .utf16.map, or .utf32.map");
            }

            //Read and parse the input map file using ANTLR
            String input = Files.readString(filePath, charset);
            MapFileLexer lexer = new MapFileLexer(CharStreams.fromString(input));
            MapFileParser parser = new MapFileParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.file();

            MapFileVisitorImpl visitor = new MapFileVisitorImpl();
            visitor.visit(tree);
            GameConfig config = visitor.getConfig();

            //Initializd the game map
            GameMap gameMap = new GameMap(config);

            //Load localized messages based on system locale
            Locale systemLocale = Locale.getDefault();
            ResourceBundle messages;
            try
            {
                messages = ResourceBundle.getBundle("Messages", systemLocale);
            }
            catch (MissingResourceException e)
            {
                messages = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }
            LocaleHolder localeHolder = new LocaleHolder(systemLocale, messages);
            gameMap.setMessages(localeHolder.messages);

            //Initialize in-game date and date format
            DateHolder gameDate = new DateHolder(LocalDate.now());
            final DateTimeFormatter[] dateFormatter = {
                DateTimeFormatter.ofPattern(localeHolder.messages.getString("date.format"), localeHolder.locale)
            };

            //Store any outputs that need to be displayed after the map
            List<String> postMapMessages = new ArrayList<>();

            //Load and initialize plugins dynamically using reflection
            List<Plugin> activePlugins = new ArrayList<>();
            for (String pluginClassName : config.getPlugins())
            {
                try
                {
                    //Dynamically load and instantiate plugin class
                    Class<?> clazz = Class.forName(pluginClassName);
                    Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                    plugin.initialize(gameMap);

                    //Try setting localization messages on the plugin
                    try
                    {
                        Method setMessagesMethod = clazz.getMethod("setMessages", ResourceBundle.class);
                        setMessagesMethod.invoke(plugin, localeHolder.messages);
                    }
                    catch (NoSuchMethodException ignored)
                    {
                    }
                    catch (IllegalAccessException | InvocationTargetException e)
                    {
                        System.err.println("Failed to set messages for plugin: " + pluginClassName);
                    }

                    activePlugins.add(plugin);
                    System.out.println(localeHolder.messages.getString("plugin.loaded") + ": " + pluginClassName);
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                       | InvocationTargetException | NoSuchMethodException e)
                {
                    System.err.println(localeHolder.messages.getString("plugin.failed") + ": " + pluginClassName);
                }
            }

            //Execute any embedded Python scripts dynamically
            for (String script : config.getScripts())
            {
                String scriptText = script;
                if (scriptText.startsWith("!{") && scriptText.endsWith("}"))
                {
                    //Remove scripts block markers
                    scriptText = scriptText.substring(2, scriptText.length() - 1);
                }

                final String finalScriptText = scriptText;

                //Capture the Python script's output
                String output = captureOutput(() -> {
                    try (PythonInterpreter pyInterp = new PythonInterpreter())
                    {
                        pyInterp.set("api", gameMap);
                        pyInterp.exec(finalScriptText);
                        pyInterp.exec("execute(api)");
                    }
                });


                if (!output.isEmpty())
                {
                    postMapMessages.add(output);
                }

                System.out.println(localeHolder.messages.getString("script.executed"));
            }

            //Display the initial map and player status
            clearConsole();
            gameMap.printMap();
            gameMap.printPlayerStatus();
            System.out.println(localeHolder.messages.getString("current_date") + ": " + gameDate.date.format(dateFormatter[0]));
            System.out.println("\n" + localeHolder.messages.getString("available.commands"));


            //Print any initial messages
            List<String> mapMessages = gameMap.getPostMapMessages();
            for (String msg : mapMessages)
            {
                System.out.println(msg);
            }

            for (String msg : postMapMessages)
            {
                System.out.println(msg);
            }
            postMapMessages.clear();

            //Main game loop: handle commands, movement, plugins, and locale changes
            try (Scanner scanner = new Scanner(System.in))
            {

                //Display loaded plugins
                String pluginNames = activePlugins.stream()
                        .map(Plugin::getName)
                        .filter(Objects::nonNull)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(localeHolder.messages.getString("none"));
                System.out.println(localeHolder.messages.getString("plugins.loaded") + ": " + pluginNames);

                int daysElapsed = 0; //Trackes how many in-game days have passed

                while (true)
                {
                    System.out.print("> ");
                    if (!scanner.hasNextLine())
                    {
                        break;
                    }
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty())
                    {
                        continue;
                    }

                    String cmd = line.split("\\s+")[0].toLowerCase();

                    //locale change command
                    if (cmd.equals("locale"))
                    {
                        System.out.print(localeHolder.messages.getString("enter.locale") + ": ");
                        String langTag = scanner.nextLine().trim();
                        Locale requestedLocale = Locale.forLanguageTag(langTag);

                        try
                        {
                            ResourceBundle newBundle = ResourceBundle.getBundle("Messages", requestedLocale);
                            localeHolder.locale = requestedLocale;
                            localeHolder.messages = newBundle;
                            gameMap.setMessages(localeHolder.messages);

                            dateFormatter[0] = DateTimeFormatter.ofPattern(localeHolder.messages.getString("date.format"), localeHolder.locale);

                            //Update locale in all active plugins
                            for (Plugin plugin : activePlugins)
                            {
                                try
                                {
                                    Method setMessagesMethod = plugin.getClass().getMethod("setMessages", ResourceBundle.class);
                                    setMessagesMethod.invoke(plugin, localeHolder.messages);
                                }
                                catch (NoSuchMethodException ignored)
                                {
                                }
                                catch (IllegalAccessException | InvocationTargetException e)
                                {
                                    System.err.println("Failed to update messages for plugin: " + plugin.getName());
                                }
                            }

                            System.out.println(localeHolder.messages.getString("locale.changed") + ": " + requestedLocale.toLanguageTag());
                        }
                        catch (MissingResourceException e)
                        {
                            //Fallback to English if translation not found
                            System.out.println("No translation found for: " + langTag + ". Using default locale: " + Locale.ENGLISH.toLanguageTag());
                            localeHolder.locale = Locale.ENGLISH;
                            localeHolder.messages = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
                            gameMap.setMessages(localeHolder.messages);
                            dateFormatter[0] = DateTimeFormatter.ofPattern(localeHolder.messages.getString("date.format"), localeHolder.locale);
                        }
                        continue;
                    }

                    //Movement shortcuts (WASD support)
                    if (cmd.equals("w")) {
                        cmd = "up";
                    }
                    else if (cmd.equals("s")){
                        cmd = "down";
                    }
                    else if (cmd.equals("a")){
                        cmd = "left";
                    }
                    else if (cmd.equals("d")) {
                        cmd = "right";
                    }

                    //Exit game
                    if (cmd.equals("quit") || cmd.equals("exit"))
                    {
                        System.out.println(localeHolder.messages.getString("quitting"));
                        break;
                    }

                    //Check if command matches any plugin name
                    boolean handledByPlugin = false;
                    String pluginMessages = "";

                    for (Plugin plugin : activePlugins)
                    {
                        String pluginName = plugin.getName();
                        if (pluginName != null && pluginName.equalsIgnoreCase(cmd))
                        {
                            handledByPlugin = true;
                            // Capture only the plugin's output while it runs
                            pluginMessages = captureOutput(plugin::onAction);
                            break;
                        }
                    }

                    if (!pluginMessages.isEmpty())
                    {
                        postMapMessages.add(pluginMessages);
                    }

                    //If a plugin handled the command, just refresh map view
                    if (handledByPlugin)
                    {
                        clearConsole();
                        gameMap.printMap();
                        gameMap.printPlayerStatus();
                        System.out.println(localeHolder.messages.getString("current_date") + ": " + gameDate.date.format(dateFormatter[0]));
                        System.out.println("\n" + localeHolder.messages.getString("available.commands"));

                        mapMessages = gameMap.getPostMapMessages();
                        for (String msg : mapMessages)
                        {
                            System.out.println(msg);
                        }

                        for (String msg : postMapMessages)
                        {
                            System.out.println(msg);
                        }
                        postMapMessages.clear();
                        continue;
                    }

                    //Handle player movement
                    String result = gameMap.movePlayer(cmd);

                    //Advance in-game day if movement is sucessful
                    if (result.contains(localeHolder.messages.getString("move_success")))
                    {
                        daysElapsed++;
                        gameDate.date = gameDate.date.plusDays(1);
                    }

                    //Check if the player is surrounded by obstacles
                    int row = gameMap.getPlayerLocation()[0];
                    int col = gameMap.getPlayerLocation()[1];
                    boolean surrounded = true;
                    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
                    for (int[] d : directions)
                    {
                        int r = row + d[0];
                        int c = col + d[1];
                        if (r >= 0 && r < gameMap.getRows() && c >= 0 && c < gameMap.getCols())
                        {
                            if (!gameMap.hasObstacle(r, c))
                            {
                                surrounded = false;
                                break;
                            }
                        }
                    }
                    if (surrounded)
                    {
                        postMapMessages.add(localeHolder.messages.getString("player.trapped"));
                    }

                    if (!result.isEmpty())
                    {
                        postMapMessages.add(result);
                    }

                    //Refresh the game map display
                    clearConsole();
                    gameMap.printMap();
                    gameMap.printPlayerStatus();
                    System.out.println(localeHolder.messages.getString("current_date") + ": " + gameDate.date.format(dateFormatter[0]));
                    System.out.println("\n" + localeHolder.messages.getString("available.commands"));

                    mapMessages = gameMap.getPostMapMessages();
                    for (String msg : mapMessages)
                    {
                        System.out.println(msg);
                    }

                    for (String msg : postMapMessages)
                    {
                        System.out.println(msg);
                    }
                    postMapMessages.clear();

                    //Check if player reached the goal
                    if (gameMap.isAtGoal())
                    {
                        System.out.println(localeHolder.messages.getString("goal.reached"));
                        break;
                    }
                }

                //Game over summary
                System.out.println(localeHolder.messages.getString("days_elapsed") + ": " + daysElapsed);
            }
        }
        catch (IOException e)
        {
            System.err.println("I/O error occurred: " + e.getMessage());
        }
    }

    /**
     * Captures the console output of a given Runnable task
     * 
     * PMD Warning Suppressions:
     * -PMD.ClosureResource: The PrintStream is closed manually in the finally block
     * -PMD.UseTryWithResources: Using try-with-resources would close the stream before restoring System.out
     * 
     * This pattern is safe and necessary when redirecting System.out, so suppression is reasonable.
     */
    @SuppressWarnings({"PMD.CloseResource", "PMD.UseTryWithResources"})
    private static String captureOutput(Runnable task) {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(buffer, true);
        PrintStream originalOut = System.out;

        try {
            System.setOut(ps);
            task.run();
            System.out.flush();
        } finally {
            System.setOut(originalOut);
            ps.close(); //Explicitly close after restoring System.out
        }

        return buffer.toString().trim();
    }
}
