package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.AddedOrderDto;
import com.batisco.fastDev.dto.OrderDto;
import com.batisco.fastDev.dto.UpdatedOrderDto;
import com.batisco.fastDev.model.Order;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class.getName());


    private final OrderService orderService;
    private final DtoMapperService mapper;

    @Autowired
    public OrderController(OrderService orderService,
                           DtoMapperService mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAll() {
        logger.info("Get all orders");
        List<OrderDto> response = mapper.mapOrdersToDto(orderService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<OrderDto> getById(@RequestParam("id") UUID orderId) {
        logger.info("Get order by id " + orderId);
        OrderDto response = mapper.mapOrderToDto(orderService.getById(orderId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDto> addApartment(@RequestBody AddedOrderDto dto) {
        logger.info("Add new order " + dto);
        Order order = orderService.add(mapper.mapToOrder(dto));
        OrderDto response = mapper.mapOrderToDto(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderDto> updateApartment(@RequestBody UpdatedOrderDto dto) {
        logger.info("Update order " + dto);
        Order order = orderService.update(mapper.mapToOrder(dto));
        OrderDto response = mapper.mapOrderToDto(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
