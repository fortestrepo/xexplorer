package com.utilitysoftwareservices.commands;

/**
 * One type of {@link CommandResult} which has nothing to report
 * 
 */
public final class NoneResult extends CommandResult {

    @Override
    public void report() {
        // None result should not report anything
    }
    
}