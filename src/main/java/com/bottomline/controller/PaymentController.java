package com.bottomline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bottomline.bo.PaymentBO;
import com.bottomline.dto.PaymentDTO;
import com.bottomline.exhandling.ResourceNotFoundException;
import com.bottomline.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	//creating an new payment plan record
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	private ResponseEntity<PaymentDTO> createPaymentPlan(@RequestBody PaymentDTO paymentDto) {
		//checking whether the payment plan is already exist.
		PaymentDTO dto = paymentService.getPaymentPlanById(paymentDto.getTotalAmount(), paymentDto.getNoOfPayments());
		
		//if no, create a new record
		if (dto == null) {
			PaymentBO paymentBo = new PaymentBO();
			
			paymentBo.setTotalAmount(paymentDto.getTotalAmount());
			paymentBo.setNoOfPayments(paymentDto.getNoOfPayments());
			paymentDto = paymentService.createPaymentplan(paymentBo);
			
			return new ResponseEntity<>(paymentDto, HttpStatus.CREATED);
		} 
		else {//if yes, return the existing record
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
	}

	//getting all the payment plans existing in the system
	@GetMapping("/getAllPlans")
	@ResponseStatus(HttpStatus.OK)
	private List<PaymentDTO> getAllPaymentPlans() {
		return paymentService.getAllPaymentPlans();
	}

	
	//getting specific payment plan based on amount and noOfPayment
	@GetMapping("/getPlan/{amount}/{noOfPayment}")
	private ResponseEntity<PaymentDTO> getPaymentPlanById(@PathVariable("amount") double amount,
			@PathVariable("noOfPayment") int noOfPayment) {
		PaymentDTO dto = paymentService.getPaymentPlanById(amount, noOfPayment);
		if (null == dto)
			throw new ResourceNotFoundException(
					"No Payment plans found with amount = " + amount + " and noOfPayment = " + noOfPayment);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
