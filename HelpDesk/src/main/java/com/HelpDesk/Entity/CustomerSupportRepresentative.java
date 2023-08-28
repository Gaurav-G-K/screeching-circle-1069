package com.HelpDesk.Entity;

import com.HelpDesk.Service.CustomerSupportRepresentativeService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerSupportRepresentative {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 40,nullable = false, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false,unique = true)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String password;

	public CustomerSupportRepresentative() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerSupportRepresentative(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerSupportRepresentative [id=" + id + ", email=" + email + ", name=" + name + ", password="
				+ password + "]";
	}
	
}
