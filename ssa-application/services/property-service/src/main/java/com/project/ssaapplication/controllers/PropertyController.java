package com.project.ssaapplication.controllers;

import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/open")
    public List<Property> getOpenForFunding() {
        return propertyService.listOpenForFunding();
    }

    @GetMapping("/all")
    public List<Property> getAllProperties() {
        return propertyService.listAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable UUID id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Property addProperty(@RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable UUID id, @RequestBody Property updatedProperty) {
        try {
            return ResponseEntity.ok(propertyService.updateProperty(id, updatedProperty));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable UUID id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/rental-income")
    public ResponseEntity<Double> calculateRentalIncome(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(propertyService.calculateRentalIncome(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/appreciation")
    public ResponseEntity<Double> calculateAppreciation(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(propertyService.calculateAppreciation(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/transition-state")
    public ResponseEntity<String> transitionPropertyState(@PathVariable UUID id, @RequestParam double fundedAmount) {
        try {
            return ResponseEntity.ok(propertyService.checkAndTransitionState(id, fundedAmount));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
