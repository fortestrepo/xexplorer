package com.utilitysoftwareservices.parsers;

import static org.junit.Assert.assertEquals;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.commands.BlockCommand;
import com.utilitysoftwareservices.commands.Command;
import com.utilitysoftwareservices.commands.ExplorerCommand;
import com.utilitysoftwareservices.commands.NoopCommand;
import com.utilitysoftwareservices.commands.PlaceCommand;
import com.utilitysoftwareservices.commands.ReportCommand;

import org.junit.Test;

/**
 * Unit tests for @{CommandParser}
 * 
 */
public class CommandParserTest {

    /**
     * Positive case of parsing @{PlaceCommand}.
     * 
     * Place command must follow by a correct position point.
     * 
     */
    @Test
    public void testParsePlaceCommand() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("PLACE 1,2");
        Point expectedPosition = Point.of(1, 2);

        assertEquals("Should return correct type of command (PlaceCommand)", PlaceCommand.class, command.getClass());
        assertEquals("The position should be correct.", expectedPosition, ((PlaceCommand) command).getPosition());
    }

    /**
     * Negative case of parsing @{PlaceCommand}.
     * 
     * Return @{NoopCommand} when there's no position part in the command.
     * 
     */
    @Test
    public void testParsePlaceCommandNoPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("PLACE");
        
        assertEquals("Place command must be followed by a position point. Otherwise, it returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Negative case of parsing @{PlaceCommand}.
     * 
     * Return @{NoopCommand} when place command is NOT followed by valid position point.
     * 
     */
    @Test
    public void testParsePlaceCommandWrongNumberPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("PLACE x,10");

        assertEquals("Place command is followed an illegal point, returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Positive case of parsing @{BlockCommand}.
     * 
     * Block command must follow by a correct position point.
     * 
     */
    @Test
    public void testParseBlockCommand() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("BLOCK 1,2");
        Point expectedPosition = Point.of(1, 2);

        assertEquals("Should return correct type of command (BlockCommand)", BlockCommand.class, command.getClass());
        assertEquals("The position should be correct.", expectedPosition, ((BlockCommand) command).getPosition());
    }

    /**
     * Negative case of parsing @{BlockCommand}.
     * 
     * Return @{NoopCommand} when there's no position part in the command.
     * 
     */
    @Test
    public void testParseBlockCommandNoPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("BLOCK");
        
        assertEquals("Block command must be followed by a position point. Otherwise, it returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Negative case of parsing @{BlockCommand}.
     * 
     * Return @{NoopCommand} when place command is NOT followed by valid position point.
     * 
     */
    @Test
    public void testParseBlockCommandWrongNumberPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("BLOCK x,10");

        assertEquals("Block command is followed an illegal point, returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Positive case of parsing @{ExploreCommand}.
     * 
     * Explorer command must follow by a correct position point.
     * 
     */
    @Test
    public void testParseExploreCommand() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("EXPLORER 1,2");
        Point expectedPosition = Point.of(1, 2);

        assertEquals("Should return correct type of command (ExploreCommand)", ExplorerCommand.class, command.getClass());
        assertEquals("The position should be correct.", expectedPosition, ((ExplorerCommand) command).getPosition());
    }

    /**
     * Negative case of parsing @{BlockCommand}.
     * 
     * Return @{NoopCommand} when there's no position part in the command.
     * 
     */
    @Test
    public void testParseExploreCommandNoPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("EXPLORER");
        
        assertEquals("Explorer command must be followed by a position point. Otherwise, it returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Negative case of parsing @{ExploreCommand}.
     * 
     * Return @{NoopCommand} when place command is NOT followed by valid position point.
     * 
     */
    @Test
    public void testParseExploreCommandWrongNumberPosition() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("EXPLORER x,10");

        assertEquals("Explorer command is followed an illegal point, returns NoopCommand.", NoopCommand.class, command.getClass());
    }

    /**
     * Positive case of parsing @{ReportCommand}.
     * 
     * Report command must follow by a correct position point.
     * 
     */
    @Test
    public void testParseReportCommand() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("REPORT");

        assertEquals("Should return correct type of command (ReportCommand)", ReportCommand.class, command.getClass());
    }

    /**
     * test the invalid command parse to @{NoopCommand}
     * 
     */
    @Test
    public void testInvalidCommand() {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("whatever wrong command");

        assertEquals("Should return correct type of command (NoopCommand)", NoopCommand.class, command.getClass());
    }
}