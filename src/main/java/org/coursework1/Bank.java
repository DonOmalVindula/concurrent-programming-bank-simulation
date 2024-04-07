package org.coursework1;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<BankAccount> accounts = new ArrayList<>();

    public BankAccount createAccount(String accountNumber, List<Customer> customer, double initialBalance, AccountType accountType, boolean overdraftFacilities, double overdraftLimit, double interestRate) {
		BankAccount account = new BankAccount(accountNumber, customer, initialBalance, accountType, overdraftFacilities, overdraftLimit, interestRate);
		accounts.add(account);
		return account;
	}
	
	public boolean removeAccount(String accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				this.accounts.remove(account);
				return true;
			}
		}
		return false;
	}
	
	public BankAccount getAccount(String accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;				
			}
		}	
		return null;
	}
	
	public void addInterest() throws IllegalArgumentException {
		for (BankAccount account : accounts) {
			if (account.getBalance() > 0) {
				double interest = account.getBalance() * account.getInterestRate() / 100 / 12;
				account.addInterest(interest);
			} else {
				throw new IllegalArgumentException("Interest cannot be calculated for account with negative balance.");
			}
		}
	}
	
	public void deductIncomeTax() throws InterruptedException {
		for (BankAccount account : accounts) {
			if (account.getBalance() > 0) {
				double interest = account.getBalance() * account.getInterestRate() / 100 / 12;
				double tax = interest * .36;
				account.deductIncomeTax(tax);
			}
		}
	}
	
	public void annualCharges() throws InterruptedException {
		// Apply annual charges only for Regular Account
		for (BankAccount account : accounts) {
			if (account.getAccountType() == AccountType.REGULAR) {
				double annualCharges = 50;
				account.deductAnnualCharges(annualCharges);
			}
		}
	}
	
	public void overdraftCharges() throws InterruptedException {
		// If overdraft available, charge for both type of accounts
		for (BankAccount account : accounts) {
			if (account.isOverdraftAvailable()) {
                double overdraftCharges = 100;
                account.deductOverdraftCharges(overdraftCharges);
			}
		}
	}
}
