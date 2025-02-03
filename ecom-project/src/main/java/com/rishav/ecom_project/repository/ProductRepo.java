package com.rishav.ecom_project.repository;

import com.rishav.ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    //we are using JPA repo to work with H2 DB and there is no method predefined to serch without primary key In JPA
    //so we need to write our own JPA Queries called JPQL to search with names, brands,etc.

    @Query("SELECT  p from Product p WHERE "+
    " LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword , '%' )) OR "
    + "lower(p.description) LIKE lower(concat('%', :keyword , '%') ) OR "
    +"lower(p.brand) like lower(concat('%', :keyword ,'%') ) OR "
    +"lower(p.category) like lower(concat('%', :keyword, '%') ) ")
    List<Product> searchProducts(String keyword);

}
