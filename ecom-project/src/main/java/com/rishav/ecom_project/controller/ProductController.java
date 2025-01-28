package com.rishav.ecom_project.controller;


import com.rishav.ecom_project.model.Product;
import com.rishav.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello from the Controller";

    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        System.out.println("yes its working from the controller");
        return service.getAllProducts();
    }

    @PostMapping("/products")
    public  void addProduct(@RequestBody Product product){
        service.addProduct(product);
    }
}
