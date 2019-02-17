package com.utilitysoftwareservices.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.utilitysoftwareservices.Point;

/**
 * An implement of Explorer {@link Controller}.
 * 
 * Internally, it uses a Cartesian Coodinate Map to model the table top. Mark
 * the unit status by {@link UnitStatus}.
 * 
 */
public class CartesianMapController implements Controller {
    static final Set<Point> FIVE_SEQUARE_TABLE_TOP;

    static {
        FIVE_SEQUARE_TABLE_TOP = generateSquareSurface(5);
    }

    static Set<Point> generateSquareSurface(int width) {
        Set<Point> surface = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                surface.add(Point.of(i, j));
            }
        }
        return surface;
    }

    private Map<Point, UnitStatus> tableTop;
    private boolean explorerPlacedSuccess;
    private PathFinder pathFinder = new ShortestPathFinder();

    public CartesianMapController() {
        reset();
    }

    /**
     * place a blocker on table top at the point
     * 
     * @param position the point the blocker will be
     * 
     */
    @Override
    public void placeBlocker(Point position) {
        if (isExplorerPlacedSuccess() && getTableTop().containsKey(position)) {
            if (UnitStatus.EMPTY.equals(getTableTop().get(position))) {
                getTableTop().put(position, UnitStatus.BLOCKED);
            }
        }
    }

    /**
     * place a explorer on table to at the point
     * 
     * @param position the point the explorer will be
     * 
     */
    @Override
    public void placeExplorer(Point position) {
        if (getTableTop().containsKey(position)) {
            reset();
            getTableTop().put(position, UnitStatus.EXPLORER_ON);
            setExplorerPlacedSuccess(true);
        }
    }

    /**
     * Move a the explorer to a new position, if
     * 
     *  <ul>
     *      <li>the explorer has previous placed on the table top correctly;</li>
     *      <li>there's a valid path to the new position.</li>
     * </ul>
     * 
     * @param to the target position
     * @return the shortest path, if move successfully; otherwise return null
     */
    @Override
    public List<Point> moveExplorer(Point to) {
        if (isExplorerPlacedSuccess() && UnitStatus.EMPTY.equals(getTableTop().get(to))) {
            List<Point> path = getPathFinder().find(getTableTop(), explorer(), to);
            if (path != null) {
                getTableTop().put(explorer(), UnitStatus.EMPTY);
                getTableTop().put(to, UnitStatus.EXPLORER_ON);
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * report the location of explorer on the table top
     * 
     * @return location where the explorer are on
     */
    @Override
    public Point explorer() {
        Optional<Point> explorerPosition = tableTop.entrySet().stream()
                .filter(kv -> UnitStatus.EXPLORER_ON.equals(kv.getValue())).map(Entry::getKey).findFirst();
        return explorerPosition.isPresent() ? explorerPosition.get() : null;
    }

    /**
     * report the all locations of blockers on the table top
     * 
     * @return list of positions where blockers are on
     */
    @Override
    public Set<Point> blockers() {
        return tableTop.entrySet().stream().filter(kv -> UnitStatus.BLOCKED.equals(kv.getValue())).map(Entry::getKey).collect(Collectors.toSet());
    }

    /**
     * reset every unit on the table top to empty status
     * 
     */
    void reset() {
        setExplorerPlacedSuccess(false);
        Map<Point, UnitStatus> emptyTableTop = FIVE_SEQUARE_TABLE_TOP.stream().collect(Collectors.toMap(Function.identity(), p -> UnitStatus.EMPTY));
        setTableTop(emptyTableTop);
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

    /**
     * @return the tableTop
     * 
     */
    public Map<Point, UnitStatus> getTableTop() {
        return tableTop;
    }

    /**
     * @param tableTop the tableTop to set
     * 
     */
    protected void setTableTop(Map<Point, UnitStatus> tableTop) {
        this.tableTop = tableTop;
    }

    /**
     *  Getter of pathFinder field
     * @return
     */
    protected PathFinder getPathFinder() {
        return pathFinder;
    }

    /**
     * @param tableTop the pathFinder to set
     * 
     */
    protected void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }
    
}