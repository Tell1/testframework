package com.github.tellmp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class determines if a given string consisting entirely of the characters ()[]{} is balanced.
 * In other words every opening bracket must have a closing bracket of the same type following it,
 * and the string in between the pair must also be balanced.
 *
 * Created by Tell Mueller-Pettenpohl on 8/17/14.
 */
public class BalancedBrackets {

    /**
     * This method determines if a given string of brackets is balanced.
     *
     * @param brackets string of the characters ()[]{}
     * @return true if brackets is balanced; otherwise false
     */
    public static boolean isBalanced(String brackets) {
        if (brackets == null || brackets.isEmpty()) {
            return true;
        }
        if (brackets.contains("()")){
            return isBalanced(brackets.replaceFirst("\\(\\)", ""));
        }
        if (brackets.contains("[]")){
            return isBalanced(brackets.replaceFirst("\\[\\]", ""));
        }
        if (brackets.contains("{}")){
            return isBalanced(brackets.replaceFirst("\\{\\}", ""));
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Please enter a string consisting entirely of the" +
                "characters ()[]{}, to determine if it is \"balanced\"." +
                "\n(i.e.: \"()[]{}(([])){[()][]}\")");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if(input != null) {
            System.out.println(isBalanced(input) ? "balanced" : "not balanced");
        }
    }
}
