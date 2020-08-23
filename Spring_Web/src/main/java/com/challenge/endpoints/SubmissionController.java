package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionServiceInterface service;

    @Autowired
    private SubmissionMapper mapper;

    @GetMapping
    public List<SubmissionDTO> getByChallengeAndAcceleration(@RequestParam Long challengeId,
                                                             @RequestParam Long accelerationId) {

        if (challengeId != null & accelerationId != null)
            return mapper.map(service.findByChallengeIdAndAccelerationId(challengeId, accelerationId));

        return Collections.emptyList();
    }

}