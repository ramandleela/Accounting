package com.bookstore.accounting.component;

// Java
import java.util.Arrays;

// Spring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Component
public class AccountingComponent {
	private static final Logger logger = LoggerFactory.getLogger(AccountingComponent.class);
	private static final String ConfigURL = "http://localhost:8000/getValue/";
	private static final String MessageQProperty = "InventoryMessageQName";
		
    public void initialize() {
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> response = restTemplate.getForEntity(ConfigURL + MessageQProperty, String.class);
    	if(response != null && response.getStatusCode() == HttpStatus.OK) {
			String qName = response.getBody().toString();
			logger.info("qName: " + qName);
			if (qName.contentEquals("InventoryQ")) {
				logger.info("InventoryQ name matches");
			} else {
				logger.warn("InventoryQ does not match");
			}
		} else {
			logger.warn("GET request for Inventory Message Q Name failed");
		}
    }

}



