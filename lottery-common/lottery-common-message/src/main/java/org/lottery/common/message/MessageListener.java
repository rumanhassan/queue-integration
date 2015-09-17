package org.lottery.common.message;

public interface MessageListener<T> {

	public void onMessage(T o);
}
