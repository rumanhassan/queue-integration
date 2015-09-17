package org.lottery.common.message.impl.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.lottery.common.message.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class MessageProducerJmsImpl implements MessageProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void send(String destinationName, final Object o) {
        jmsTemplate.send(destinationName, new MessageCreator() {
            @Override
            public Message createMessage(Session arg0) throws JMSException {
                Message createMessage = arg0.createMessage();
                createMessage.setStringProperty("msg", JSON.toJSONString(o));
                return createMessage;
            }

        });
    }
}
