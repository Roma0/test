package com.ascending.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.ascending.service.FileService;
import com.ascending.service.MessageService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("unit")
public class AWSTestConfig {
    @Bean
    public FileService getFileService(){
        AmazonS3 s3Client = mock(AmazonS3.class);
        FileService fileService = new FileService(s3Client);
//        fileService.setBucketName("training-dan-1");
        return fileService;
    }

//    @Bean
//    public MessageService messageService(){
//        AmazonSQS sqsClient = mock(AmazonSQS.class);
//        MessageService messageService = new MessageService(sqsClient);
//        return messageService;
//    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS(){
        return mock(AmazonSQS.class);
    }

}
