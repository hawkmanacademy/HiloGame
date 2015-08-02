package com.hilogame.services;

public interface MessageService {
	public void sendMessage(String messageId,Object message);
	
	public Object readMessage(String messageId);
}
