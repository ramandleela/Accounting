package com.bookstore.accounting.controller;
// Java

// Spring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This Service
import com.bookstore.accounting.component.AccountingComponent;

@RestController
public class AccountingController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountingController.class);
	private AccountingComponent accountingComponent;


	@Autowired
	AccountingController(AccountingComponent accountingComponent){
		this.accountingComponent = accountingComponent;
	}

	
	@RequestMapping(method = RequestMethod.GET,  value= "/healthcheck")
	String getStatus() {
		logger.info("in getStatus()");
		return "OK";
	}
	
}
