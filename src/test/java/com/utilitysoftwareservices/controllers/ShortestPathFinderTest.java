package com.utilitysoftwareservices.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.utilitysoftwareservices.Point;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * ShortestPahtFinderTest
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ShortestPathFinderTest {

    private ShortestPathFinder pathFinder;
    private ShortestPathFinder.Graph graph;

    @Before
    public void setup() {
        pathFinder = new ShortestPathFinder();
        graph = mock(ShortestPathFinder.Graph.class);
        pathFinder.graph = graph;
    }

    @Test
    public void testFindValidPath() {
        Point source = Point.of(0, 0);
        Point target = Point.of(0, 1);
        ShortestPathFinder.Node sourceNode = mock(ShortestPathFinder.Node.class);
        ShortestPathFinder.Node targetNode = mock(ShortestPathFinder.Node.class);
        List<Point> expectedPointPath = Arrays.asList(source, target);
        List<ShortestPathFinder.Node> expectedNodePath = expectedPointPath.stream().map(ShortestPathFinder.Node::new).collect(Collectors.toList());
        when(graph.find(source)).thenReturn(Optional.of(sourceNode));
        when(graph.find(target)).thenReturn(Optional.of(targetNode));
        doAnswer(new CallsRealMethods()).when(graph).calculateShortestPathFromSource(sourceNode);
        when(targetNode.getShortestPath()).thenReturn(expectedNodePath);

        List<Point> actualPath = pathFinder.find(allEmptyMap(), source, target);

        assertEquals("Should return a path from source to target.", expectedPointPath, actualPath);
    }

    @Test
    public void testNullPathWhenCannotReachTarget() {
        Point source = Point.of(0, 0);
        Point target = Point.of(0, 1);
        ShortestPathFinder.Node sourceNode = mock(ShortestPathFinder.Node.class);
        ShortestPathFinder.Node targetNode = mock(ShortestPathFinder.Node.class);
        when(graph.find(any())).thenReturn(Optional.of(sourceNode));
        when(graph.find(any())).thenReturn(Optional.of(targetNode));
        doAnswer(new CallsRealMethods()).when(graph).calculateShortestPathFromSource(sourceNode);
        when(targetNode.getShortestPath()).thenReturn(null);

        List<Point> actualPath = pathFinder.find(allEmptyMap(), source, target);

        assertNull("Should return null when the target is not reachable.", actualPath);
    }

    @Test
    public void tetNullPathWhenSourceAndTargetAreInvalid() {

        when(graph.find(any())).thenReturn(Optional.empty());
        when(graph.find(any())).thenReturn(Optional.empty());


        List<Point> actualPath = pathFinder.find(allEmptyMap(), null, null);

        assertNull("Should return null when the source and target are invalid.", actualPath);
    }

    @Test
    public void testFindValidPathWithGraph() {
        Point source = Point.of(0, 0);
        Point target = Point.of(1, 0);
        pathFinder.graph = new ShortestPathFinder.Graph();
        List<Point> expectedPointPath = Arrays.asList(source, target);


        List<Point> actualPath = pathFinder.find(allEmptyMap(), source, target);

        assertEquals("Should return a path from source to target.", expectedPointPath, actualPath);
    }

    private Map<Point, UnitStatus> allEmptyMap() {
        return CartesianMapController.FIVE_SEQUARE_TABLE_TOP.stream().collect(Collectors.toMap(Function.identity(), p -> UnitStatus.EMPTY));
    }
    
}