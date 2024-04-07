package org.coursework1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Banking App.
 */
public class AppTest extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    private BankAccount createSampleAccount() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("CUST001", "John", "Doe", "555-0101", "123 Elm St, Springfield", "S1234567A");
        customers.add(customer);

        return new BankAccount("ACC001", customers, 1000, AccountType.REGULAR, false, 0, 1.5);
    }

    public void testAccountCreation() {
        Bank bank = new Bank();
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer("CUST001", "John", "Doe", "555-0101", "123 Elm St, Springfield", "S1234567A");
        Customer customer2 = new Customer("CUST002", "Jane", "Doe", "555-0102", "124 Elm St, Springfield", "S1234567B");

        customers.add(customer1);
        customers.add(customer2);
        BankAccount account = bank.createAccount("ACC001", customers, 1000, AccountType.REGULAR, false, 0, 1.5);

        assertNotNull("Account should be created successfully.", account);
    }

    public void testDeposit() {
        BankAccount account = createSampleAccount();
        account.deposit(500);
        assertEquals("Balance should be 1500 after depositing 500.", 1500.0, account.getBalance());
    }

    public void testWithdrawalWithSufficientBalance() throws InterruptedException {
        BankAccount account = createSampleAccount();
        account.withdraw(500);
        assertEquals("Balance should be 500 after withdrawing 500.", 500.0, account.getBalance());
    }

    public void testWithdrawalWithOverdraftProtection() throws InterruptedException {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("CUST001", "John", "Doe", "555-0101", "123 Elm St, Springfield", "S1234567A");
        customers.add(customer);

        BankAccount account =  new BankAccount("ACC001", customers, 1000, AccountType.REGULAR, true, 200, 1.5);
        account.withdraw(1100); // Assuming the account has a $1000 balance and $200 overdraft limit
        assertEquals("Balance should not exceed overdraft limit.", -100.0, account.getBalance());
    }


    public void testConcurrentDeposits() throws InterruptedException {
        final BankAccount account = createSampleAccount();
        Thread t1 = new Thread(() -> account.deposit(500));
        Thread t2 = new Thread(() -> account.deposit(500));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertEquals("Balance should be 2000 after two concurrent deposits of 500 each.", 2000.0, account.getBalance());
    }

    public void testWithdrawalBlocking() throws InterruptedException {
        final BankAccount account = createSampleAccount(); // Initial balance 1000
        Thread depositThread = new Thread(() -> {
            try {
                Thread.sleep(100); // Simulate delay
                account.deposit(500); // This will unblock the withdrawal thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread withdrawalThread = new Thread(() -> {
            try {
                account.withdraw(1200); // This will initially block, waiting for funds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        withdrawalThread.start();
        depositThread.start();

        withdrawalThread.join();
        depositThread.join();

        assertEquals("Balance should be 300 after withdrawing 1200 and depositing 500.", 300.0, account.getBalance());
    }

    public void testConcurrentDepositsAndWithdrawals() throws InterruptedException {
        final BankAccount account = createSampleAccount(); // Initial balance is $1000

        Runnable deposit = () -> account.deposit(1000); // Deposit $1000
        Runnable withdraw = () -> {
            try {
                account.withdraw(500); // Withdraw $500
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread[] depositThreads = new Thread[5];
        Thread[] withdrawThreads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            depositThreads[i] = new Thread(deposit);
            withdrawThreads[i] = new Thread(withdraw);

            depositThreads[i].start();
            withdrawThreads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            depositThreads[i].join();
            withdrawThreads[i].join();
        }

        assertEquals( "Final balance should reflect all deposits and withdrawals.", 3500.0, account.getBalance());
    }

    public void testHighConcurrencyOnSingleAccount() throws InterruptedException {
        final BankAccount account = createSampleAccount();
        final int iterations = 1000; // Number of operations
        final double depositAmount = 10; // Fixed deposit amount
        final double withdrawalAmount = 5; // Fixed withdrawal amount
        List<Thread> threads = new ArrayList<>();

        // Create threads for deposit
        for (int i = 0; i < iterations; i++) {
            Thread t = new Thread(() -> account.deposit(depositAmount));
            threads.add(t);
            t.start();
        }

        // Create threads for withdrawal
        for (int i = 0; i < iterations; i++) {
            Thread t = new Thread(() -> {
                try {
                    account.withdraw(withdrawalAmount);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(t);
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : threads) {
            t.join();
        }

        double expectedBalance = 1000 + (depositAmount - withdrawalAmount) * iterations;
        assertEquals("The final balance should match the expected balance after all operations.",expectedBalance, account.getBalance());
    }



}
