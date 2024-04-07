package org.coursework1;

import java.text.DecimalFormat;
import java.util.List;

public class BankAccount {
	
	private final String accountNumber;
	private final List<Customer> customers;
	private double balance; // BigDecimal is recommended to use
	private AccountType accountType;
	private boolean overdraftAvailable;
	private double overdraftLimit;
	private double interestRate;
	private final DecimalFormat numberFormat = new DecimalFormat("#.00");

	public BankAccount(String accountNumber, List<Customer> customers, double balance, AccountType accountType,
			boolean overdraftAvailable, double overdraftLimit, double interestRate) {
		super();
		this.accountNumber = accountNumber;
		this.customers = customers;
		this.balance = balance;
		this.accountType = accountType;
		this.overdraftAvailable = overdraftAvailable;
		this.overdraftLimit = overdraftLimit;
		this.interestRate = interestRate;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void addCustomers(Customer customer) {
		this.customers.add(customer);
	}
	
	public boolean removeCustomer(String customerID) {
		for (Customer customer : customers) {
			if (customer.getCustomerID().equals(customerID)) {
				this.customers.remove(customer);
				return false;
			}
		}
		return true;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public synchronized boolean isOverdraftAvailable() {
		return overdraftAvailable;
	}

	public synchronized void setOverdraftAvailable(boolean overdraftAvailable) {
		this.overdraftAvailable = overdraftAvailable;
	}

	public synchronized double getOverdraftLimit() {
		return overdraftLimit;
	}

	public synchronized void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public synchronized double getBalance() {
		return balance;
	}

	public synchronized void setBalance(double balance) {
		this.balance = balance;
	}
	
	public synchronized void withdraw(double amount) throws InterruptedException, IllegalArgumentException {
		if(amount > 0) {
			while (amount > this.getBalance() + (this.isOverdraftAvailable() ? this.getOverdraftLimit() : 0)) {
				System.out.println(Thread.currentThread().getName() + " - Insufficient funds for account " +
						this.getAccountNumber() + ". Waiting for deposit to withdraw...");
				wait();
			}

			this.setBalance(this.getBalance() - amount);
			System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(amount) +
					" withdrawn successfully from account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
		} else {
			throw new IllegalArgumentException("Withdraw amount cannot be 0 or below");
		}
	}
	
	public synchronized void deposit(double amount) throws IllegalArgumentException {
		if(amount > 0) {
			this.setBalance(this.getBalance() + amount);
			System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(amount) +
					" deposited successfully to account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
			notifyAll(); // Notify waiting threads (e.g., for withdrawal)
		} else {
			throw new IllegalArgumentException("Deposit amount cannot be 0 or below");
		}
	}
	
	public synchronized void addInterest(double amount) {
		this.setBalance(this.getBalance() + amount);
		System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(amount) +
				" added interest to account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
		notifyAll(); // Notify waiting threads (e.g., for withdrawal)
	}
	
	public synchronized void deductIncomeTax(double amount) throws InterruptedException, IllegalArgumentException  {
		if(amount > 0) {
			while (amount > this.getBalance()) {
				System.out.println(Thread.currentThread().getName() + " - Insufficient funds for account " +
						this.getAccountNumber() + ". Waiting for deposit to deduct tax...");
				wait();
			}

			this.setBalance(this.getBalance() - amount);
			System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(amount) +
					" deducted taxes from account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
		} else {
			throw new IllegalArgumentException("Tax Amount cannot be 0 or below");
		}
	}

	public synchronized void deductAnnualCharges(double annualCharges) throws InterruptedException {
		while (annualCharges > this.getBalance()) {
			System.out.println(Thread.currentThread().getName() + " - Insufficient funds for account " +
					this.getAccountNumber() + ". Waiting for deposit to deduct annual charges...");
			wait();
		}

		this.setBalance(this.getBalance() - annualCharges);
		System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(annualCharges) +
					" deducted annual charges from account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
	}

	public synchronized void deductOverdraftCharges(double overdraftCharges) throws InterruptedException {
		while (overdraftCharges > this.getBalance()) {
			System.out.println(Thread.currentThread().getName() + " - Insufficient funds for account " +
					this.getAccountNumber() + ". Waiting for deposit to deduct overdraft charges...");
			wait();
		}

		this.setBalance(this.getBalance() - overdraftCharges);
		System.out.println(Thread.currentThread().getName() + " - Amount " + numberFormat.format(overdraftCharges) +
				" deducted overdraft charges from account " + this.getAccountNumber() + ". Balance: " + numberFormat.format(this.getBalance()));
	}
}
