package com.bottomline.dto;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private long paymentId;
	private double totalAmount;
	private int noOfPayments;
	private double regularPaymentAmount;
	private double lastAmount;

}
