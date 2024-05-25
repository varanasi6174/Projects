package com.jpa.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.hibernate.entity.Laptop;
import com.jpa.hibernate.repository.LaptopRepository;
import com.jpa.hibernate.service.LaptopService;

@Service

public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public Laptop createLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop getLaptopById(Integer laptopId) {
        return laptopRepository.findById(laptopId).orElse(null);
    }

    @Override
    public Laptop updateLaptop(Integer laptopId, Laptop updateLaptop) {
        Laptop existingLaptop = laptopRepository.findById(laptopId).orElse(null);
        if (existingLaptop != null) {
            existingLaptop.setBrand(updateLaptop.getBrand());
            existingLaptop.setModel(updateLaptop.getModel());
            existingLaptop.setSerialNumber(updateLaptop.getSerialNumber());
           

            return laptopRepository.save(existingLaptop);
        }
        return null;
    }

    @Override
    public void deleteLaptopById(Integer laptopId) {
        laptopRepository.deleteById(laptopId);
    }
}

