package com.utilitysoftwareservices;

import com.utilitysoftwareservices.commands.CommandResult;
import com.utilitysoftwareservices.controllers.Controller;
import com.utilitysoftwareservices.parsers.CommandParser;

/**
 * Hello world!
 *
 */
public class App {
    private CommandParser commandParser;

    public App(CommandParser commandParser, Controller controller) {
        this.commandParser = commandParser;
        this.commandParser.setController(controller);
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    

    CommandResult execute(String commandLine) {
        return commandParser.parse(commandLine).execute();
    }
}
