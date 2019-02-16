package com.utilitysoftwareservices.commands;

import java.util.Set;

import com.utilitysoftwareservices.Point;

/**
 * One type of CommandResult, which holds positions of explorer and blockers
 * 
 */
public final class PositionsResult extends CommandResult {

    private final Point explorer;
    private final Set<Point> blockers;


    public PositionsResult(Point explorer, Set<Point> blockers) {
        this.explorer = explorer;
        this.blockers = blockers;
    }

    /**
     * Getter of explorer field
     * 
     * @return position of explorer
     */
    public Point getExplorer() {
        return this.explorer;
    }


    /**
     * Getter of blockers field
     * 
     * @return positions of blockers
     */
    public Set<Point> getBlockers() {
        return this.blockers;
    }

    @Override
    public String toString() {
        return String.format("E:%s B: %s", getExplorer(), blockersToString());
    }

    String blockersToString() {
        if (getBlockers() == null || getBlockers().isEmpty()) {
            return "";
        }
        return String.join(" ", getBlockers().stream().map(Point::toString).toArray(String[]::new));
    }
}