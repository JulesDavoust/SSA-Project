// Property Repository
package com.project.ssaapplication.repositories;

import com.project.ssaapplication.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    List<Investment> findByUserId(UUID userId);
    List<Investment> findByProperty(UUID property);
}