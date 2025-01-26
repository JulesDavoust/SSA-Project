package com.project.ssaapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ssaapplication.models.Property;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // Trouver toutes les propriétés avec un statut spécifique
    List<Property> findByFundingStatus(String fundingStatus);
}
