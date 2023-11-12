package com.rft.neptun.web.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a exam.
 */
@Data
@Builder
public class ExamView implements Comparable<ExamView>{
    private Long id;
    private String course;
    private LocalDateTime examDate;
    private String location;    

    @Override
    public int compareTo(ExamView o) {
        return id.compareTo(o.id);
    }
}
