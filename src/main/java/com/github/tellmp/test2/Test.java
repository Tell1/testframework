package com.github.tellmp.test2;

import java.util.*;
import java.util.concurrent.*;

/**
 * This class provides tests for the most delicious tacos
 */
public class Test {

	//must be at least 5
	private static final int TACO_COUNT = 10 * 100;
	
	public static void main(String[] args) throws Exception {
		final List<Taco> tacos = new LinkedList<Taco>();
		long timer = System.currentTimeMillis();
		
		//generate a whole bunch of tacos
		final Taco.TacoType[] tacoTypes = Taco.TacoType.values();
		for(long i = 0; i < TACO_COUNT; i++) {
			final int crunchiness = (int)(Math.random() * 5);
			final Taco.TacoType type = tacoTypes[(int)(Math.random() * tacoTypes.length)];
			final boolean hardShell = Math.random() >= 0.5;
			final float deliciousness = (float)Math.random();
			
			tacos.add(new Taco(crunchiness, type, hardShell, deliciousness));
		}
		timer = printTimeTaken(timer, "taco generation");
		
		doProcessing(tacos);
		timer = printTimeTaken(timer, "processing");
	}
	
	//a little helper function for printing out
	// how long something takes in a function
	public static long printTimeTaken(long lastTime, String task) {
		final long now = System.currentTimeMillis();
		System.out.println("Time taken for " + task + ": " + (now - lastTime));
		return now;
	}

	//This function will do the processing for every taco:
	public static void doProcessing(List<Taco> tacos) throws Exception {
		
		//first we need to do some basic processing on every taco
		//now we need to sort them by type
		//TODO: Multithread this - can be done while taste testing!
		final Taco.TacoType[] tacoTypes = Taco.TacoType.values();
		final Map<Taco.TacoType, Queue<Taco>> buckets = new HashMap<Taco.TacoType, Queue<Taco>>();
        // Schedules a thread for every taco inside of an ExecuterService
        ExecutorService s = Executors.newFixedThreadPool(TACO_COUNT);

        for (Taco.TacoType t : tacoTypes) {
            buckets.put(t, new  ConcurrentLinkedQueue<Taco>());
        }
        for(final Taco taco : tacos) {
            s.execute(new Runnable() {
                @Override
                public void run() {
                    taco.tasteTest();
                    // fills the tacoList with sorted by type
                    Queue<Taco> tacoList = buckets.get(taco.type);
                    tacoList.add(taco);
                }
            });
		}
        s.shutdown();
        s.awaitTermination(1, TimeUnit.DAYS);

		//now we add up the score of every type of taco
		//TODO: Use your Pair structure here to match a taco type with a total score
		final Taco.TacoType[] totalScoresTACOS = tacoTypes;
		final double[] totalScoresTOTAL = new double[totalScoresTACOS.length];
        List<Pair<Taco.TacoType, Double>> tacoList = new ArrayList<Pair<Taco.TacoType, Double>>();
		for(int i = 0; i < totalScoresTACOS.length; i++) {
                for(Taco taco : buckets.get(totalScoresTACOS[i])) {
                    totalScoresTOTAL[i] += taco.calcScore();
                }
            // fills the tacoList with the Tacotype and related score
            tacoList.add(new Pair<Taco.TacoType, Double>(totalScoresTACOS[i], totalScoresTOTAL[i]));
        }

		//now find the most delish set of tacos
		//TODO: Use collections.sort and your pair structure instead of my bad sort
         Collections.sort(tacoList);
		
		System.out.println("Most delish taco type: " + totalScoresTACOS[totalScoresTACOS.length - 1] + " with total " + totalScoresTOTAL[totalScoresTACOS.length - 1]);
		System.out.println("Second most delish taco type: " + totalScoresTACOS[totalScoresTACOS.length - 2] + " with total " + totalScoresTOTAL[totalScoresTACOS.length - 2]);
	}
}
