package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * PlaceCommand
 */
public final class PlaceCommand implements Command {

    private final Point position;
    private final Controller controller;

    public PlaceCommand(Controller controller, Point position) {
        this.controller = controller;
        this.position = position;
    }

    @Override
    public CommandResult execute() {
        controller.reset();
        controller.placeExplorer(position);
        return new NoneResult();
    }
}