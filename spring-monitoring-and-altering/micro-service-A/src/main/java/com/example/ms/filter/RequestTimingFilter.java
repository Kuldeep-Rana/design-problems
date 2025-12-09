package com.example.ms.filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.io.IOException;
import java.time.Duration;

@Component
public class RequestTimingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestTimingFilter.class);
    private final MeterRegistry meterRegistry;
    private final Timer requestTimer;

    public RequestTimingFilter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.requestTimer = Timer.builder("app.request.duration")
                .description("Request duration in ms")
                .publishPercentiles(0.5, 0.95)
                .register(meterRegistry);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long timeMs = System.currentTimeMillis() - start;
            // record to micrometer timer
            requestTimer.record(Duration.ofMillis(timeMs));
            // structured log: URL, method, status, time
            log.info("req completed url={} method={} status={} durationMs={}",
                    request.getRequestURI(), request.getMethod(), response.getStatus(), timeMs);
        }
    }
}
