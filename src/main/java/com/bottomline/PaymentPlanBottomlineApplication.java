package com.bottomline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.bottomline.*")
public class PaymentPlanBottomlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentPlanBottomlineApplication.class, args);
	}

}
