package com.ascending.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonSQS sqsClient;

    private String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/469638324168/training-dev";

    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);
        logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }

//    public void sendMessage(){
////        System.out.println("Sending a message to MyQueue.\n");
////        amazonSQS.sendMessage(new SendMessageRequest(myQueueUrl, "This is my message text."));
//        final SendMessageRequest sendMessageRequest = new SendMessageRequest();
//        sendMessageRequest.withQueueUrl(myQueueUrl).withMessageBody("msg");
//        final SendMessageResult sendMessageResult = sqsClient.sendMessage(sendMessageRequest);
//        final String sequenceNumber = sendMessageResult.getSequenceNumber();
//        final String messageId = sendMessageResult.getMessageId();
//        System.out.println("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");
//    }

    public void sendMessage(String messageBody, Integer delaySed) {
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(myQueueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySed);
        sqsClient.sendMessage(sendMsgRequest);
    }

    public AmazonSQS getSqsClient() {
        return sqsClient;
    }

}
