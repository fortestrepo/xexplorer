package com.utilitysoftwareservices.commands;

/**
 * No op Command, this command does nothing and return {@link NoneResult}
 * 
 */
public class NoopCommand implements Command {

    @Override
    public CommandResult execute() {
        return new NoneResult();
    }

    
}