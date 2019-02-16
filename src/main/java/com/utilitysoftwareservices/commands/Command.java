package com.utilitysoftwareservices.commands;

/**
 * Command interface
 * 
 */
public interface Command {

    /**
     *  execute the command
     * 
     * @return result of the execution
     */
    CommandResult execute();
}