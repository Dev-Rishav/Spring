package com.rishav.ecom_project.service;

import com.rishav.ecom_project.model.Product;
import com.rishav.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
//        System.out.println("Getting all products"+ repo.findAll());
        return repo.findAll();
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        //now the thing is save function wont work as we have to save the image as well
        //so we have to kindof spread the image into the product object
        //so we have to convert the image into byte array
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }

    public Product getProductById(int id){
        return repo.findById(id).orElse(null);
    }

    //? we are using orElse() cause the the findById method returns a optional datatype when nothing is found in DB. so to avoid type mismatching we can use get() or orElse().Actually we should implement proper data to show in frontend.


}
