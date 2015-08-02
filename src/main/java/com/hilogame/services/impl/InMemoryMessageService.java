package com.hilogame.services.impl;

import java.util.HashMap;
import java.util.Map;

import com.hilogame.services.MessageService;

public class InMemoryMessageService implements MessageService {

	private Map<String,Object> messages = new HashMap<String, Object>();
	
	@Override
	public void sendMessage(String messageId, Object message) {
		messages.put(messageId, message);
	}

	@Override
	public Object readMessage(String messageId) {
		Object message = messages.get(messageId);
		messages.remove(messageId);
		return message;
	}
	
}
