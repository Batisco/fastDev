package com.batisco.fastDev.dto;

import com.batisco.fastDev.model.OrderState;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UpdatedOrderDto {

    private UUID id;
    private UUID user;
    private List<UUID> apartments;
    private OrderState state;
    private BigDecimal price;
    private String description;

    public UpdatedOrderDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public List<UUID> getApartments() {
        return apartments;
    }

    public void setApartments(List<UUID> apartments) {
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
        UpdatedOrderDto that = (UpdatedOrderDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(apartments, that.apartments) &&
                state == that.state &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, apartments, state, price, description);
    }

    @Override
    public String toString() {
        return "UpdatedOrderDto{" +
                "id=" + id +
                ", user=" + user +
                ", apartments=" + apartments +
                ", state=" + state +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
