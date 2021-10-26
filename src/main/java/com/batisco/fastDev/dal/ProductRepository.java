package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            value = """
                    select * from products where name=?1
                    """,
            nativeQuery = true
    )
    public Product getByName(String name);

}
