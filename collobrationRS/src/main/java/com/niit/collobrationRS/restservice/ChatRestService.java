package com.niit.collobrationRS.restservice;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collobration.model.Chatmessage;
import com.niit.collobration.model.Outputmessage;

@Controller
@RequestMapping("/")
public class ChatRestService 
{
	
	  @MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public Outputmessage sendMessage(Chatmessage message) {
	    return new Outputmessage(message, new Date());
	  }
}

