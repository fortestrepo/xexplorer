package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.utilitysoftwareservices.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * ReportCommandTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportCommandTest extends CommandTestBase {

    @Test
    public void testExecute() {
        Point explorerPosition = Point.of(0, 0);
        Set<Point> blockers = new HashSet<>(Arrays.asList(Point.of(1, 1), Point.of(2, 2)));
        when(controller.explorer()).thenReturn(explorerPosition);
        when(controller.blockers()).thenReturn(blockers);
        ReportCommand command = new ReportCommand(controller);
        CommandResult result = command.execute();

        assertEquals("ReportCommand returns PositionsResult.", PositionsResult.class, result.getClass());
        assertEquals("The exploer position should come from controller.", explorerPosition, ((PositionsResult)result).getExplorer());
        assertEquals("The blocker positions should come from controller.", blockers, ((PositionsResult)result).getBlockers());

    }
}