package com.utilitysoftwareservices.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
        assertEquals("Empty set of blockers returns empty string", "", result2.blockersToString());
    }

    @Test
    public void testMultipleBlokersToString() {
        Point blocker1 = Point.of(1, 1);
        Point blocker2 = Point.of(2, 2);
        Set<Point> blockers = new HashSet<>(Arrays.asList(blocker1, blocker2));
        String expectedString1 = String.format("%s %s", blocker1, blocker2);
        String expectedString2 = String.format("%s %s", blocker2, blocker1);
        PositionsResult result = new PositionsResult(null, blockers);

        // the Set has no guarantee of keeping the order, so need to assert all possible combination
        assertThat("Multiple blockers' toString should concatenate the blocker strings.",
            result.blockersToString(), anyOf(is(expectedString1), is(expectedString2)));

    }
}