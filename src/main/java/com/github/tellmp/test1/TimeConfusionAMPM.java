package com.github.tellmp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class determines from the time displayed
 * on each watch, the actual time.
 * If it is not possible, print "Look at the sun".
 * <p/>
 * Note: This class is implemented with the assumption that the given times
 * can be in AM or PM. Therefore all the possible time representations are
 * checked in a gray-code approach. (i.e.: 5:00 12:00 10:00 -> 17:00 00:00 22:00)
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/17/14.
 */
public class TimeConfusionAMPM {

    private static final Pattern TIME = Pattern.compile("(\\d+):(\\d+)");
    private static final long AMPMDELTA = TimeUnit.HOURS.toMinutes(12);

    /**
     * This method generates all possible AM and PM times for a list of times.
     *
     * @param times list of times as Long
     * @return new list of types with all possible AM and PM times
     */
    protected static List<Long> generateAMPMTimes(List<Long> times) {
        List<Long> timesInMinutesAMPM = new ArrayList<Long>(times.size() * 2);

        timesInMinutesAMPM.addAll(times);
        for (Long l : times) {
            if (l < AMPMDELTA) {
                timesInMinutesAMPM.add(l + AMPMDELTA);
            } else {
                timesInMinutesAMPM.add(l - AMPMDELTA);
            }
        }
        return timesInMinutesAMPM;
    }

    /**
     * This method determines the right time out of the given times calculating
     * the difference between each of the times.
     *
     * @param time String of three different times where of them two are delayed
     */
    public static void rightTime(String time) {
        Matcher m = TIME.matcher(time);
        List<Long> list = new ArrayList<Long>();
        while (m.find()) {
            list.add(TimeUnit.HOURS.toMinutes(Long.parseLong(m.group(1))) + Long.parseLong(m.group(2)));
        }

        List<Long> normalizedTimes = generateAMPMTimes(list);
        int i, j, k;
        int len = normalizedTimes.size();
        for (i = 0; i < len - 2; i++) {
            for (j = i + 1; j < len - 1; j++) {
                for (k = j + 1; k < len; k++) {
                    long delta1 = Math.abs(normalizedTimes.get(i) - normalizedTimes.get(j));
                    long delta2 = Math.abs(normalizedTimes.get(j) - normalizedTimes.get(k));
                    if (delta1 == delta2) {
                        System.out.printf("Correct time is %02d:%02d, delta is %d minutes%n", TimeUnit.MINUTES.toHours(normalizedTimes.get(j)), normalizedTimes.get(j) % 60, delta1);
                        return;
                    }
                }
            }
        }
        System.out.println("Look at the sun.");
    }

    public static void main(String args[]) throws ParseException, IOException {
        System.out.println("The input begins with an integer T indicating the number of cases.\n" +
                "￼Each of the following T lines contains one test case, made up of three readings,\n" +
                "￼separated by single space characters: H1:M1 H2:M2 H3:M3\n" +
                "￼In each reading H1,H2,H3 represent the hours displayed (0 < H1,H2,H3 < 13), and\n" +
                "￼M1,M2,M3 represent the minutes displayed (0 < M1,M2,M3 < 60). If the number of\n" +
                "￼i.e.: \n\"3\n 5:00 12:00 10:00\n 11:59 12:30 1:01\n 12:00 4:00 8:00\"");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if (input != null && !input.isEmpty()) {
            int numCases = Integer.parseInt(input);
            String[] testCases = new String[numCases];
            for (int i = 0; i < testCases.length; i++) {
                testCases[i] = br.readLine();
            }
            for (int i = 0; i < testCases.length; i++) {
                rightTime(testCases[i]);
            }
        }
    }
}
