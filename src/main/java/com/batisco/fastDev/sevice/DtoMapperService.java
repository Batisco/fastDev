package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dto.*;

import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.Order;
import com.batisco.fastDev.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoMapperService {

    private final UserService userService;
    private final ApartmentService apartmentService;

    @Autowired
    public DtoMapperService(UserService userService,
                            ApartmentService apartmentService) {
        this.userService = userService;
        this.apartmentService = apartmentService;
    }

    public ApartmentDto mapApartmentToDto(Apartment apartment) {
        ApartmentDto dto = new ApartmentDto();
        dto.setId(apartment.getId());
        dto.setAddress(apartment.getAddress());
        dto.setNumber(apartment.getNumber());
        dto.setDescription(apartment.getDescription());
        dto.setLevel(apartment.getLevel());
        return dto;
    }

    public List<ApartmentDto> mapApartmentsToDto(List<Apartment> apartments) {
        return apartments.stream().
                map(this::mapApartmentToDto).
                toList();
    }

    public Apartment mapToApartment(ApartmentDto dto) {
        Apartment apartment = new Apartment();
        apartment.setId(dto.getId());
        apartment.setAddress(dto.getAddress());
        apartment.setNumber(dto.getNumber());
        apartment.setDescription(dto.getDescription());
        apartment.setLevel(dto.getLevel());
        return apartment;
    }

    public Apartment mapToApartment(AddedApartmentDto dto) {
        Apartment apartment = new Apartment();
        apartment.setAddress(dto.getAddress());
        apartment.setNumber(dto.getNumber());
        apartment.setDescription(dto.getDescription());
        apartment.setLevel(dto.getLevel());
        return apartment;
    }


    public UserDto mapUserToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public List<UserDto> mapUsersToDto(List<User> users) {
        return users.stream().
                map(this::mapUserToDto).
                toList();
    }

    public User mapToUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }

    public User mapToUser(AddedUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        return user;
    }


    public OrderDto mapOrderToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUser(mapUserToDto(order.getUser()));
        dto.setApartments(order.getApartments().stream().map(this::mapApartmentToDto).toList());
        dto.setState(order.getState());
        dto.setPrice(order.getPrice());
        dto.setDescription(order.getDescription());
        return dto;
    }

    public List<OrderDto> mapOrdersToDto(List<Order> orders) {
        return orders.stream().
                map(this::mapOrderToDto).
                toList();
    }

    public Order mapToOrder(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(mapToUser(dto.getUser()));
        order.setApartments(dto.getApartments().stream().map(this::mapToApartment).toList());
        order.setState(dto.getState());
        order.setPrice(dto.getPrice());
        order.setDescription(dto.getDescription());
        return order;
    }

    public Order mapToOrder(AddedOrderDto dto) {
        Order order = new Order();
        order.setUser(userService.getById(dto.getUser()));
        order.setApartments(dto.getApartments().stream().map(apartmentService::getById).toList());
        order.setState(dto.getState());
        order.setPrice(dto.getPrice());
        order.setDescription(dto.getDescription());
        return order;
    }

    public Order mapToOrder(UpdatedOrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userService.getById(dto.getUser()));
        order.setApartments(dto.getApartments().stream().map(apartmentService::getById).toList());
        order.setState(dto.getState());
        order.setPrice(dto.getPrice());
        order.setDescription(dto.getDescription());
        return order;
    }

}
