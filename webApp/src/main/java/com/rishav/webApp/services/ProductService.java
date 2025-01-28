package com.rishav.webApp.services;

import com.rishav.webApp.model.Product;
import com.rishav.webApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

//    List<Product> products=new ArrayList<>(Arrays.asList(new Product(101,"Iphone",50000),
//            new Product(102,"Samsung",40000),
//            new Product(103,"OnePlus",30000)));

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int prodId){
        return repo.findById(prodId).orElse(null);
    }

    public void addProduct(Product prod){
        repo.save(prod);
    }

    public void updateProduct(Product product) {
        repo.save(product);
    }

    public void deleteProduct(int prodId) {
        repo.deleteById(prodId);
    }

}
