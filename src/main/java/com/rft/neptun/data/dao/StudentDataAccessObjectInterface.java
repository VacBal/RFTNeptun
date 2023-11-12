package com.rft.neptun.data.dao;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.domain.StudentEntity;
import com.rft.neptun.error.StudentNotFoundException;

import java.util.Collection;

/**
 * DAO interface to access to {@link StudentEntity}.
 */
public interface StudentDataAccessObjectInterface {
    /**
     * Returns a {@link StudentEntity} with the given id.
     *
     * @param studentId The id of the student.
     * @return A {@link StudentEntity} with the given id or {@link StudentNotFoundException} if the given id not exists.
     */
    StudentEntity getStudentById(Long studentId);

    /**
     * Returns a Collection of all student.
     *
     * @return a {@link Collection} of {@link StudentEntity}
     */
    Collection<StudentEntity> getAllStudent();
    
    /**
     * Adds the given student to the database.
     *
     * @param entity The entity that will be saved.
     */
    void addStudent(StudentEntity entity);    
}
