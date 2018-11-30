package edu.osu.cse5234.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PAYMENT_INFO")
public class PaymentInfo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@NotEmpty(message = "*Credit Card Number cannot be null")
	@Pattern(regexp = "[0-9]{16}", message = "*CreditCardNumber to be 16 digits")	
	@Column(name = "CARD_NUMBER")
	private String creditCardNum;
	
	@NotEmpty(message = "*Enter the expiry date")
	@Column(name = "EXP_DATE")
	private String expDate;
	
	@NotEmpty(message = "*Enter cvv code")
	@Pattern(regexp = "[0-9]{3}", message = "*CVV code has to be 3 digits")
	@Column(name = "CVV_CODE")
	private String cvvCode;
	
	@NotEmpty(message = "*Enter the cardholder's name")
	@Column(name = "CARD_HOLDER_NAME")
	private String cardHolderName;
	
	@Column(name = "CONFIRM_CODE")
	private String confirm_code;
	
	public String getConfirm_code() {
		return confirm_code;
	}
	public void setConfirm_code(String confirm_code) {
		this.confirm_code = confirm_code;
	}
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
