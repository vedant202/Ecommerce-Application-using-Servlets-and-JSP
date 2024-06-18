package com.vedant_servelets.entities;


import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fname;
	private String lname;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private Address address;

	@ColumnDefault(value = "'USER'")
	private String role;

	@Column(unique=true)
	private String email;

	private String password;
	@Column(unique=true)
	private String phoneNumber;

	@Basic
	private Timestamp date;



	public User() {
		super();
	}



	public User(String fname, String lname, Address address, String email, String password, String phoneNumber,
			Timestamp date) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.date = date;
	}



	public User(String fname, String lname, Address address, String role, String email, String password,
			String phoneNumber, Timestamp date) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.role = role;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.date = date;
	}



	public User(long id, String fname, String lname, Address address, String email, String password, String phoneNumber,
			Timestamp date) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.date = date;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address=" + address + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", date=" + date + "]";
	}





}
