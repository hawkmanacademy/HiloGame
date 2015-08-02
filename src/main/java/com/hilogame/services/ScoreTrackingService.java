package com.hilogame.services;

public interface ScoreTrackingService {
	
	public int recordWin();
	
	public int recordLoss();
	
	public int getTotalWins();
	
	public int getTotalLosses();
}
