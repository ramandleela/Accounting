package com.bookstore.accounting.component;

// Java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Spring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
public class Receiver {
	
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	RabbitMessagingTemplate template;
	
	@Autowired
	public Receiver(RabbitMessagingTemplate template){
		this.template = template;
	}
	
	@Bean
	Queue queue() {
		return new Queue("InventoryQ", false);
	}

	
	@RabbitListener(queues = "InventoryQ")
	public void processMessage(Map<String,Object> bookMap) {
	   logger.info("Getting Messages ...");
	   try {
		   logger.info(bookMap.toString());
		   if(!bookMap.isEmpty()) {
			   logger.info("Messages Found...");
			   for(Map.Entry<String, Object> entry : bookMap.entrySet()) {
				   List<String> tokens = new ArrayList<>(Arrays.asList(((String)entry.getValue()).split(",")));
				   for (String i : tokens) {
						  logger.info(i);
				   }
				} 
			} else {
				logger.info("No Messages Found...");
			}
	   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}
}
	


