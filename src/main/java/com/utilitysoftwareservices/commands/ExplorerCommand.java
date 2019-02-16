package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;

/**
 * ExploreCommand
 */
public final class ExplorerCommand implements Command {
    private final Point position;
    
    public ExplorerCommand(Point position) {
        this.position = position;
    }
    @Override
    public CommandResult execute() {
        return new PathResult();
    }

    /**
     * Getter of position field
     * 
     * @return position
     */
    public Point getPosition() {
        return position;
    }
}