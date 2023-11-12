package com.rft.neptun.web.service;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.domain.StudentEntity;
import com.rft.neptun.web.domain.StudentView;

import java.util.List;

/**
 * Service object to handle {@link StudentEntity} related logic.
 */
public interface StudentServiceInterface {

    /**
     * Returns a {@link StudentView} with the given id.
     * The user lists are sorted by name.
     *
     * @param id The id of the user, cannot be null.
     * @return A {@link StudentView} with the given id or a {@link com.rft.neptun.error.StudentNotFoundException} if the given id is null or non-existing.
     */
    StudentView getStudentById(Long id);

    /**
     * Returns all users in an ordered list.
     * The list is in alphabetical order.
     *
     * @return a {@link List} of {@link StudentView}
     */
    List<StudentView> getAllStudent();
    
     /**
     * Checks if the default student exists and creates it if necessary.
     */
    void checkDefaultStudent();
}
