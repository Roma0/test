package com.ascending.controller;


import com.ascending.service.MessageService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value={"/message"})
public class MessageSQSController {
    @Autowired
    public MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(method= RequestMethod.POST,params = {"messageBody"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendMessageRequest(@RequestParam(value = "messageBody") String messageBody){
//        Map<Long,String> result=new HashMap<>();
        String result;

        try{messageService.sendMessage(messageBody,5);
            result = messageBody;

            return true;
        }
        catch(ServiceException e){ //compile exception(not runtime exception)
            logger.error(" didn't send.",e.getMessage());
        }
        return null;
    }
}
