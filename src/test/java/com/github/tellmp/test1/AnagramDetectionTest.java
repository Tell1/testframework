package com.github.tellmp.test1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnagramDetectionTest {

    @Test
    public void testCheckAnagram() throws Exception {
        assertEquals(AnagramDetection.checkAnagram(null, null), false);
        assertEquals(AnagramDetection.checkAnagram("nAdnBndAndBdaB", null), false);
        assertEquals(AnagramDetection.checkAnagram(null, "dAn"), false);
        assertEquals(AnagramDetection.checkAnagram("nAdnBndAndBdaB", "dAn"), true);
        assertEquals(AnagramDetection.checkAnagram("AcadAbRa", "cAda"), true);
    }

    @Test
    public void testCountAnagrams() throws Exception {
        assertEquals(AnagramDetection.countAnagrams(null, null), 0);
        assertEquals(AnagramDetection.countAnagrams("nAdnBndAndBdaB", null), 0);
        assertEquals(AnagramDetection.countAnagrams(null, "dAn"), 0);
        assertEquals(AnagramDetection.countAnagrams("AdnBndAndBdaBn", "dAn"), 4);
        assertEquals(AnagramDetection.countAnagrams("AbrAcadAbRa", "cAda"), 2);
    }

}