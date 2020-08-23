package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeServiceInterface service;

    @GetMapping
    public List<Challenge> getByAccelerationAndUser(@RequestParam Long accelerationId, @RequestParam Long userId) {

        if (accelerationId != null & userId != null)
            return service.findByAccelerationIdAndUserId(accelerationId, userId);

        return Collections.emptyList();

    }

}