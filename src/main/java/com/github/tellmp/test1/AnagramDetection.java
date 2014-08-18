package com.github.tellmp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class determines how many times a query string – or an anagram of a
 * query string – appears in a parent string.
 * Complexity: O((parent-query) * O(query log query))
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/17/14.
 */

public class AnagramDetection {

    /**
     * This methods checks if parent and query are anagrams.
     *
     * @param parent
     * @param query
     * @return true if parent and query are anagrams
     */
    public static boolean checkAnagram(String parent, String query) {
        int i = 0;
        int j = 0;

        if (parent == null || query == null) {
            return false;
        }
        while (i < parent.length() && j < query.length()) {
            if (parent.indexOf(query.charAt(j)) < 0 || query.indexOf(parent.charAt(i)) < 0) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }// end of Check Anagram Method. the Big-Oh is O(n log n)

    /**
     * This method counts how many times an anagram of the query string appears in the parent string.
     *
     * @param parent
     * @param query
     * @return count of anagrams
     */
    public static int countAnagrams(String parent, String query) {
        int count = 0;
        if (parent != null && query != null) {
            for (int i = 0; i < parent.length() - query.length(); i++) {
                if (checkAnagram(parent.substring(i, i + query.length()), query)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Please enter two strings, a ‘parent’ string and a" +
                "‘query’ string respectively, to determine how many times the query " +
                "string – or an anagram of the query string – appears in the" +
                "parent string.\n(i.e.: \n\"AdnBndAndBdaBn\n dAn\n\")");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String parent = br.readLine();
        String query = br.readLine();
        System.out.println(countAnagrams(parent, query));
    }
}
