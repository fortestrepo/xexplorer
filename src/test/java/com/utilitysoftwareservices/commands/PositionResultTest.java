package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.utilitysoftwareservices.Point;

import org.junit.Test;

/**
 * PositionResultTest
 * 
 */
public class PositionResultTest {

    @Test
    public void testBlockersToStringWithNoBlockers() {
        PositionsResult result1 = new PositionsResult(null, null);
        assertEquals("Null of blockers returns empty string", "", result1.blockersToString());


        PositionsResult result2 = new PositionsResult(null, Collections.emptySet());
        assertEquals("Empty set of blockers returns empty string", "", result1.blockersToString());
    }

    @Test
    public void testMultipleBlokersToString() {
        Point blocker1 = Point.of(1,1);
        Point blocker2 = Point.of(2, 2);
        Set<Point> blockers = new LinkedHashSet<>(Arrays.asList(blocker1, blocker2));
        String expectedString = String.format("%s %s", blocker1, blocker2);
        PositionsResult result = new PositionsResult(null, blockers);

        assertEquals("Multiple blockers' toString should concatenate the blocker strings.", expectedString, result.blockersToString());
    }
}