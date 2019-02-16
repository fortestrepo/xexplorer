package com.utilitysoftwareservices.commands;

/**
 * ReportCommand
 */
public class ReportCommand implements Command {

    @Override
    public CommandResult execute() {
        return new PositionsResult();
    }

    
}