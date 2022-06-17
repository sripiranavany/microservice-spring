package com.sripiranavan.order.service;

import com.sripiranavan.order.dto.OrderLineItemsDto;
import com.sripiranavan.order.dto.OrderRequest;
import com.sripiranavan.order.model.Order;
import com.sripiranavan.order.model.OrderLineItems;
import com.sripiranavan.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItems;
    }
}
