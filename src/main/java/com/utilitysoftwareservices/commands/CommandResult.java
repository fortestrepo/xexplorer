package com.utilitysoftwareservices.commands;

/**
 * CommandResult base class of command execution result
 * 
 */
public abstract class CommandResult {

    /**
     * report result
     * 
     */
    public void report() {
        System.out.println(this);
    }
}