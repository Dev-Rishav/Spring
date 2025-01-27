package com.rishav.webApp.services;

import com.rishav.webApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products= Arrays.asList(new Product(101,"Iphone",50000),
            new Product(102,"Samsung",40000),
            new Product(103,"OnePlus",30000));

    public List<Product> getProducts(){
        return products;
    }
}
