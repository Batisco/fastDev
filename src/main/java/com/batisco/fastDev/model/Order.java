package com.batisco.fastDev.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_to_apartments",
            joinColumns = @JoinColumn(name = "o_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "apartment_id")
    )
    private List<Apartment> apartments;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private OrderState state;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    public Order() {
        state = OrderState.BOOKED;
        apartments = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
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
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", apartments=" + apartments +
                ", state=" + state +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

}
