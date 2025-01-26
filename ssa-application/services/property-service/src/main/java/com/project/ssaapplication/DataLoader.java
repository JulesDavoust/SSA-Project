package com.project.ssaapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.repositories.PropertyRepository;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final PropertyRepository propertyRepository;

    @Autowired
    public DataLoader(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        propertyRepository.save(new Property("Luxury Apartment", "Apartment", 150000, new Date(), "OPEN"));
        propertyRepository.save(new Property("Modern Office", "Building", 300000, new Date(), "OPEN"));
        propertyRepository.save(new Property("Family House", "House", 200000, new Date(), "OPEN"));
    }
}
