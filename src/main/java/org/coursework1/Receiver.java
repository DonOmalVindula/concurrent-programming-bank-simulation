package org.coursework1;

public class Receiver implements Runnable {
	private final Customer customer;
	private final BankAccount account;
	private final double amount;

	public Receiver(Customer customer, BankAccount account, double amount) {
		super();
		this.customer = customer;
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		// Check if the customer is present in the BankAccount object
		if(account.getCustomers() == null || !account.getCustomers().contains(customer)) {
			System.out.println(Thread.currentThread().getName() + " - Customer: " + this.customer.getCustomerID() + " not present in the account " +
					this.account.getAccountNumber() + ". Cannot withdraw money.");
			return;
		}

		// Withdraw the amount from the account
		try {
			System.out.println(Thread.currentThread().getName() + " - Customer: " + this.customer.getCustomerID() + " is present in the account " +
					this.account.getAccountNumber() + ". Can withdraw money.");
			account.withdraw(amount);
		} catch(Exception e) {
			System.out.println("Error in withdrawal "+e.getMessage());
		}
	}
}
