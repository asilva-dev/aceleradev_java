package com.challenge.endpoints;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.challenge.dto.CandidateDTO;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateServiceInterface service;

    @Autowired
    private CandidateMapper mapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable Long userId, @PathVariable Long companyId,
                                                     @PathVariable Long accelerationId) {

        Optional<Candidate> opCandidate = service.findById(userId, companyId, accelerationId);

        return new ResponseEntity<CandidateDTO>(
                mapper.map(opCandidate.orElseThrow(() -> new ResourceNotFoundException("Candidate"))), HttpStatus.OK);
    }

    @GetMapping
    public List<CandidateDTO> getFiltredCandidateList(@RequestParam(required = false) Long companyId, @RequestParam(required = false) Long accelerationId) {

        if (companyId != null)
            return mapper.map(service.findByCompanyId(companyId));

        if (accelerationId != null)
            return mapper.map(service.findByAccelerationId(accelerationId));

        return Collections.emptyList();
    }

}