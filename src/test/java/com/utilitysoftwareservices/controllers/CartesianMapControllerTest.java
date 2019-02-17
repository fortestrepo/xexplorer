package com.utilitysoftwareservices.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.utilitysoftwareservices.Point;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.*;

/**
 * CartesianMapControllerTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class CartesianMapControllerTest {
    
    private CartesianMapController controller;
    private PathFinder pathFinder;

    @Before
    public void setup() {
        controller = new CartesianMapController();
        pathFinder = mock(PathFinder.class);
        controller.setPathFinder(pathFinder);
    }

    @Test
    public void placeExplorerAtValidPosition() {
        Point explorerPosition = Point.of(1, 1);
        controller.placeExplorer(explorerPosition);

        assertEquals("Reported position of explorer should be where it was placed.", explorerPosition, controller.explorer());
        assertTrue("Should set explorere placed success flag", controller.isExplorerPlacedSuccess());
    }

    @Test
    public void placeExploreAtWrongPlace() {
        Point wrongPlace = Point.of(5, 5);
        controller.placeExplorer(wrongPlace);

        assertNull("Report null when the explorer is placed at wrong position.", controller.explorer());
        assertFalse("Explorere placed success flag should remain false", controller.isExplorerPlacedSuccess());
    }


    @Test
    public void placeBlockerAtValidPosition() {
        Point explorerPosition = Point.of(1, 1);
        controller.placeExplorer(explorerPosition);
        Point blocker = Point.of(2, 2);

        controller.placeBlocker(blocker);

        assertEquals("Blocker should be placed correctly.", new HashSet<>(Arrays.asList(blocker)), controller.blockers());
    }

    @Test
    public void placeBlockerAtWrongPlace() {
        Point explorerPosition = Point.of(1, 1);
        controller.placeExplorer(explorerPosition);
        Point blocker = Point.of(5, 5);

        controller.placeBlocker(blocker);

        assertFalse("Wrong position of blocker should not be placed", controller.blockers().contains(blocker));
    }

    @Test
    public void placeBlockerBeforeExplorerIsIgnored() {
        Point blocker = Point.of(2, 2);

        controller.placeBlocker(blocker);

        assertFalse("Wrong position of blocker should not be placed", controller.blockers().contains(blocker));
    }

    @Test
    public void moveExplorerToValidPosition() {
        Point explorerPosition = Point.of(1, 1);
        Point moveTo = Point.of(2, 1);
        controller.placeExplorer(explorerPosition);
        List<Point> expectedPath = Arrays.asList(Point.of(1, 1), Point.of(2, 1));
        when(pathFinder.find(any(), any(), any())).thenReturn(expectedPath);

        List<Point> actualPath = controller.moveExplorer(moveTo);

        assertEquals("Should return path as expected.", expectedPath, actualPath);
        assertEquals("The new position of explorere should be the one want to.", moveTo, controller.explorer());
    }

    @Test
    public void moveExplorerToWrongPlace() {
        Point explorerPosition = Point.of(1, 1);
        Point moveTo = Point.of(5, 5);
        controller.placeExplorer(explorerPosition);
        when(pathFinder.find(any(), any(), any())).thenReturn(null);

        List<Point> actualPath = controller.moveExplorer(moveTo);

        assertNull("Should return null when trying to move to a wrong place.", actualPath);
    }

    @Test
    public void moveExplorerBeforePlaced() {
        Point moveTo = Point.of(2, 1);
        List<Point> expectedPath = Arrays.asList(Point.of(1, 1), Point.of(2, 1));
        when(pathFinder.find(any(), any(), any())).thenReturn(expectedPath);

        List<Point> actualPath = controller.moveExplorer(moveTo);

        assertNull("Should return null when move before placing.", actualPath);
    }
}