package com.utilitysoftwareservices.controllers;

import java.util.List;
import java.util.Set;

import com.utilitysoftwareservices.Point;

/**
 * CartesianMapController
 * 
 */
public class CartesianMapController implements Controller {
    private boolean explorerPlacedSuccess;
    private Point explorerPosition;

    @Override
    public void reset() {
        setExplorerPlacedSuccess(false);
    }


    @Override
    public void placeBlocker(Point position) {

    }

    @Override
    public void placeExplorer(Point position) {
        explorerPosition = position;
        setExplorerPlacedSuccess(true);
    }

    @Override
    public List<Point> moveExplorer(Point from, Point to) {
        return null;
    }

    @Override
    public Point explorer() {
        return explorerPosition;
    }

    @Override
    public Set<Point> blockers() {
        return null;
    }

    /**
     * @return the explorerPlacedSuccess
     */
    public boolean isExplorerPlacedSuccess() {
        return explorerPlacedSuccess;
    }

    /**
     * @param explorerPlacedSuccess the explorerPlacedSuccess to set
     */
    protected void setExplorerPlacedSuccess(boolean explorerPlacedSuccess) {
        this.explorerPlacedSuccess = explorerPlacedSuccess;
    }
    
}