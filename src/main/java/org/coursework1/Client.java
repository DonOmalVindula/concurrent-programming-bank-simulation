package org.coursework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// simulation of a bank with multiple customers and bank accounts
public class Client {

	public static void main(String[] args) {
		Bank bank = new Bank();
		Random rand = new Random();

		List<BankAccount> accounts = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();

		// Creating thread groups for different actors
		ThreadGroup customerVIP = new ThreadGroup("VIP Customer");
		ThreadGroup regularCustomer = new ThreadGroup("Regular Customer");
		ThreadGroup bankManager = new ThreadGroup("BankManagers");

		// Setting priority for thread groups
		customerVIP.setMaxPriority(10);
		regularCustomer.setMaxPriority(5);
		bankManager.setMaxPriority(8);

		// List of customers
		Customer customer1 = new Customer("CUST001", "John", "Doe", "555-0101", "123 Elm St, Springfield", "S1234567A");
		Customer customer2 = new Customer("CUST002", "Jane", "Doe", "555-0102", "124 Elm St, Springfield", "S1234567B");
		Customer customer3 = new Customer("CUST003", "Jim", "Doe", "555-0103", "125 Elm St, Springfield", "S1234567C");
		Customer customer4 = new Customer("CUST004", "Jill", "Doe", "555-0104", "126 Elm St, Springfield", "S1234567D");
		Customer customer5 = new Customer("CUST005", "Jack", "Doe", "555-0105", "127 Elm St, Springfield", "S1234567E");
		Customer customer6 = new Customer("CUST006", "Jenny", "Doe", "555-0106", "128 Elm St, Springfield", "S1234567F");
		Customer customer7 = new Customer("CUST007", "Jesse", "Doe", "555-0107", "129 Elm St, Springfield", "S1234567G");
		Customer customer8 = new Customer("CUST008", "Jasmine", "Doe", "555-0108", "130 Elm St, Springfield", "S1234567H");
		Customer customer9 = new Customer("CUST009", "Jared", "Doe", "555-0109", "131 Elm St, Springfield", "S1234567I");
		Customer customer10 = new Customer("CUST010", "Jocelyn", "Doe", "555-0110", "132 Elm St, Springfield", "S1234567J");
		Customer customer11 = new Customer("CUST011", "Jasper", "Doe", "555-0111", "133 Elm St, Springfield", "S1234567K");
		Customer customer12 = new Customer("CUST012", "Jenna", "Doe", "555-0112", "134 Elm St, Springfield", "S1234567L");
		Customer customer13 = new Customer("CUST013", "Javier", "Doe", "555-0113", "135 Elm St, Springfield", "S1234567M");
		Customer customer14 = new Customer("CUST014", "Jasmine", "Doe", "555-0114", "136 Elm St, Springfield", "S1234567N");
		Customer customer15 = new Customer("CUST015", "Jared", "Doe", "555-0115", "137 Elm St, Springfield", "S1234567O");
		Customer customer16 = new Customer("CUST016", "Jocelyn", "Doe", "555-0116", "138 Elm St, Springfield", "S1234567P");
		Customer customer17 = new Customer("CUST017", "Jasper", "Doe", "555-0117", "139 Elm St, Springfield", "S1234567Q");
		Customer customer18 = new Customer("CUST018", "Jenna", "Doe", "555-0118", "140 Elm St, Springfield", "S1234567R");
		Customer customer19 = new Customer("CUST019", "Javier", "Doe", "555-0119", "141 Elm St, Springfield", "S1234567S");
		Customer customer20 = new Customer("CUST020", "Jasmine", "Doe", "555-0120", "142 Elm St, Springfield", "S1234567T");

		// Adding customers to the list
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);
        customers.add(customer10);
        customers.add(customer11);
        customers.add(customer12);
        customers.add(customer13);
        customers.add(customer14);
        customers.add(customer15);
        customers.add(customer16);
        customers.add(customer17);
        customers.add(customer18);
        customers.add(customer19);
        customers.add(customer20);

        // Creating 5 bank accounts with varied attributes and no customers
		BankAccount account1 = bank.createAccount("ACC001", new ArrayList<>(), 1000, AccountType.REGULAR, false, 0, 10);
		BankAccount account2 = bank.createAccount("ACC002", new ArrayList<>(), 2000, AccountType.VIP, true, 500, 20);
		BankAccount account3 = bank.createAccount("ACC003", new ArrayList<>(), 1500, AccountType.REGULAR, true, 300, 15);
		BankAccount account4 = bank.createAccount("ACC004", new ArrayList<>(), 2500, AccountType.VIP, false, 0, 2.5);
		BankAccount account5 = bank.createAccount("ACC005", new ArrayList<>(), 3000, AccountType.REGULAR, true, 600, 10);

