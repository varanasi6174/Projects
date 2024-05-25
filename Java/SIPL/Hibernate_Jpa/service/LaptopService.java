package com.jpa.hibernate.service;

import com.jpa.hibernate.entity.Laptop; 

public interface LaptopService {

    Laptop getLaptopById(Integer laptopId);

    Laptop createLaptop(Laptop laptop);

    Laptop updateLaptop(Integer laptopId, Laptop updatedLaptop);

    void deleteLaptopById(Integer laptopId);

}

