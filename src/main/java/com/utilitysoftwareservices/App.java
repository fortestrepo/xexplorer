package com.utilitysoftwareservices;

import com.utilitysoftwareservices.commands.Command;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void executeCommand(Command command) {
        command.execute();
    }
}
