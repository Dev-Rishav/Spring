package com.rishav.webApp.controller;

import com.rishav.webApp.model.Product;
import com.rishav.webApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @RequestMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    /****** 1. GET Method *****/

    //{prodId} will act as :id of Node
    //PathVariable annotation is used to bind the value of the template variable to the method parameter
    @GetMapping("/products/{prodId}")
    //By default the request mapping is of get type
    public Product getProductById(@PathVariable int prodId){
        return service.getProductById(prodId);
    }

    /****** 2. POST Method *****/
    @PostMapping("/products")
    //now this will overlap with the get method so we can use GetMapping, PostMapping,etc
    public void addProduct(@RequestBody  Product prod){
        //@requestBody annotation is used to bind the request body with a method parameter
        service.addProduct(prod);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product){
        service.updateProduct(product);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId){
        service.deleteProduct(prodId);
    }
}
