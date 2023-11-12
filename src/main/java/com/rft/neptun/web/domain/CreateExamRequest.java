package com.rft.neptun.web.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Represents a CREATE request and contains necessary data about the exam.
 */
@Builder
@Data
public class CreateExamRequest {
    @NotBlank(message = "Exam course is mandatory")
    private String course;
    
    @NotBlank(message = "Exam date is mandatory")    
    private String examDate;
    
    @NotBlank(message = "Exam location is mandatory")
    private String location;

    public CreateExamRequest() {
    } 

    public CreateExamRequest(String course, String examDate, String location) {
        this.course = course;
        this.examDate = examDate;
        this.location = location;
    }
    
}
