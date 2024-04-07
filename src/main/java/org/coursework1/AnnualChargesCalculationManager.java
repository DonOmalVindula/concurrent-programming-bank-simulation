package org.coursework1;

public class AnnualChargesCalculationManager implements Runnable {
	private final Bank bank;

	public AnnualChargesCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
        try {
            bank.annualCharges();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
