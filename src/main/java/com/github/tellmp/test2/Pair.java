package com.github.tellmp.test2;

import java.util.Comparator;

/**
 * A pair of some types
 * 
 * Is immutable
 * Is equal if and only if both of it's pair objects are equal
 * Comparable - should compare the second type only
 * 
 * @param <T1> First pair type
 * @param <T2> Second pair type
 */
public class Pair<T1, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
    T1 tacoType;
    T2 tacoScore;

    public Pair(T1 tacoType, T2 tacoScore) {
        this.tacoType = tacoType;
        this.tacoScore = tacoScore;
    }

    @Override
    public int compareTo(Pair<T1, T2> o) {
        return this.tacoScore.compareTo(o.tacoScore);
    }
}
