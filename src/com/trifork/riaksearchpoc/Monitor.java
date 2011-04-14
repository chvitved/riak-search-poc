package com.trifork.riaksearchpoc;

import java.util.Timer;
import java.util.TimerTask;

public class Monitor {

	private static final int outputInterval = 5000;
	private int counter = 0;
	private int totalCounter = 0;
	private Graph graph = new Graph();
		
	Monitor() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				int total = getTotalCounter();
				System.out.println("total objects inserted: " + total);
				int insertsPerSecond = getCounter() * 1000 / outputInterval;
				System.out.println("inserts per second: " + insertsPerSecond);
				graph.addPoint(total, insertsPerSecond);
				graph.writeToFile();
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
