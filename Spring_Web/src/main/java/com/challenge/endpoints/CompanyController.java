package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceInterface service;

    @GetMapping("{Id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long Id) {
        return new ResponseEntity<Company>(
                service.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public List<Company> getFilteredCompanyList(@RequestParam(required = false) Long accelerationId, @RequestParam(required = false) Long userId) {

        if (accelerationId != null)
            return service.findByAccelerationId(accelerationId);

        if (userId != null)
            return service.findByUserId(userId);

        return Collections.emptyList();
    }

}