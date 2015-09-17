package org.lottery.common.message.impl.kafka;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

import org.lottery.common.message.MessageListener;
import org.lottery.common.message.MessageListenerRegister;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerRegisterKafkaImpl implements MessageListenerRegister {

    ConnectionFactory connectionFactory;

    @Override
    public void register(String queueName, MessageListener listener) {
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(connectionFactory);
        dmlc.setDestinationName(queueName);
        dmlc.setMessageListener(new QueueProcessor());
        dmlc.setConcurrentConsumers(10);
    }

    public class QueueProcessor implements javax.jms.MessageListener {

        MessageListener listener;

        public QueueProcessor() {
        }

        public QueueProcessor(MessageListener listener) {
            this.listener = listener;
        }

        @Override
        public void onMessage(Message message) {
            String stringProperty;
            try {
                stringProperty = message.getStringProperty("msg");
                Type actualType2 = ((ParameterizedType) listener).getActualTypeArguments()[0];
                listener.onMessage(JSON.parseObject(stringProperty, actualType2));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
