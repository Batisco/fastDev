package com.batisco.fastDev.model;

import javax.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"})
)
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                '}';
    }

}
