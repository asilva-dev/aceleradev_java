package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface service;

    @GetMapping("/{Id}")
    public ResponseEntity<User> getUser(@PathVariable Long Id) {
        return new ResponseEntity<User>(
                service.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping
    public List<User> getFilteredUserList(@RequestParam(required = false) String accelerationName, @RequestParam(required = false) Long companyId) {

        if (accelerationName != null)
            return service.findByAccelerationName(accelerationName);

        if (companyId != null)
            return service.findByCompanyId(companyId);

        return Collections.emptyList();
    }

}