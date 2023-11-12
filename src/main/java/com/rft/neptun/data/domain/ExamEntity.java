package com.rft.neptun.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Represents an exam in the database. Contains the id, course, date, location and registrations of the exams.
 */
@Entity
@Table(name = "exam")
@NoArgsConstructor
@Data
public class ExamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String course;
    
    @Column(name = "exam_date", nullable = false)
    private LocalDateTime examDate;

    @Column(nullable = false)
    private String location;
    
    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "exams")    
    private Set<StudentEntity> students = new HashSet<>();
    
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamEntity that = (ExamEntity) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, course, examDate, location);
    }
    
    @Override
    public String toString() {
        return "ExamEntity{" +
               "id=" + id +
               ", course='" + course + '\'' +
               ", examDate=" + examDate +
               ", location='" + location + '\'' +
               '}';
    }
}