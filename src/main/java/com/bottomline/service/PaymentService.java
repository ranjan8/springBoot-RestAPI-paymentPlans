package com.bottomline.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bottomline.bo.PaymentBO;
import com.bottomline.dao.PaymentRepository;
import com.bottomline.dto.PaymentDTO;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public PaymentDTO createPaymentplan(PaymentBO paymentBo) {
		//deviding the totalAmount by noOfPayments to get the regularAmount
		double regularPaymentAmount = paymentBo.getTotalAmount() / paymentBo.getNoOfPayments();
		
		//getting the 2 digit decimal no. 
		regularPaymentAmount = round(regularPaymentAmount);

		//calculating the totalRegular payment after getting the 2digit decimal value with noOfPayments
		double totalRegularPayment = regularPaymentAmount * paymentBo.getNoOfPayments();

		//caculating the difference between totalAmount and the calculated totalRegularPayment value.
		double difference = paymentBo.getTotalAmount() - totalRegularPayment;

		double lastAmount = 0;

		//if the difference value is not equal to Zero, need to add the regularPaymentValue with the difference 
		if (difference != 0)
			lastAmount = round(regularPaymentAmount + difference);

		paymentBo.setRegularPaymentAmount(regularPaymentAmount);
		paymentBo.setLastAmount(lastAmount);

		//saving to database
		paymentBo = paymentRepository.save(paymentBo);
		
		//using ModelMapper class to bind BO to DTO
		ModelMapper modelMapper = new ModelMapper();
		PaymentDTO paymentDTO = modelMapper.map(paymentBo, PaymentDTO.class);
		paymentDTO.setTotalAmount(5444333222.88);
		return paymentDTO;
	}

	public List<PaymentDTO> getAllPaymentPlans() {
		ModelMapper modelMapper = new ModelMapper();
		List<PaymentBO> bo = paymentRepository.getAllPaymentPlans(); 
		//iterating all the values from List of BO to List of dto object
		List<PaymentDTO> dtos = bo
				  .stream()
				  .map(b -> modelMapper.map(b, PaymentDTO.class))
				  .collect(Collectors.toList());
		return dtos;
	}

	public static double round(double value) {
		return Double.valueOf(String.format("%.02f", value));
	}

	public PaymentDTO getPaymentPlanById(double amount, int noOfPayment) {
		ModelMapper modelMapper = new ModelMapper();
		PaymentDTO paymentDTO = null;
		
		//finding the existing record with custom query
		Optional<PaymentBO> paymentBo = paymentRepository.findByAmountAndNoOfPayment(amount, noOfPayment);
		if (paymentBo.isPresent())
			paymentDTO = modelMapper.map(paymentBo.get(), PaymentDTO.class);
		
		return paymentDTO;
	}

}
