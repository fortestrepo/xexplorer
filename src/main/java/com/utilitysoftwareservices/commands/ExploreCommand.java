package com.utilitysoftwareservices.commands;

/**
 * ExploreCommand
 */
public final class ExploreCommand implements Command {

    @Override
    public CommandResult execute() {
        return new PathResult();
    }

    
}