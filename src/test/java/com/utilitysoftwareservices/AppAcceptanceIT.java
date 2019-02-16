package com.utilitysoftwareservices;

import static org.junit.Assert.*;

import com.utilitysoftwareservices.commands.Command;
import com.utilitysoftwareservices.commands.CommandResult;
import com.utilitysoftwareservices.commands.PlaceCommand;
import com.utilitysoftwareservices.commands.PositionsResult;
import com.utilitysoftwareservices.commands.ReportCommand;

import org.junit.Test;

/**
 * AppAcceptanceIT
 * 
 */
public class AppAcceptanceIT {

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
        Command placeAtOrigin = new PlaceCommand();
        Command reportCommand = new ReportCommand();
        placeAtOrigin.execute();
        CommandResult result = reportCommand.execute();

        assertTrue("Report command should return PositionResult.", result instanceof PositionsResult);
    }
}