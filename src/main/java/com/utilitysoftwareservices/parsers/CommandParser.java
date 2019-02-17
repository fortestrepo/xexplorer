package com.utilitysoftwareservices.parsers;

import com.utilitysoftwareservices.Point;
import com.utilitysoftwareservices.commands.BlockCommand;
import com.utilitysoftwareservices.commands.Command;
import com.utilitysoftwareservices.commands.ExplorerCommand;
import com.utilitysoftwareservices.commands.NoopCommand;
import com.utilitysoftwareservices.commands.PlaceCommand;
import com.utilitysoftwareservices.commands.ReportCommand;
import com.utilitysoftwareservices.controllers.Controller;

/**
 * CommandParser parses text to command
 *
 */
public final class CommandParser {
    /**
     * Place command prefix
     * 
     */
    public final static String PLACE_COMMAND = "PLACE";
    /**
     * Block command prefix
     * 
     */
    public final static String BLOCK_COMMAND = "BLOCK";
    /**
     * Report command prefix
     * 
     */
    public final static String REPORT_COMMAND = "REPORT";
    /**
     * Explorer command prefix
     * 
     */
    public final static String EXPLORER_COMMAND = "EXPLORER";

    private Controller controller;

    /**
     * parse text to command
     * 
     * Valid commands:
     * <ul>
     *  <li>{@link PlaceCommand}</li>
     *  <li>{@link BlockCommand}</li>
     *  <li>{@link ReportCommand}</li>
     *  <li>{@link ExplorerCommand}</li>
     * </ul>
     * Any invalid texts are resolved to {@link NoopCommand}.
     * 
     * @param commandLine command text
     * @return according {@link Command}
     */
    public Command parse(String commandLine) {
        if (commandLine != null) {
            if (commandLine.startsWith(PLACE_COMMAND)) {
                Point position = parsePosition(positionPart(commandLine));
                if (position != null) {
                    return new PlaceCommand(getController(), position);
                }
            } else if (commandLine.startsWith(BLOCK_COMMAND)) {
                Point position = parsePosition(positionPart(commandLine));
                if (position != null) {
                    return new BlockCommand(getController(), position);
                }
            } else if (commandLine.startsWith(REPORT_COMMAND)) {
                return new ReportCommand(getController());
            } else if (commandLine.startsWith(EXPLORER_COMMAND)) {
                Point position = parsePosition(positionPart(commandLine));
                if (position != null) {
                    return new ExplorerCommand(getController(), position);
                }
            }
        }

        return new NoopCommand();
    }


    private String positionPart(String commandLine) {
        String[] parts = commandLine.split(" ");
        if (parts.length >= 2) {
            return parts[1];
        } else {
            return "";
        }
    }

    private Point parsePosition(String position) {
        if (position == null) {
            return null;
        }
        String[] parts = position.split(",");
        if (parts.length == 2) {
            try {
                return Point.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    
    /**
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}