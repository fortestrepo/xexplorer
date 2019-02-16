package com.utilitysoftwareservices.commands;

/**
 * PlaceCommand
 */
public final class PlaceCommand implements Command{

    @Override
    public CommandResult execute() {
        return new NoneResult();
    }
}