package com.example.events.service;

import com.example.events.event.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent evt) {
        LOGGER.info("Sending email to user for order {}", evt.getOrderId());
    }
}
