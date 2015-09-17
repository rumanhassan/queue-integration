/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lottery.common.message;

import java.util.Random;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 *
 * @author @kaybinwong kaybinwong@gmail.com
 */
public class ProducerTest {
    
    private static final String CONFIG = "classpath*:/common-message-test.xml";
    private static final Random rand = new Random();
    
    @Test
    public void testSend() {
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG);
        ctx.start();

        final MessageChannel channel = ctx.getBean("common-message.producer", MessageChannel.class);

        channel.send(MessageBuilder.withPayload("from messageChannel" + System.currentTimeMillis())
                .setHeader(KafkaHeaders.MESSAGE_KEY, "key")
                .setHeader(KafkaHeaders.TOPIC, "test")
                .build());
        
        MessageProducer messageProducer = ctx.getBean(MessageProducer.class);
        messageProducer.send("test", "from messageProducer" + System.currentTimeMillis());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }
    
}
