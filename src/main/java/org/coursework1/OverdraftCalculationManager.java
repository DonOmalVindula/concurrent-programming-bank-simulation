package org.coursework1;

public class OverdraftCalculationManager implements Runnable {
    private final Bank bank;

    public OverdraftCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.overdraftCharges();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
