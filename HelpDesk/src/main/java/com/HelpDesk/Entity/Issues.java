package com.HelpDesk.Entity;

import com.HelpDesk.DOA.Status;
import com.HelpDesk.Service.CustomerSupportRepresentativeService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Issues {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 300, nullable = false)
	private String issue;
	
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="Customer_id")
	private Customer customer;
	
	@Column(length =300)
	private String reply;
	
	@ManyToOne
	@JoinColumn(name = "CustomerSupportRepresentative_id")
	private CustomerSupportRepresentative CustomerSupportRepresentative;

	public Issues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Issues(String issue, Status status, Customer customer, CustomerSupportRepresentative CustomerSupportRepresentative, String reply) {
		super();
		this.issue = issue;
		this.status = status;
		this.customer = customer;
		this.reply = reply;
		this.CustomerSupportRepresentative = CustomerSupportRepresentative;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public CustomerSupportRepresentative getCustomerSupportRepresentative() {
		return CustomerSupportRepresentative;
	}

	public void setCustomerSupportRepresentativeService(
			CustomerSupportRepresentative customerSupportRepresentative) {
		CustomerSupportRepresentative = customerSupportRepresentative;
	}

	@Override
	public String toString() {
		return "Issues [id=" + id + ", issue=" + issue + ", status=" + status + ", customer=" + customer + ", reply="
				+ reply + ", Customer Support Representative=" + CustomerSupportRepresentative + "]";
	}
	
}
