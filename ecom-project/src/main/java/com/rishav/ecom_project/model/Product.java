package com.rishav.ecom_project.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //this will make sure that the id generation is automatically handled


    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    //? Change the format of the date to be shown in, the month must be in MM capital
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    //for now we are handling using react

    private Date releaseDate;
    private boolean isAvailable;
    private int quantity;

    private String imageName;
    private String imageType;
    //here for learning purposes we are using storing images into databases
    //byte array is used to store the image with Large Object Binary annotation
    @Lob
    private byte[] imageBytes;
    private Date imageDate;

    public Date getImageDate() {
        return imageDate;
    }

    public void setImageDate(Date imageDate) {
        this.imageDate = imageDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }



    public Product(int id, String name, String description, String brand, BigDecimal price, String category, Date releaseDate, boolean isAvailable, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    public Product (){}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
