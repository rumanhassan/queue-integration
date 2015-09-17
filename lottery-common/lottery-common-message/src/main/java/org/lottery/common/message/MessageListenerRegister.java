package org.lottery.common.message;

public interface MessageListenerRegister {

    public void register(String queue, MessageListener listener);
}
