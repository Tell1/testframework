package com.github.tellmp.test1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BalancedBracketsTest {

    @Test
    public void testIsBalanced() throws Exception {
        assertEquals(BalancedBrackets.isBalanced(null), true);
        assertEquals(BalancedBrackets.isBalanced("()[]{}(([])){[()][]}"), true);
        assertEquals(BalancedBrackets.isBalanced("())[]{}"), false);
        assertEquals(BalancedBrackets.isBalanced("[(])"), false);
    }
}