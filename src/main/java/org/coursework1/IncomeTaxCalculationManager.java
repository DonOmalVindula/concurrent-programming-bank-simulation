package org.coursework1;

public class IncomeTaxCalculationManager implements Runnable {
	private final Bank bank;

	public IncomeTaxCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
		// exception handling must be written
        try {
            bank.deductIncomeTax();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
