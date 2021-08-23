package com.ascending.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.ascending.ApplicationBootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MessageService messageService;

    @Autowired
    public AmazonSQS amazonSQS;

    private String fakeQueueUrl = "https://sqs.us-east-1.amazonaws.com/469638324168/training-dev";

    @Before
    public void setUp(){
        logger.debug(">>>>>>>Setting up before testing...");
        GetQueueUrlResult urlResult = Mockito.mock(GetQueueUrlResult.class);
        when(urlResult.getQueueUrl()).thenReturn(fakeQueueUrl);
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(urlResult);
    }

    @Test
    public void sendMessage(){
        String msg = ">>>>>>test sendMessage 1";
        messageService.sendMessage(msg, 2);
        Mockito.verify(messageService.getSqsClient(), times(1))
                .sendMessage(any());
    }
}
