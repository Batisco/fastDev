package com.batisco.fastDev.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "furnitures",
        uniqueConstraints = @UniqueConstraint(columnNames = {"furniture_id", "apartment_id"})
)
public class Furniture {

    @Id
    @Column(name = "furniture_id", nullable = false)
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FurnitureType type;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id")
    private Apartment apartment;

    public Furniture() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FurnitureType getType() {
        return type;
    }

    public void setType(FurnitureType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return Objects.equals(id, furniture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", apartment=" + apartment +
                '}';
    }

}
