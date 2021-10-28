package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dto.AddedUserDto;
import com.batisco.fastDev.dto.ProductDto;
import com.batisco.fastDev.dto.UserDto;
import com.batisco.fastDev.model.Product;

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

    public ProductDto mapProductToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setUserId(product.getUser().getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public Product mapToProduct(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setUser(userService.getById(dto.getUserId()));
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return product;
    }

    public List<ProductDto> mapProductsToDto(List<Product> products) {
        return products.stream().
                map(this::mapProductToDto).
                toList();
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
