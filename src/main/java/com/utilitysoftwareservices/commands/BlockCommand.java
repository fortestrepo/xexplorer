package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * BlockCommand
 */
public class BlockCommand implements Command {
    private final Controller controller;
    private final Point position;

    public BlockCommand(Controller controller, Point position) {
        this.controller = controller;
        this.position = position;
    }

    @Override
    public CommandResult execute() {
        controller.placeBlocker(position);
        return new NoneResult();
    }

    
}