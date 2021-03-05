package org.neethaudupi.hrms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * This entity is used to hold address information of an employee
 */
@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	
	@NotEmpty(message="Address is required")
	private String addressLine1;
	private String addressLine2;
	
	@NotEmpty(message="City is required")
	private String city;
	
	@NotEmpty(message="State is required")
	private String userState;
	
	@NotEmpty(message="Country is required")
	private String country;
	
	@NotNull(message="Zipcode is required")
	private int zipcode;
		

	public Address() {
		super();
	}
	
	public Address(int addressId, @NotEmpty(message = "Address is required") String addressLine1, String addressLine2,
			@NotEmpty(message = "City is required") String city,
			@NotEmpty(message = "State is required") String userState,
			@NotEmpty(message = "Country is required") String country,
			@NotNull(message = "Zipcode is required") int zipcode) {
		super();
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.userState = userState;
		this.country = country;
		this.zipcode = zipcode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
}
