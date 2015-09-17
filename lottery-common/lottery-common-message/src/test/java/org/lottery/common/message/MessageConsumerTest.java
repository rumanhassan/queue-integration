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
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"classpath*:common-message-test.xml"})
public class MessageConsumerTest {

    @Autowired
    MessageListenerRegister messageListenerRegister;

    @Test
    public void receiveMessage() throws InterruptedException {

        messageListenerRegister.register("test", new MessageListener<String>() {
            @Override
            public void onMessage(String o) {
                System.out.println("receive:" + o);
            }
        });
        Thread.sleep(10000);
        System.out.println("passed");
    }

}
