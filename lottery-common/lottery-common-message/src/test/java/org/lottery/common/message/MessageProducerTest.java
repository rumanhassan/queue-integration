package org.lottery.common.message;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        for(int i=0;i < 10; i++) {
            messageProducer.send("test", i+"");
        }
        Thread.sleep(10000);
    }
    
}
