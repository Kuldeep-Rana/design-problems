package com.example;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PaymentGatewayService {

    private final AtomicInteger attemptCounter = new AtomicInteger(0);

    @Retryable(
            value = {RuntimeException.class}, // Retry on these exceptions
            maxAttempts = 4,                   // 1 initial + 3 retries
            backoff = @Backoff(delay = 2000, multiplier = 2) // exponential backoff: 2s, 4s, 8s
    )
    public String callExternalPaymentAPI(String orderId) {
        int attempt = attemptCounter.incrementAndGet();
        System.out.println("[" + LocalTime.now() + "] Attempt " + attempt + " for orderId: " + orderId);

        // Simulate transient failure for first 3 attempts
        if (attempt < 4) {
            throw new RuntimeException("Payment gateway timeout");
        }

        // Success on the 4th attempt
        return "✅ Payment processed successfully for orderId: " + orderId;
    }

    @Recover
    public String recover(RuntimeException ex, String orderId) {
        System.out.println("❌ All retries exhausted for orderId: " + orderId);
        return "Fallback: Could not process payment for orderId: " + orderId + " due to " + ex.getMessage();
    }
}
