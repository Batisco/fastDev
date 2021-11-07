package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dto.apartment.ApartmentRequestDto;
import com.batisco.fastDev.dto.apartment.ApartmentResponseDto;
import com.batisco.fastDev.dto.apartment.ApartmentShortResponseDto;
import com.batisco.fastDev.dto.furniture.FurnitureRequestDto;
import com.batisco.fastDev.dto.furniture.FurnitureResponseDto;
import com.batisco.fastDev.dto.order.OrderResponseDto;
import com.batisco.fastDev.dto.order.OrderRequestDto;
import com.batisco.fastDev.dto.user.UserRequestDto;
import com.batisco.fastDev.dto.user.UserResponseDto;
import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.Furniture;
import com.batisco.fastDev.model.Order;
import com.batisco.fastDev.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoMapperService {

    private final UserService userService;
    private final ApartmentService apartmentService;
    private final FurnitureService furnitureService;

    @Autowired
    public DtoMapperService(UserService userService,
                            ApartmentService apartmentService,
                            FurnitureService furnitureService) {
        this.userService = userService;
        this.apartmentService = apartmentService;
        this.furnitureService = furnitureService;
    }

    public ApartmentResponseDto mapApartmentToDto(Apartment apartment) {
        ApartmentResponseDto dto = new ApartmentResponseDto();
        dto.setId(apartment.getId());
        dto.setAddress(apartment.getAddress());
        dto.setNumber(apartment.getNumber());
        dto.setDescription(apartment.getDescription());
        dto.setLevel(apartment.getLevel());
        dto.setFurnitures(
                apartment.getFurnitures().stream().
                map(this::mapFurnitureToDto).
                toList()
        );
        return dto;
    }

    public ApartmentShortResponseDto mapApartmentToShortDto(Apartment apartment) {
        ApartmentShortResponseDto dto = new ApartmentShortResponseDto();

        dto.setId(apartment.getId());
        dto.setAddress(apartment.getAddress());
        dto.setDescription(apartment.getDescription());
        dto.setLevel(apartment.getLevel());
        dto.setNumber(apartment.getNumber());

        return dto;
    }

    public Page<ApartmentResponseDto> mapApartmentsToDto(Page<Apartment> apartments) {
        return apartments.map(this::mapApartmentToDto);
    }

    public Apartment mapToApartment(ApartmentRequestDto dto) {
        Apartment apartment = new Apartment();
        apartment.setAddress(dto.getAddress());
        apartment.setNumber(dto.getNumber());
        apartment.setDescription(dto.getDescription());
        apartment.setLevel(dto.getLevel());
        apartment.setFurnitures(
                dto.getFurnitures().stream().
                        map(furnitureService::getById).
                        collect(Collectors.toList())
        );
        return apartment;
    }


    public UserResponseDto mapUserToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public Page<UserResponseDto> mapUsersToDto(Page<User> users) {
        return users.map(this::mapUserToDto);
    }

    public User mapToUser(UserRequestDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }



    public OrderResponseDto mapOrderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setUser(mapUserToDto(order.getUser()));
        dto.setApartments(order.getApartments().stream().map(this::mapApartmentToShortDto).toList());
        dto.setState(order.getState());
        dto.setPrice(order.getPrice());
        dto.setDescription(order.getDescription());
        return dto;
    }

    public Page<OrderResponseDto> mapOrdersToDto(Page<Order> orders) {
        return orders.map(this::mapOrderToDto);
    }

    public Order mapToOrder(OrderRequestDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(userService.getById(dto.getUser()));
        order.setState(dto.getState());
        order.setPrice(dto.getPrice());
        order.setDescription(dto.getDescription());
        order.setApartments(dto.getApartments().stream().map(apartmentService::getById).toList());
        return order;
    }


    public FurnitureResponseDto mapFurnitureToDto(Furniture furniture) {
        FurnitureResponseDto dto = new FurnitureResponseDto();

        dto.setId(furniture.getId());
        dto.setPrice(furniture.getPrice());
        dto.setType(furniture.getType());
        dto.setApartmentId(furniture.getApartment() != null ? furniture.getApartment().getId() : null);

        return dto;
    }

    public Page<FurnitureResponseDto> mapFurnituresToDto(Page<Furniture> furnitures) {
        return furnitures.map(this::mapFurnitureToDto);
    }

    public Furniture mapToFurniture(FurnitureRequestDto dto) {
        Furniture furniture = new Furniture();

        furniture.setId(dto.getId());
        furniture.setPrice(dto.getPrice());
        furniture.setType(dto.getType());
        apartmentService.getByFurnitureId(dto.getId()).ifPresent(furniture::setApartment);

        return furniture;
    }


}
