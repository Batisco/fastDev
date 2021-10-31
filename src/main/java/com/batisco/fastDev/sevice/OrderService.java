package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.OrderRepository;
import com.batisco.fastDev.model.Order;
import com.batisco.fastDev.model.exceptions.UnknownOrderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DtoMapperService mapper;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        DtoMapperService mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getById(UUID orderId) {
        if(orderId == null)
            throw new UnknownOrderException("Unknown order with id = " + orderId);

        return Optional.ofNullable(orderRepository.getById(orderId)).
                orElseThrow(() -> new UnknownOrderException("Unknown order with id = " + orderId));
    }

    @Transactional
    public Order add(Order orderWithoutId) {
        orderWithoutId.setId(UUID.randomUUID());

        orderRepository.saveAndFlush(orderWithoutId);

        return orderWithoutId;
    }

    @Transactional
    public Order update(Order order) {
        if(order.getId() != null && orderRepository.existsById(order.getId())) {
            orderRepository.saveAndFlush(order);
            return order;
        }
        throw new UnknownOrderException("Unknown order with id = " + order.getId());
    }

}
