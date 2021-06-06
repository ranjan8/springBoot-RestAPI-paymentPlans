package com.bottomline.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PaymentBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;
	private double totalAmount;
	private int noOfPayments;
	private double regularPaymentAmount;
	private double lastAmount;

}
