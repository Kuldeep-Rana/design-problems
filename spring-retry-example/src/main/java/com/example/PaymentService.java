package com.example;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGatewayService paymentGatewayService;

    public PaymentService(PaymentGatewayService paymentGatewayService) {
        this.paymentGatewayService = paymentGatewayService;
    }

    public void processPayment(String orderId) {
        System.out.println("Starting payment processing for: " + orderId);
        String result = paymentGatewayService.callExternalPaymentAPI(orderId);
        System.out.println("Result: " + result);
    }
}
