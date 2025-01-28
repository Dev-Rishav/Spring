package com.rishav.ecom_project.service;

import com.rishav.ecom_project.model.Product;
import com.rishav.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        System.out.println("Getting all products"+ repo.findAll());
        return repo.findAll();
    }

}
