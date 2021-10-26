package com.batisco.fastDev.dto;

import java.util.Objects;

public class AddedUserDto {

    private String name;

    public AddedUserDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddedUserDto that = (AddedUserDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AddedUserDto{" +
                "name='" + name + '\'' +
                '}';
    }

}
