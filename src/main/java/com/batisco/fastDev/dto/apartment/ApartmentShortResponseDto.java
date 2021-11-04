package com.batisco.fastDev.dto.apartment;

import com.batisco.fastDev.model.ApartmentLevel;

import java.util.Objects;
import java.util.UUID;

public class ApartmentShortResponseDto {

    private UUID id;
    private String address;
    private Integer number;
    private String description;
    private ApartmentLevel level;

    public ApartmentShortResponseDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApartmentLevel getLevel() {
        return level;
    }

    public void setLevel(ApartmentLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentShortResponseDto that = (ApartmentShortResponseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(number, that.number) &&
                Objects.equals(description, that.description) &&
                level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, number, description, level);
    }

    @Override
    public String toString() {
        return "ApartmentShortResponseDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", level=" + level +
                '}';
    }

}
