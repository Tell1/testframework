package com.github.tellmp.test1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertArrayEquals;

public class TimeConfusionAMPMTest {

    private static final Pattern TIME = Pattern.compile("(\\d+):(\\d+)");

    @Test
    public void testGenerateAMPMTimes() throws Exception {
        String[] test1 = {"5:00", "12:00", "10:00"};
        Long[] solution1 = {300l, 720l, 600l, 1020l, 0l, 1320l};
        List<Long> testList = new ArrayList<Long>();


        for (int i = 0; i < test1.length; i++) {
            Matcher m = TIME.matcher(test1[i]);
            while (m.find()) {
                testList.add(TimeUnit.HOURS.toMinutes(Long.parseLong(m.group(1))) + Long.parseLong(m.group(2)));
            }
        }
        assertArrayEquals(TimeConfusionAMPM.generateAMPMTimes(testList).toArray(), solution1);
    }
}