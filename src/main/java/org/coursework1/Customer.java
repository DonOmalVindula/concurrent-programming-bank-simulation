package org.coursework1;

public class Customer {
	private final String customerID;
	private final String firstName;
	private final String lastName;
	private String contactNo;
	private String address;
	private final String nicNo;
	
	public Customer(String customerID, String firstName, String lastName, String contactNo, String address,
			String nicNo) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.address = address;
		this.nicNo = nicNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNicNo() {
		return nicNo;
	}
}
