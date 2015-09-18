package org.lottery.common.message.impl.kafka;

import javax.annotation.Resource;

import org.lottery.common.message.MessageProducer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * http://colobu.com/2014/11/19/kafka-spring-integration-in-practice/
 * @author Administrator
 */
@Component
public class MessageProducerKafkaImpl implements MessageProducer {

    @Resource(name = "common-message.producer")
    MessageChannel messageChannel;

    @Override
    public void send(String destinationName, final Object o) {
        messageChannel.send(MessageBuilder.withPayload(o)
                .setHeader("messageKey", "key")
                .setHeader("topic", destinationName)
                .build(), 1000);
    }
}
