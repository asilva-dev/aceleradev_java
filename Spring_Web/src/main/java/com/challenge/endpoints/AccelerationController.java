package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationServiceInterface service;

    @GetMapping("/{Id}")
    public ResponseEntity<Acceleration> getAcceleration(@PathVariable Long Id) {
        return new ResponseEntity<Acceleration>(
                service.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public List<Acceleration> getFiltredAccelerationList(@RequestParam Long companyId) {

        if (companyId != null)
            return service.findByCompanyId(companyId);

        return Collections.emptyList();
    }

}