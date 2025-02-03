package com.rishav.ecom_project.controller;


import com.rishav.ecom_project.model.Product;
import com.rishav.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin//this will solve the cross origin errors (CORS)

public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private Product product;


    //?its good practice to send status code with the responses so we need to send a responseEntity object which binds the result with a status code

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
//        System.out.println("yes its working from the controller");
        return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
        public ResponseEntity<Product> getProduct(@PathVariable int id){
            Product p=service.getProductById(id);
            if(p==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>( service.getProductById(id),HttpStatus.OK);
        }
        @PostMapping("/product")
        public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
            try {
                //one of the hectious error is, we have to name the imageFile as imageFile in the form data.
                System.out.println("yes"+ product+"  "+imageFile);
                Product prod = service.addProduct(product, imageFile);
                System.out.println(prod);
                return new ResponseEntity<>(prod, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping("product/{id}/image")
        public ResponseEntity<byte[]> getImageByProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        byte[] imageFile=product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
     }

     @PutMapping("product/{id}")
     public ResponseEntity<String> updateProduct(@PathVariable  int id,@RequestPart Product product,@RequestPart MultipartFile imageFile) throws IOException {
        Product prod=service.updateProduct(id,product,imageFile);
        if(prod == null)
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Updated",HttpStatus.OK);
     }

     @DeleteMapping("/product/{id}")
    public  ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product prod=service.getProductById(id);
        if(prod==null)
            return  new ResponseEntity<>("No such product exists!",HttpStatus.NOT_FOUND);
        else{
            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully!",HttpStatus.OK);
        }

     }

     @GetMapping("products/search")
     //here we cant use {} as we are not passing dynamic paths like id, we are passing parameters from the url so we need to use annotation @RequestParam to do so, the variable name should be same
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
            List<Product> products=service.searchProducts(keyword);
            return  new ResponseEntity<>(products,HttpStatus.OK);
     }
}
