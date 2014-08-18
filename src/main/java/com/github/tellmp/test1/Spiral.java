package com.github.tellmp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class generates the integers obtained by spiralling outward in an
 * anti-clockwise direction from row r column c, starting upward.
 *
 * Created by Tell Mueller-Pettenpohl on 8/17/14.
 */

public class Spiral {

    /**
     * This method determines the number of iterations through the given array.
     * @param array of integers for spiralling
     * @param y first starting point index
     * @param x second starting point index
     */
    public static void printSpiral(int[][] array, int y, int x) {
        x--;
        y--;
        int step = 1;
        while (printIteration(array, y++, x++, step)) {
            step += 2;
        }
        System.out.println();
    }

    /**
     * This method prints the integers obtained by spiralling outward in an
     * anti-clockwise direction from row r column c, starting upward
     * of the given array.
     * @param array of integers for spiralling
     * @param y first starting point index
     * @param x second starting point index
     * @param step number of max steps while spiralling in one direction
     * @return true if there are more integers to print
     */
    private static boolean printIteration(int[][] array, int y, int x, int step) {
        boolean isPrinting = false;
        for (int z = y; z > y - step; z--) {
            if (checkBoundaries(array, z, x)) {
                isPrinting = true;
                System.out.print("\t" + array[z][x]);
            }
        }
        y -= step;
        for (int z = x; z > x - step; z--) {
            if (checkBoundaries(array, y, z)) {
                isPrinting = true;
                System.out.print("\t" + array[y][z]);
            }
        }
        x -= step;
        step++;
        for (int z = y; z < y + step; z++) {
            if (checkBoundaries(array, z, x)) {
                isPrinting = true;
                System.out.print("\t" + array[z][x]);
            }
        }
        y += step;
        for (int z = x; z < x + step; z++) {
            if (checkBoundaries(array, y, z)) {
                isPrinting = true;
                System.out.print("\t" + array[y][z]);
            }
        }
        return isPrinting;
    }

    /**
     * Checks if the indices x and y are still inside the boundaries of the
     * given array.
     * @param array given array to check boundaries
     * @param y index
     * @param x index
     * @return true if its still inside the boundaries of the given array
     */
    protected static boolean checkBoundaries(int[][] array, int y, int x) {
        if ((x >= 0 && y >= 0) && (y < array.length && x < array[0].length)) {
            return true;
        }
        return false;
    }

    /**
     * Fills the given array with consecutive integers from left to right,
     * top to bottom, starting with 1.
     * @param arr array to fill
     */
    protected static void fillArray(int[][] arr) {
        int cnt = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = cnt;
                cnt++;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Please enter the dimensions h x w of the Grid and" +
                " the starting position separated with spaces.\n(i.e.: \"5 5 3 3\")");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int[] inputValues = new int[4];
        int idx = 0;
        for (int i = 0; i < input.length() && idx < inputValues.length; i++) {
            char c = input.charAt(i);
            if (c != ' ') {
                inputValues[idx] = Character.getNumericValue(c);
                idx++;
            }
        }
        if (inputValues != null) {
            int[][] arr = new int[inputValues[0]][inputValues[1]];
            System.out.println();
            fillArray(arr);
            printSpiral(arr, inputValues[2], inputValues[3]);
        }
    }
}
