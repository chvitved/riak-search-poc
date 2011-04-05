package com.trifork.riaksearchpoc;

import java.util.Timer;
import java.util.TimerTask;

public class Monitor {

	private static final int outputInterval = 5000;
	private int counter = 0;
	private int totalCounter = 0;
		
	Monitor() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("total objects inserted: " + getTotalCounter());
				double insertsPerSecond = getCounter() * 1000.0 / outputInterval;
				System.out.println("inserts per second: " + insertsPerSecond);
				resetCounter();
			}
		}, outputInterval, outputInterval);
	}
	
	public synchronized void increment() {
		counter++;
		totalCounter++;
	}
	
	private synchronized int getCounter() {
		return counter;
	}
	
	private synchronized void resetCounter() {
		counter = 0;
	}
	
	private synchronized int getTotalCounter() {
		return totalCounter;
	}
}
