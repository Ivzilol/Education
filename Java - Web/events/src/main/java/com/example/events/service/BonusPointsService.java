package com.example.events.service;

import com.example.events.event.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BonusPointsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonusPointsService.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent evt) {
        LOGGER.info("Adding bonus points to user for order {}", evt.getOrderId());
    }
}
