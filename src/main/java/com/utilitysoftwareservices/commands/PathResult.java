package com.utilitysoftwareservices.commands;

import java.util.List;

import com.utilitysoftwareservices.Point;

/**
 * One type of CommandResult, which holds path of explorer movement.
 * 
 */
public final class PathResult extends CommandResult {

    private final List<Point> path;


    public PathResult(List<Point> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        if (getPath() == null ) {
            return "";
        }
        return String.join(" ", getPath().stream().map(Point::toString).toArray(String[]::new));
    }


    /**
     * Getter of path field
     * 
     * @return
     */
    public List<Point> getPath() {
        return this.path;
    }    

}