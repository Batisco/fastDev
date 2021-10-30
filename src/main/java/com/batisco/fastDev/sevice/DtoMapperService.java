package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dto.AddedApartmentDto;
import com.batisco.fastDev.dto.AddedUserDto;
import com.batisco.fastDev.dto.ApartmentDto;
import com.batisco.fastDev.dto.UserDto;

import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoMapperService {

    private final UserService userService;

    @Autowired
    public DtoMapperService(UserService userService) {
        this.userService = userService;
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

}