		// Adding customers to bank accounts. Each account has 4 customers
		account1.addCustomers(customer1);
		account1.addCustomers(customer2);
		account1.addCustomers(customer3);
		account1.addCustomers(customer4);
		account1.addCustomers(customer5);
		account1.addCustomers(customer6);
		account1.addCustomers(customer7);
		account1.addCustomers(customer8);

		account2.addCustomers(customer5);
		account2.addCustomers(customer6);
		account2.addCustomers(customer7);
		account2.addCustomers(customer8);
		account2.addCustomers(customer9);
		account2.addCustomers(customer10);
		account2.addCustomers(customer11);
		account2.addCustomers(customer12);

		account3.addCustomers(customer9);
		account3.addCustomers(customer10);
		account3.addCustomers(customer11);
		account3.addCustomers(customer12);
		account3.addCustomers(customer13);
		account3.addCustomers(customer14);
		account3.addCustomers(customer15);
		account3.addCustomers(customer16);

		account4.addCustomers(customer13);
		account4.addCustomers(customer14);
		account4.addCustomers(customer15);
		account4.addCustomers(customer16);
		account4.addCustomers(customer17);
		account4.addCustomers(customer18);
		account4.addCustomers(customer19);
		account4.addCustomers(customer20);

		account5.addCustomers(customer17);
		account5.addCustomers(customer18);
		account5.addCustomers(customer19);
		account5.addCustomers(customer20);
		account5.addCustomers(customer1);
		account5.addCustomers(customer2);
		account5.addCustomers(customer3);
		account5.addCustomers(customer4);

		// Adding bank accounts to the list
		accounts.add(account1);
		accounts.add(account2);
		accounts.add(account3);
		accounts.add(account4);
		accounts.add(account5);

		// Creating 3 threads for bank managers
		Thread annualChargesCalculationManagerThread = new Thread(bankManager, new AnnualChargesCalculationManager(bank), "Annual Charges Calculation Manager");
		Thread incomeTaxCalculationManagerThread = new Thread(bankManager, new IncomeTaxCalculationManager(bank), "Income Tax Calculation Manager");
		Thread interestCalculationManagerThread = new Thread(bankManager, new InterestCalculationManager(bank), "Interest Calculation Manager");
		Thread overdraftCalculationManagerThread = new Thread(bankManager, new OverdraftCalculationManager(bank), "Overdraft Calculation Manager");
		threads.add(interestCalculationManagerThread);
		threads.add(incomeTaxCalculationManagerThread);
		threads.add(annualChargesCalculationManagerThread);
		threads.add(overdraftCalculationManagerThread);

		// Create 5 depositor regular threads
		for (int i = 0; i < 5; i++) {
			BankAccount selectedAccount = accounts.get(rand.nextInt(accounts.size()));
			double depositAmount = 1000 + 1000 * rand.nextInt(9); // Random deposit amount between 0 and 10000
			Thread depositorThread = new Thread(regularCustomer, new Depositor(selectedAccount, depositAmount), "Depositor " + (i + 1));
			threads.add(depositorThread);
		}

		// Create 5 depositor VIP threads
		for (int i = 0; i < 5; i++) {
			BankAccount selectedAccount = accounts.get(rand.nextInt(accounts.size()));
			double depositAmount = 1000 + 1000 * rand.nextInt(9); // Random deposit amount between 1000 and 10000
			Thread depositorThread = new Thread(customerVIP, new Depositor(selectedAccount, depositAmount), "Depositor " + (i + 6));
			threads.add(depositorThread);
		}

		// Create 5 receiver regular threads
		for (int i = 0; i < 5; i++) {
			BankAccount selectedAccount = accounts.get(rand.nextInt(accounts.size()));
			Customer selectedCustomer = customers.get(rand.nextInt(customers.size()));
			double withdrawalAmount = 500 + 500 * rand.nextInt(9); // Random withdrawal amount between 500 and 5000
			Thread receiverThread = new Thread(regularCustomer, new Receiver(selectedCustomer, selectedAccount, withdrawalAmount), "Receiver " + (i + 1));
			threads.add(receiverThread);
		}

		// Create 5 receiver VIP threads
		for (int i = 0; i < 5; i++) {
			BankAccount selectedAccount = accounts.get(rand.nextInt(accounts.size()));
			Customer selectedCustomer = customers.get(rand.nextInt(customers.size()));
			double withdrawalAmount = 500 + 500 * rand.nextInt(9); // Random withdrawal amount between 500 and 5000
			Thread receiverThread = new Thread(customerVIP, new Receiver(selectedCustomer, selectedAccount, withdrawalAmount), "Receiver " + (i + 6));
			threads.add(receiverThread);
		}

		// Start all threads
		for (Thread thread: threads) {
			thread.start();
		}
	}
}
