package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.utilitysoftwareservices.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * BlockCommandTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class BlockCommandTest extends CommandTestBase {

    @Test
    public void testExecute() {
        Point position = Point.of(0, 0);
        BlockCommand command = new BlockCommand(controller, position);
        CommandResult result = command.execute();

        assertEquals("BlockCommand returns NoneResult.", NoneResult.class, result.getClass());
        verify(controller).placeBlocker(position);
    }
}