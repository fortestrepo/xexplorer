package com.utilitysoftwareservices;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.utilitysoftwareservices.commands.CommandResult;
import com.utilitysoftwareservices.controllers.CartesianMapController;
import com.utilitysoftwareservices.controllers.Controller;
import com.utilitysoftwareservices.parsers.CommandParser;

/**
 * Mars Explorer Simulator Application
 *
 */
public class App {
    private static final String DEMO_COMMAND_RESOUCE_NAME = "/demo-command.txt";
    private CommandParser commandParser;

    public App(CommandParser commandParser, Controller controller) {
        this.commandParser = commandParser;
        this.commandParser.setController(controller);
    }

    /**
     * Application starting pont
     * 
     * Usage:
     * <ul>
     * <li>run with file: java -jar xexplorer <command-file-name></li>
     * <li>run demo: java -jar xexplorer</li>
     * 
     * 
     * @param args arguments from external command
     */
    public static void main(String[] args) {
        InputStream inputStream = getCommandStream(args);
        if (inputStream != null) {
            App app = new App(new CommandParser(), new CartesianMapController());
            app.run(inputStream);
        }
        
    }

    static InputStream getCommandStream(String[] args) {
        InputStream inputStream;
        if (args != null && args.length > 0) {
            try {
                inputStream = new FileInputStream(args[0]);
            } catch (FileNotFoundException e) {
                inputStream = null;
            }
        } else {
            inputStream = App.class.getResourceAsStream(DEMO_COMMAND_RESOUCE_NAME);
        }

        return inputStream;
    }

    void run(InputStream commandStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(commandStream))) {
            while(reader.ready()) {
                String line = reader.readLine();
                execute(line).report();
           }
        } catch (IOException ioe) {
            System.out.println("Failed to read resouce.");
        }
    }

    CommandResult execute(String commandLine) {
        return commandParser.parse(commandLine).execute();
    }
}
