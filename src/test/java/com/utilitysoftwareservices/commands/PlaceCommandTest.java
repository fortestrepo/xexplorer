package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.utilitysoftwareservices.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * PlaceCommandTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest extends CommandTestBase {

    @Test
    public void testExecute() {
        Point position = Point.of(0, 0);
        PlaceCommand command = new PlaceCommand(controller, position);
        CommandResult result = command.execute();

        assertEquals("PlaceCommand returns NoneResult.", NoneResult.class, result.getClass());
        verify(controller).placeExplorer(position);
    }

}