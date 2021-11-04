package com.batisco.fastDev.dto.furniture;

import com.batisco.fastDev.model.FurnitureType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class FurnitureRequestDto {

    private UUID id;
    private FurnitureType type;
    private BigDecimal price;

    public FurnitureRequestDto() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FurnitureRequestDto that = (FurnitureRequestDto) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price);
    }

    @Override
    public String toString() {
        return "FurnitureRequestDto{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

}
