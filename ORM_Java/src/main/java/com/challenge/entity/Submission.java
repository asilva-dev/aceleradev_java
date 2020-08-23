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
public class Submission {

    @EmbeddedId
    private SubmissionIdentity submissionIdentity;

    @NotNull
    private float score;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    public SubmissionIdentity getSubmissionIdentity() {
        return submissionIdentity;
    }

    public void setSubmissionIdentity(SubmissionIdentity submissionIdentity) {
        this.submissionIdentity = submissionIdentity;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}