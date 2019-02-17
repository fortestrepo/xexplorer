package com.utilitysoftwareservices.commands;

import java.util.List;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * ExplorerCommand responds EXPLORER command
 * 
 * Move a the explorer to a new position.
 * 
 */
public final class ExplorerCommand implements Command {
    private final Controller controller;
    private final Point position;

    /**
     * Create instance of BlockCommand
     * 
     * @param controller explorer controller, see {@link Controller}
     * @param position where to place the blocker, see {@link Point}
     */
    public ExplorerCommand(Controller controller, Point position) {
        this.controller = controller;
        this.position = position;
    }
    
    /**
     * Move a the explorer to a new position, if
     * 
     *  <ul>
     *      <li>the explorer has previous placed on the table top correctly;</li>
     *      <li>there's a valid path to the new position.</li>
     * </ul>
     * 
     * @return execution result; returns {@link PathResult} if a valid path presents, otherwise return {@link NoneResult}
     */
    @Override
    public CommandResult execute() {
        List<Point> path = controller.moveExplorer(position);
        if (path != null) {
            return new PathResult(path);
        } else {
            return new NoneResult();
        }
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