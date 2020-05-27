package com.custom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.custom.service.CardApplicationService;

@RestController
@RequestMapping("card-application")
public class CardController {
	
	
	@Autowired
	CardApplicationService cardApplicationService;
	
	@GetMapping("/register/{mobileNumber}/{name}/{email}")
	private ResponseEntity<String> registerCard(long mobileNumber , String name , String email)
	{
		return new ResponseEntity<String>(
				cardApplicationService.registerForCard(mobileNumber, name, email), HttpStatus.OK);
	}
	
	@PostMapping("/transact")
	private ResponseEntity<String> transact(String cardNumber, int amount)
	{
		cardApplicationService.transact(cardNumber, amount);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	private ResponseEntity<String> withdraw(String cardNumber , int amount)
	{
		cardApplicationService.withdraw(cardNumber, amount);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping("/generateStatement")
	private ResponseEntity<String> generateCreditCardStatement(String cardNumber)
	{
		cardApplicationService.generateCreditCardStatement(cardNumber);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping("/pay")
	private ResponseEntity<String> pay(String cardNumber, double amount)
	{
		cardApplicationService.pay(cardNumber, amount);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@PostMapping("/closeCard")
	private ResponseEntity<String> closeCard(String cardNumber)
	{
		cardApplicationService.closeCard(cardNumber);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		
	}
		
	
	
	
	
	

}
