package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.controllers.Controller;

/**
 * ReportCommand
 */
public class ReportCommand implements Command {
    private Controller controller;

    public ReportCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public CommandResult execute() {
        return new PositionsResult(controller.explorer(), controller.blockers());
    }

    
}