package org.lottery.common.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author kaybinwong@gmail.com @kaybinwong
 */
@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/common-message-test.xml"})
public class MessageProducerTest {

    @Autowired
    MessageProducer messageProducer;

    @Test
    public void testSend() throws InterruptedException {
        String payload = "11111111111";
        messageProducer.send("test", payload);
        Thread.sleep(10000);
        System.out.println("passed");
    }
    
}
