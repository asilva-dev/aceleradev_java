package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EntityListeners({AuditingEntityListener.class})
@Entity
public class Candidate {

    @EmbeddedId
    private CandidateIdentity candidateIdentity;

    @NotNull
    private int status;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    public CandidateIdentity getCandidateIdentity() {
        return candidateIdentity;
    }

    public void setCandidateIdentity(CandidateIdentity candidateIdentity) {
        this.candidateIdentity = candidateIdentity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}