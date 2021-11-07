package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.order.OrderResponseDto;
import com.batisco.fastDev.dto.order.OrderRequestDto;
import com.batisco.fastDev.model.Order;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<OrderResponseDto>> getAll(Pageable pageable) {
        logger.info("Get all orders. Params: pageable={}", pageable);
        Page<OrderResponseDto> response = mapper.mapOrdersToDto(orderService.getAll(pageable));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<OrderResponseDto> getById(@RequestParam("id") UUID orderId) {
        logger.info("Get order by id " + orderId);
        OrderResponseDto response = mapper.mapOrderToDto(orderService.getById(orderId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderResponseDto> addApartment(@RequestBody OrderRequestDto dto) {
        logger.info("Add new order " + dto);
        Order order = orderService.add(mapper.mapToOrder(dto));
        OrderResponseDto response = mapper.mapOrderToDto(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderResponseDto> updateApartment(@RequestBody OrderRequestDto dto) {
        logger.info("Update order " + dto);
        Order order = orderService.update(mapper.mapToOrder(dto));
        OrderResponseDto response = mapper.mapOrderToDto(order);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
