package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * PlaceCommand responds PLACE command
 * 
 * Place an explorer on the table top.
 * 
 */
public final class PlaceCommand implements Command {

    private final Point position;
    private final Controller controller;

    /**
     * Create instance of PlaceCommand
     * 
     * @param controller explorer controller, see {@link Controller}
     * @param position where to place the explorer, see {@link Point}
     */
    public PlaceCommand(Controller controller, Point position) {
        this.controller = controller;
        this.position = position;
    }

    /**
     * Reset the table top, place an explorer on table top and return the execution result
     * 
     * @return execution result
     */

    @Override
    public CommandResult execute() {
        controller.placeExplorer(position);
        return new NoneResult();
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