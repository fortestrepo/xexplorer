package com.utilitysoftwareservices.commands;

/**
 * CommandResult
 * 
 */
public abstract class CommandResult {

    public void report() {
        System.out.println(this);
    }
}