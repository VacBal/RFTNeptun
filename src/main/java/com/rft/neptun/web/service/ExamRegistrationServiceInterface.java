package com.rft.neptun.web.service;

/**
 * Service object to handle student exam registration related logic.
 */
public interface ExamRegistrationServiceInterface {

    /**
     * Saves the student's application on both sides in the database.
     *
     * @param studentId The ID of the student who wants to apply.
     * @param examId The ID of the exam you want to apply for.
     */
    void registerStudentToExam(Long studentId, Long examId);
}
