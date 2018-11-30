package edu.osu.cse5234.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SHIPPING_INFO")
public class ShippingInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotEmpty(message="FirstName cannot be empty")
	@Column(name = "NAME")
	private String name;
	
	@NotEmpty(message="Address cannot be empty")
    @Size(min=5,max=20,message="Size should be between 5 and 20")
	@Column(name = "ADDRESSLINE1")
	private String addressLine1;
	
	@NotNull	
	@Column(name = "ADDRESSLINE2")
	private String addressLine2;
	
	@NotEmpty(message="City cannot be empty")
    @Size(min=5,max=20,message="Size should be between 5 and 10")
	@Column(name = "CITY")
	private String city;
	
	@NotEmpty(message="State cannot be empty")
    @Size(min=5,max=20,message="Size should be between 5 and 10")
	@Column(name = "STATE")
	private String state;
	
	@NotEmpty(message="Zip cannot be empty")
	@Pattern(regexp = "[0-9]{5}", message = "*ZipCode to be 5 digits")
	@Column(name = "ZIPCODE")
	private String zip;
	
	public ShippingInfo() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
