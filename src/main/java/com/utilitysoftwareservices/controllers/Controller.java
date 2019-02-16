package com.utilitysoftwareservices.controllers;

import java.util.List;
import java.util.Set;
import com.utilitysoftwareservices.Point;

/**
 * Controller interface defines command behaviours for an explorer controller
 * 
 */
public interface Controller {
    /**
     * reset the every to initial states and initialise the table to coordinate map
     * 
     */
    void reset();

    /**
     * place a blocker on table top at the point
     * 
     * @param position the point the blocker will be
     * 
     */
    void placeBlocker(Point position);

    /**
     * place a explorer on table to at the point
     * 
     * @param position the point the explorer will be
     * 
     */
    void placeExplorer(Point position);

    /**
     * move the explorer from one position to another
     * 
     * @param from the original positon 
     * @param to the target position
     * @return the shortest path
     */
    List<Point> moveExplorer(Point from, Point to);

    /**
     * report the location of explorer on the table top
     * 
     * @return location where the explorer are on
     */
    Point explorer();

    /**
     * report the all locations of blockers on the table top
     * 
     * @return list of positions where blockers are on
     */
    Set<Point> blockers();
}