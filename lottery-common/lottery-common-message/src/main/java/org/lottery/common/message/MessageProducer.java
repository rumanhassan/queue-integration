package org.lottery.common.message;

public interface MessageProducer {

    public void send(String destinationName, Object o);
}
