package org.coursework1;

public class InterestCalculationManager implements Runnable {
	private final Bank bank;

	public InterestCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
		try {
			bank.addInterest();
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}
}
