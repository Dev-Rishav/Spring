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

    public void addProduct(Product product) {
        repo.save(product);
    }

    public Product getProductById(int id){
        return repo.findById(id).orElse(null);
    }

    //? we are using orElse() cause the the findById method returns a optional datatype when nothing is found in DB. so to avoid type mismatching we can use get() or orElse().Actually we should implement proper data to show in frontend.


}
