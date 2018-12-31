package com.daoming.fileservice.rabbitmq.Sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.daoming.fileservice.config.ApplicationConfig.class)
public class SenderTest {

    @Autowired
    Sender sender;

    @Test
    public void send() throws Exception{
        while(true){
            String msg = new Date().toString();
            sender.send(msg);
            Thread.sleep(1000);
        }
    }
}