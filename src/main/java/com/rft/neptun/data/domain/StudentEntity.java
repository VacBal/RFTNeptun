package com.rft.neptun.data.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Represents a student in the database. Contains the id, name, email and registrations of the users.
 */
@Entity
@Table(name = "student")
@NoArgsConstructor
@Data
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @Column(name = "email", nullable = false, unique = true)
    private String emailAddress;

    @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
    @JoinTable(name = "exam_registrations",
        joinColumns = { @JoinColumn(name = "student_id") },
        inverseJoinColumns = { @JoinColumn(name = "exam_id") })
    private Set<ExamEntity> exams = new HashSet<>();    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, userName, emailAddress);
    }
    
    
    
     @Override
    public String toString() {
        return "StudentEntity{" +
               "id=" + id +
               ", userName='" + userName + '\'' +
               ", emailAddress='" + emailAddress + '\'' +
               '}';
    }
}