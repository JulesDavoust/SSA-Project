package com.project.ssaapplication.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.ssaapplication.models.Property;

@Service
@FeignClient(name = "property-service", url = "http://localhost:8080")
public interface PropertyServiceClient {
    @GetMapping("/properties/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable("id") String id);

    @GetMapping("/properties/all")
    public ResponseEntity<List<Property>> getAllProperties();
}

