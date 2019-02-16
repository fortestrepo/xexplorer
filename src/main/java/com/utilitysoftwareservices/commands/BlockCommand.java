package com.utilitysoftwareservices.commands;

import com.utilitysoftwareservices.Point;

/**
 * BlockCommand
 */
public class BlockCommand implements Command {
    private final Point position;

    public BlockCommand(Point position) {
        this.position = position;
    }

    @Override
    public CommandResult execute() {

        return new NoneResult();
    }

    
}