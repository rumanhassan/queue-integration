package org.lottery.common.message.impl.jms;

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
public class MessageListenerRegisterJmsImpl implements MessageListenerRegister {

    ConnectionFactory connectionFactory;

    @Override
    public void register(String queueName, MessageListener listener) {
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setConnectionFactory(connectionFactory);
        dmlc.setDestinationName(queueName);
        dmlc.setMessageListener(new QueueProcessor(listener));
        dmlc.setMaxConcurrentConsumers(10);
        dmlc.initialize();
        dmlc.start();
    }

    public class QueueProcessor implements javax.jms.MessageListener {

        MessageListener listener;
        Type messageType;

        public QueueProcessor() {
        }

        public QueueProcessor(MessageListener listener) {
            Type[] genericInterfaces = listener.getClass().getGenericInterfaces();
            for (int i = 0; i < genericInterfaces.length; i++) {
                Type type = genericInterfaces[i];
                System.out.println(type.getClass());
                if (type instanceof ParameterizedType) {
                    ParameterizedType messageListenerType = (ParameterizedType) type;
                    if (messageListenerType.getRawType().getClass().equals(MessageListener.class)) {
                        messageType = messageListenerType.getActualTypeArguments()[0];
                    }
                }
            }
            this.listener = listener;
        }

        @Override
        public void onMessage(Message message) {
            String stringProperty = null;
            try {
                stringProperty = message.getStringProperty("msg");
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            listener.onMessage(JSON.parseObject(stringProperty, messageType));

        }
    }

    /**
     * @return the connectionFactory
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * @param connectionFactory the connectionFactory to set
     */
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
