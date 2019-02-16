package com.utilitysoftwareservices.commands;

/**
 * BlockCommand
 */
public class BlockCommand implements Command {

    @Override
    public CommandResult execute() {

        return new NoneResult();
    }

    
}