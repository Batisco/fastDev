package com.batisco.fastDev.dto;

import com.batisco.fastDev.model.OrderState;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDto {

    private UUID id;
    private UserDto user;
    private List<ApartmentDto> apartments;
    private OrderState state;
    private BigDecimal price;
    private String description;

    public OrderDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<ApartmentDto> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentDto> apartments) {
        this.apartments = apartments;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(user, orderDto.user) &&
                Objects.equals(apartments, orderDto.apartments) &&
                state == orderDto.state &&
                Objects.equals(price, orderDto.price) &&
                Objects.equals(description, orderDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, apartments, state, price, description);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", user=" + user +
                ", apartments=" + apartments +
                ", state=" + state +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
