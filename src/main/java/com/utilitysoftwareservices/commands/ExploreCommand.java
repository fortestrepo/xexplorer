package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;

/**
 * ExploreCommand
 */
public final class ExploreCommand implements Command {
    private final Point position;
    
    public ExploreCommand(Point position) {
        this.position = position;
    }
    @Override
    public CommandResult execute() {
        return new PathResult();
    }

    
}