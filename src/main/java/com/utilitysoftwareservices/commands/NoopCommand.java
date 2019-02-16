package com.utilitysoftwareservices.commands;

/**
 * NoopCommand
 */
public class NoopCommand implements Command {

    @Override
    public CommandResult execute() {
        return new NoneResult();
    }

    
}