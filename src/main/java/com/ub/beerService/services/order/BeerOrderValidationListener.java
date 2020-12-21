package com.ub.beerService.services.order;

import com.ub.beerService.config.JMSConfig;
import com.ub.brewery.events.ValidateOrderRequest;
import com.ub.brewery.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrderDto());

        jmsTemplate.convertAndSend(JMSConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult
                        .builder()
                        .orderId(validateOrderRequest.getBeerOrderDto().getId())
                        .isValid(isValid)
                        .build());
    }
}
