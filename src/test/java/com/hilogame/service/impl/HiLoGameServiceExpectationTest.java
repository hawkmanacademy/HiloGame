package com.hilogame.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hilogame.constants.Outcome;
import com.hilogame.constants.PlayerChoice;
import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.services.MessageService;
import com.hilogame.services.impl.HiLoGameService;

public class HiLoGameServiceExpectationTest {
	HiLoGameService hlgs;
	Map<Outcome,AtomicInteger> results = new HashMap<Outcome, AtomicInteger>();

	@Before
	public void setUp() throws Exception {
		hlgs = new HiLoGameService(new MessageService() {

			@Override
			public void sendMessage(String messageId, Object message) {
				// TODO Auto-generated method stub

			}

			@Override
			public Object readMessage(String messageId) {
				return Math.random() < 0.5?"h":"l";
			}
		});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Executor pool =Executors.newFixedThreadPool(1000);
		
		for (int i=0;i<1000000;i++){
			pool.execute(new Runnable(){

				@Override
				public void run() {
					Game game = hlgs.startGame();
					GameResult result = hlgs.endGame(game);
					
					AtomicInteger counter = null;
					if ((counter = results.get(result.getGameOutcome())) == null){
						counter = new AtomicInteger();
						results.put(result.getGameOutcome(),counter);
					}
					counter.incrementAndGet();
				}
			});
			
		}
		System.out.println(results);
	}

}
