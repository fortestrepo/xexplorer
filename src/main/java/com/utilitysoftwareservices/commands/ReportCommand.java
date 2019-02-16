package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.controllers.Controller;

/**
 * ReportCommand responds REPORT command
 * 
 * Report the explorer and blockers positions.
 * 
 */
public class ReportCommand implements Command {
    private Controller controller;

    /**
     * Create instance of ReportCommand
     * 
     * @param controller explorer controller, see {@link Controller}
     */
    public ReportCommand(Controller controller) {
        this.controller = controller;
    }

    /**
     * Report the explorer and blockers positions
     * 
     * @return execution result
     */
    @Override
    public CommandResult execute() {
        return new PositionsResult(controller.explorer(), controller.blockers());
    }

    
}