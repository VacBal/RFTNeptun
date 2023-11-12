package com.rft.neptun.web.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a student.
 */
@Data
@Builder
public class StudentView implements Comparable<StudentView>{
    private Long id;
    private String userName;
    private String emailAddress;

    @Override
    public int compareTo(StudentView o) {
        return userName.compareTo(o.userName);
    }
}
