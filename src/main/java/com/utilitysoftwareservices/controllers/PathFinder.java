package com.utilitysoftwareservices.controllers;

import java.util.List;
import java.util.Map;

import com.utilitysoftwareservices.Point;

/**
 * PathFinder
 * 
 */
public interface PathFinder {

    /**
     * Find the path to the target position from the explorer's current position
     * 
     * @param tableTopWithStatus all units of the table top with statuses.
     * @param target the destination position.
     * @return the path from explorer's current position to target position. Return empty list when there's way to reach the target.
     */
    List<Point> find(Map<Point, UnitStatus> tableTopWithStatus, Point target);
}