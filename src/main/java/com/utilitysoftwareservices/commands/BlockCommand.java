package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * BlockCommand responds BLOCK command
 * 
 * Place a blocker on the table top.
 * 
 */
public class BlockCommand implements Command {
    private final Controller controller;
    private final Point position;

    /**
     * Create instance of BlockCommand
     * 
     * @param controller explorer controller, see {@link Controller}
     * @param position where to place the blocker, see {@link Point}
     */
    public BlockCommand(Controller controller, Point position) {
        this.controller = controller;
        this.position = position;
    }

    /**
     * Place a block on table top and return the execution result
     * 
     * @return execution result
     */
    @Override
    public CommandResult execute() {
        controller.placeBlocker(position);
        return new NoneResult();
    }

    /**
     * Getter of position
     * 
     * @return position point
     */
    public Point getPosition() {
        return position;
    }
}