package com.custom.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.custom.dao.BillingDaoService;
import com.custom.dao.InstrumentAttributeDaoService;
import com.custom.dao.InstrumentDaoService;
import com.custom.dao.TransactionDaoService;
import com.custom.exception.CardCanNotBeClosedException;
import com.custom.model.Billing;
import com.custom.model.CustomFlag;
import com.custom.model.Instrument;
import com.custom.model.InstrumentAttributes;
import com.custom.model.InstrumentState;
import com.custom.model.Transaction;

public class CardApplicationServiceImpl implements CardApplicationService {

	@Autowired
	private InstrumentDaoService instrumentDaoService;
	
	@Autowired
	InstrumentAttributeDaoService instrumentAttributesDaoService;
	
	@Autowired
	BillingDaoService billingDaoService;
	
	@Autowired
	TransactionDaoService transactionDaoService;
	
	@Autowired
	GregorianCalendar gregorianCalendar ;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public String registerForCard(long mobileNumber , String name , String email) {
		//Step 1- method to register for a card . This method returns the card number
		Instrument instrument = new Instrument();
		InstrumentAttributes instrumentAttributes = new InstrumentAttributes();
		
		Long cardNumber = mobileNumber+ (new Random().nextInt(899999) + 100000);
		
		instrument.setName(name);
		instrument.setPhoneNumber(mobileNumber);
		instrument.setCardNumber(cardNumber.toString());
		instrument.setEmailId(email);
		instrument.setCashLimit(2000);
		instrument.setCreationDate(gregorianCalendar.getTime());
		instrument.setLastUpdateDate(gregorianCalendar.getTime());
		instrument.setCreditLimit(10000);
		instrument.setExpiryDate(gregorianCalendar.getTime()); //need to get future date
		instrument.setInstrumentState(InstrumentState.ACTIVE);
		
		instrumentAttributes.setAvailableCashLimit(2000);
		instrumentAttributes.setAvailableCreditLimit(10000);
		instrumentAttributes.setBillCycle(gregorianCalendar.getTime().getDate()); //need to pass todays day
		instrumentAttributes.setCardNumber(cardNumber.toString());
		instrumentAttributes.setInterestCharged(0);
		
		instrumentAttributesDaoService.save(instrumentAttributes);
		instrumentDaoService.save(instrument);
		
		return cardNumber.toString();
	}

	@Override
	public void transact(String cardNumber, int amount) {
		
		int count = instrumentDaoService.count(cardNumber);
		Instrument instrument = instrumentDaoService.findByCardNumberActive(cardNumber);
		Optional<InstrumentAttributes> instrumentAttributes = instrumentAttributesDaoService.findById(cardNumber);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCardNumber(cardNumber);
		transaction.setCreationDate(gregorianCalendar.getTime());
		transaction.setTransactionType("TRANSACT");
		if(count <=0)
		{
			transaction.setStatus("INVALID");
		}
		else if(instrument == null)
		{
			transaction.setStatus("INACTIVE");
		}
		else if(instrumentAttributes.get().getAvailableCreditLimit() < amount)
		{
			transaction.setStatus("LIMIT_EXCEEDED");
		}
		else {
			transaction.setStatus("SUCCESS");
			instrumentAttributes.get().setAvailableCreditLimit(instrumentAttributes.get().getAvailableCreditLimit()-amount);
			instrumentAttributesDaoService.save(instrumentAttributes);
		}
		transactionDaoService.save(transaction);
	}

	@Override
	public void withdraw(String cardNumber, int amount) {
		
		int count = instrumentDaoService.count(cardNumber);
		Instrument instrument = instrumentDaoService.findByCardNumberActive(cardNumber);
		Optional<InstrumentAttributes> instrumentAttributes = instrumentAttributesDaoService.findById(cardNumber);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCardNumber(cardNumber);
		transaction.setCreationDate(new Date());
		transaction.setTransactionType("CASH");
		if(count <=0)
		{
			transaction.setStatus("INVALID");
		}
		else if(instrument == null)
		{
			transaction.setStatus("INACTIVE");
		}
		else if(instrumentAttributes.get().getAvailableCashLimit() < amount)
		{
			transaction.setStatus("LIMIT_EXCEEDED");
		}
		else {
			transaction.setStatus("SUCCESS");
			instrumentAttributes.get().setAvailableCashLimit(instrumentAttributes.get().getAvailableCashLimit()-amount);
			instrumentAttributes.get().setInterestCharged(instrumentAttributes.get().getInterestCharged() + (amount*2) /100);
			instrumentAttributesDaoService.save(instrumentAttributes);
		}
		
		
	}

