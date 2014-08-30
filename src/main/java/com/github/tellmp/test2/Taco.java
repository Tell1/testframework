package com.github.tellmp.test2;

import java.util.Random;


public class Taco {

    private static final Random random = new Random();
    public final int crunchiness;    // a rating from 0 - 5 on how crunchy the taco is
    public final TacoType type;        // the traditional type of this taco
    public final boolean hardShell;    // was the taco made with a traditionally 'hard' tortilla
    public final float deliciousness;  // a rating on a normalized scale (0-1) on how delicious this taco is to be

    public Taco(int crunchiness, TacoType type, boolean hardShell, float deliciousness) {
        this.crunchiness = crunchiness;
        this.type = type;
        this.hardShell = hardShell;
        this.deliciousness = deliciousness;
    }

    //calculates a positive, non-normalized score for this taco on
    // how great life would be while eating it. The score doesn't have to be
    // perfect, but should appropriately sort a taco on the following:
    // * Science has proven that people score tacos higher if they are both crunchy and delish
    // * Science has proven that people will always rate hard shell tacos with a higher score than soft shell ones,
    //	  regardless of their crunchiness/deliciousness.
    // * Science has proven that people always love Sudados tacos, regardless of any other property
    // * Science has proven that people always hate Asador tacos, regardless of any other property
    public float calcScore() {
        if (type == TacoType.Sudados)
            return 100;
        if (type == TacoType.Asador)
            return 0;

        final float SCALE = 30;
        float score = 1; // 1 to make sure asador is always at the bottom
        score += SCALE * (crunchiness / 5.0f) * deliciousness;
        if (hardShell)
            score += SCALE;
        return score;
    }

    public void tasteTest() {
        try {
            final long time = (long) (calcScore() * random.nextFloat());
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        final String deliciousnessStr;
        final String typeStr = this.type.toString();
        final String shellStr = hardShell ? "hard" : "soft";
        final String crunchinessStr;

        if (this.deliciousness < 0.25f)
            deliciousnessStr = "not very delish";
        else if (this.deliciousness < 0.5)
            deliciousnessStr = "fairly delish";
        else if (this.deliciousness < 0.75)
            deliciousnessStr = "very delish";
        else
            deliciousnessStr = "extremely delish";

        if (this.crunchiness == 0)
            crunchinessStr = "floppy";
        else if (this.crunchiness == 1)
            crunchinessStr = "a bit crunchy";
        else if (this.crunchiness == 2)
            crunchinessStr = "slightly crunchy";
        else if (this.crunchiness == 3)
            crunchinessStr = "crunchy";
        else if (this.crunchiness == 4)
            crunchinessStr = "very crunchy";
        else
            crunchinessStr = "extremely crunchy";

        return "A " + deliciousnessStr + ", " + crunchinessStr + ", " + typeStr + " taco with a " + shellStr + " shell";
    }

    public enum TacoType {
        Cazo,
        Asador,
        Sudados,
        Dorados
    }
}
