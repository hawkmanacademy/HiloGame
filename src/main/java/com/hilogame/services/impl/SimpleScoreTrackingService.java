package com.hilogame.services.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.hilogame.services.ScoreTrackingService;

public class SimpleScoreTrackingService implements ScoreTrackingService {

	private final AtomicInteger lossCounter;
	
	private final AtomicInteger winCounter;
	
	public SimpleScoreTrackingService() {
		this.lossCounter = new AtomicInteger();
		this.winCounter = new AtomicInteger();
	}
	
	@Override
	public int recordWin() {
		return winCounter.incrementAndGet();
	}

	@Override
	public int recordLoss() {
		return lossCounter.incrementAndGet();
	}
	
	@Override
	public int getTotalWins() {
		return winCounter.intValue();
	}
	
	@Override
	public int getTotalLosses() {
		return lossCounter.intValue();
	}

}
