package com.project.ssaapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.repositories.PropertyRepository;

import java.util.Calendar;
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
        // Définir une date de financement à 2 mois dans le futur
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        Date fundingDeadline = calendar.getTime();

        // Ajouter des propriétés avec des valeurs appropriées
        propertyRepository.save(new Property(
                "Luxury Apartment", 
                "Apartment", 
                150000, 
                fundingDeadline, 
                "OPEN", 
                6.0, // Rental income percentage
                2.0  // Appreciation percentage
        ));
        propertyRepository.save(new Property(
                "Modern Office", 
                "Building", 
                300000, 
                fundingDeadline, 
                "OPEN", 
                5.5, // Rental income percentage
                3.0  // Appreciation percentage
        ));
        propertyRepository.save(new Property(
                "Family House", 
                "House", 
                200000, 
                fundingDeadline, 
                "OPEN", 
                4.0, // Rental income percentage
                1.5  // Appreciation percentage
        ));
    }
}
