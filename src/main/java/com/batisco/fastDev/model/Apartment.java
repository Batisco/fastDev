package com.batisco.fastDev.model;

import javax.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "apartments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"address", "number"})
)
public class Apartment {

    @Id
    @Column(name = "apartment_id", nullable = false)
    private UUID id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "level", nullable = false)
    private ApartmentLevel level;

    public Apartment() {

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
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", level=" + level +
                '}';
    }

}
