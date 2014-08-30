package com.github.tellmp.test1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpiralTest {

    @Test
    public void testCheckBoundaries() throws Exception {
        int[][] arr = new int[5][5];
        assertEquals(Spiral.checkBoundaries(arr, 0, 0), true);
        assertEquals(Spiral.checkBoundaries(arr, -1, 0), false);
        assertEquals(Spiral.checkBoundaries(arr, 0, -1), false);
        assertEquals(Spiral.checkBoundaries(arr, -1, -1), false);
        assertEquals(Spiral.checkBoundaries(arr, 0, 4), true);
        assertEquals(Spiral.checkBoundaries(arr, 4, 5), false);
        assertEquals(Spiral.checkBoundaries(arr, 5, 4), false);
        assertEquals(Spiral.checkBoundaries(arr, 5, 5), false);
        assertEquals(Spiral.checkBoundaries(arr, 4, 0), true);
        assertEquals(Spiral.checkBoundaries(arr, -1, 4), false);
        assertEquals(Spiral.checkBoundaries(arr, 4, -1), false);
        assertEquals(Spiral.checkBoundaries(arr, 4, 4), true);
    }
}