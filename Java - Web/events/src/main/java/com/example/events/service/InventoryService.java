package com.example.events.service;

import com.example.events.event.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent evt) {
        LOGGER.info("Decreasing inventory points to user for order {}", evt.getOrderId());
    }
}
