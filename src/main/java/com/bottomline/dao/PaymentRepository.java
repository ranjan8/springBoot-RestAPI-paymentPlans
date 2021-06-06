package com.bottomline.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bottomline.bo.PaymentBO;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentBO, Long>{

	@Query("Select p from PaymentBO p")
	public List<PaymentBO> getAllPaymentPlans();

	@Query("Select p from PaymentBO p where p.totalAmount =?1 and p.noOfPayments =?2")
	public Optional<PaymentBO> findByAmountAndNoOfPayment(double amount, int noOfPayment);

}
