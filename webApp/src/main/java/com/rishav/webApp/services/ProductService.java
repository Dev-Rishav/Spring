package com.rishav.webApp.services;

import com.rishav.webApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products=new ArrayList<>(Arrays.asList(new Product(101,"Iphone",50000),
            new Product(102,"Samsung",40000),
            new Product(103,"OnePlus",30000)));

    public List<Product> getProducts(){
        return products;
    }

    public Product getProductById(int prodId){
        return products.stream().filter(t->t.getProdId()==prodId).findFirst().get();
    }

    public void addProduct(Product prod){
        products.add(prod);
    }

    //@Override
//    public String toString() {
//        return "ProductService{" +
//                "products=" + products +
//                '}';
//    }
}
