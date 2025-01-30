package com.rishav.ecom_project.controller;


import com.rishav.ecom_project.model.Product;
import com.rishav.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin//this will solve the cross origin errors (CORS)

public class ProductController {
    @Autowired
    private ProductService service;


    //?its good practice to send status code with the responses so we need to send a responseEntity object which binds the result with a status code

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println("yes its working from the controller");
        return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
    }

    @PostMapping("/products")
    public  void addProduct(@RequestBody Product product){
        service.addProduct(product);
    }

    @GetMapping("/products/{id}")
        public ResponseEntity<Product> getProduct(@PathVariable int id){
            Product p=service.getProductById(id);
            if(p==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>( service.getProductById(id),HttpStatus.OK);
        }
}