	@Override
	public void generateCreditCardStatement(String cardNumber) {
		//case when there is no pending amount from past i.e there 
		
		Billing billing =   billingDaoService.findBillByCardNumberStatus(cardNumber);
		
		Optional<InstrumentAttributes> instrumentAttributes = instrumentAttributesDaoService.findById(cardNumber);
		
		Optional<Instrument> instrument = instrumentDaoService.findById(cardNumber);
		
		double totalCashLimit = instrument.get().getCashLimit();
		double totalCreditLimit = instrument.get().getCreditLimit();
		
		double usedCashLimit = totalCashLimit- instrumentAttributes.get().getAvailableCashLimit();
		double usedCreditLimit = totalCreditLimit - instrumentAttributes.get().getAvailableCreditLimit();
		double interestDue = instrumentAttributes.get().getInterestCharged();
		
		double minDue = (usedCashLimit + usedCreditLimit+ interestDue) * 2/ 100;
		if(billing == null) //there is no old pending for the card number, in this case calculate the bill
		{
			
			Billing bill = new Billing();
			bill.setBillingStatus("NEW");
			bill.setCardNumber(cardNumber);
			bill.setCarryForwardedCash(0);
			bill.setCarryForwardedCredit(0);
			bill.setCarryForwardedCycle(0);
			bill.setCreationDate(gregorianCalendar.getTime());
			bill.setDueDate(new Date());//future date
			bill.setTotalCashDue(usedCashLimit);//to be derived from the instrument attributes 
			bill.setTotalCreditDue(usedCreditLimit);//to be derived from the inst table
			bill.setTotalDueAmount(usedCashLimit+usedCreditLimit+interestDue);//derive
			bill.setMinimumAmountDue(minDue); //find the min amount due
			bill.setMinimumAmountPaidFlag(CustomFlag.NO);
			
			
			//get the amount from instrument and reset in instrument attribute
			instrumentAttributes.get().setAvailableCashLimit(totalCashLimit);
			instrumentAttributes.get().setAvailableCreditLimit(totalCreditLimit);
			instrumentAttributes.get().setInterestCharged(0);
			
			billingDaoService.save(bill);
			instrumentAttributesDaoService.save(instrumentAttributes);
		}
		else {
			
			double totalDue = billing.getTotalDueAmount();
			int noOfCycles = billing.getCarryForwardedCycle();

			Billing bill = new Billing();
			bill.setBillingStatus("NEW");
			bill.setCardNumber(cardNumber);
			bill.setCarryForwardedCycle(noOfCycles+1);
			bill.setCreationDate(new Date());
			bill.setDueDate(new Date());//future date
			bill.setMinimumAmountDue(12); //find the min amount due
			bill.setMinimumAmountPaidFlag(CustomFlag.NO);
			bill.setTotalCashDue(usedCashLimit);//to be derived from the instrument attributes 
			bill.setTotalCreditDue(usedCreditLimit);//to be derived from the inst table
			bill.setCarryForwardedCash(billing.getTotalCashDue());
			bill.setCarryForwardedCredit(billing.getTotalCreditDue());
			bill.setTotalDueAmount(usedCreditLimit+usedCreditLimit+ billing.getTotalCashDue() + billing.getTotalCreditDue()+billing.getInterest());
		   
			billing.setBillingStatus("CARRY_FORWARDED");
			
			if(billing.getMinimumAmountPaidFlag().equals("N") && instrument.get().getInstrumentState().equals(InstrumentState.ACTIVE))
				bill.setLateCharges(100);
			
			if(noOfCycles >=3 && usedCashLimit + usedCreditLimit+ interestDue > 100 )
			{
				instrument.get().setInstrumentState(InstrumentState.DELINQUENT);
			}
		}
		
	}

	@Override
	public void pay(String cardNumber, double amount) {
		

		Billing billing = billingDaoService.findBillByCardNumberStatus(cardNumber);
		double dueAmount = billing.getTotalDueAmount();
		double dueCash = billing.getTotalCashDue();
		double dueCredit  = billing.getTotalCreditDue();
		double carryForwardedCash = billing.getCarryForwardedCash();
		double carryForwardedCredit = billing.getCarryForwardedCredit();
		double lateCharges = billing.getLateCharges();
		
		if(dueAmount <= amount)
		{
			billing.setBillingStatus("CLOSED");			
		}
		else if (amount >= dueCash)
		{
			billing.setAmountPaid(amount);
			billing.setTotalCashDue(0);
			billing.setTotalCreditDue(dueCredit-(amount-dueCredit));
			
		}
		else 
		{
			billing.setAmountPaid(amount);
			billing.setTotalCashDue(dueCash-amount);
		}
	}

	@Override
	public void closeCard(String cardNumber) {
	
		Billing billing = billingDaoService.findBillByCardNumberStatus(cardNumber);
		
		if(instrumentDaoService.findById(cardNumber).get().getInstrumentState().equals(InstrumentState.ACTIVE) && billing == null)
		{
			instrumentDaoService.findByCardNumberActive(cardNumber).setInstrumentState(InstrumentState.CLOSED);
		}
		else
			throw new CardCanNotBeClosedException(); 
		}
}