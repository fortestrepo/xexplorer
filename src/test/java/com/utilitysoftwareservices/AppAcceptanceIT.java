package com.utilitysoftwareservices;

import static org.junit.Assert.assertEquals;

import com.utilitysoftwareservices.commands.CommandResult;
import com.utilitysoftwareservices.controllers.CartesianMapController;
import com.utilitysoftwareservices.parsers.CommandParser;

import org.junit.Before;
import org.junit.Test;

/**
 * AppAcceptanceIT
 * 
 */
public class AppAcceptanceIT {

    private App app;

    @Before
    public void setup() {
        app = new App(new CommandParser(), new CartesianMapController());
    }

    /**
     * test the reported position is where the explorer is placed
     * 
     * Example a 
     * PLACE 0,0 
     * REPORT
     * 
     * Expected output:
     * E:(0,0) B:
     * 
     */
    @Test
    public void reportAfterExplorerPlaced() {
        app.execute("PLACE 0,0");
        CommandResult result = app.execute("REPORT");


        assertEquals("should report the explorere position correctly and no blockers.", 
            "E:(0,0) B: ",
            result.toString());
    }
}