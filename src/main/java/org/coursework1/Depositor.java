package org.coursework1;

public class Depositor implements Runnable {
	private final BankAccount account;
	private final double amount;

	public Depositor(BankAccount account, double amount) {
		super();
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		// Anyone can deposit money
		try {
			account.deposit(amount);
		} catch(Exception e) {
			System.out.println("Error in deposit: " + e.getMessage());
		}
	}
}
