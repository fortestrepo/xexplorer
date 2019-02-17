package com.utilitysoftwareservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

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

    /**
     * test the explorer and two blocks are placed in correct positions
     * 
     * Example b
     * PLACE 0,0
     * BLOCK 0,1
     * BLOCK 0,2
     * REPORT
     * 
     * Expected output:
     * E:(0,0) B: (0,1) (0,2)
     * 
     */
    @Test
    public void reportAfterExplorerAndTwoBlockersPlaced() {
        app.execute("PLACE 0,0");
        app.execute("BLOCK 0,1");
        app.execute("BLOCK 0,2");
        CommandResult result = app.execute("REPORT");
        String possibleOutput1 = "E:(0,0) B: (0,1) (0,2)";
        String possibleOutput2 = "E:(0,0) B: (0,2) (0,1)";

        // the blocker positions are supported by Set, there's no guarantee of order.
        assertThat("should report the explorere position and blocker positions correctly.", 
            result.toString(),
            anyOf(is(possibleOutput1), is(possibleOutput2)));
    }

    /**
     * test place the explorer in second time remove all previous items.
     * 
     * Example c
     * PLACE 0,0
     * BLOCK 0,1
     * BLOCK 0,2
     * PLACE 0,1
     * REPORT
     * 
     * Expected output:
     * E:(0,1) B:
     * 
     */
    @Test
    public void secordPlaceRemoveAllPreviousItems() {
        app.execute("PLACE 0,0");
        app.execute("BLOCK 0,1");
        app.execute("BLOCK 0,2");
        app.execute("PLACE 0,1");
        CommandResult result = app.execute("REPORT");

        assertEquals("should report the explorere in the second time placed position and no blockers.", 
            "E:(0,1) B: ",
            result.toString());
    }

    /**
     * test move the explorer and report after the move
     * 
     * Example d
     * PLACE 0,0
     * BLOCK 0,2
     * EXPLORER 0,3
     * 
     * Expected output
     * PATH: (0,0) (0,1) (1,1) (1,2) (1,3) (0,3)
     * 
     * REPORT
     * Expected output
     * E:(0,3) B: (0,2)
     * 
     */
    @Test
    public void moveExplorerToAnotherPosition() {
        app.execute("PLACE 0,0");
        app.execute("BLOCK 0,2");
        CommandResult result = app.execute("EXPLORER 0,3");

        assertEquals("should report correct path after the explorer has moved.",
            "PATH: (0,0) (0,1) (1,1) (1,2) (1,3) (0,3)",
            result.toString());

        result = app.execute("REPORT");
        assertEquals("should report correct the new position of exlorer and blockers correctly.",
            "E:(0,3) B: (0,2)",
            result.toString());
    }
}