package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.utilitysoftwareservices.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * ExplorerCommandTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ExplorerCommandTest extends CommandTestBase {
    @Test
    public void testExecute() {
        Point position = Point.of(0, 0);
        List<Point> expectedPath = Arrays.asList(Point.of(1, 0), Point.of(0, 0));
        ExplorerCommand command = new ExplorerCommand(controller, position);
        when(controller.moveExplorer(position)).thenReturn(expectedPath);
        CommandResult result = command.execute();

        assertEquals("ExplorerCommand returns PathResult when there's a valid path.", PathResult.class, result.getClass());
        assertEquals("The result should hold correct path.", expectedPath, ((PathResult)result).getPath());
    }

    @Test
    public void testExecuteReturnNoneResult() {
        Point position = Point.of(0, 0);
        ExplorerCommand command = new ExplorerCommand(controller, position);
        when(controller.moveExplorer(position)).thenReturn(null);
        CommandResult result = command.execute();

        assertEquals("ExplorerCommand returns NoneResult when there's no path.", NoneResult.class, result.getClass());
    }
    
}