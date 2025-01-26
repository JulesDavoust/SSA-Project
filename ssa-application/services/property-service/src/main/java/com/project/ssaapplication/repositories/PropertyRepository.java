// Property Repository
package com.project.ssaapplication.repositories;

import com.project.ssaapplication.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {
    List<Property> findByFundingStatus(String fundingStatus);
}