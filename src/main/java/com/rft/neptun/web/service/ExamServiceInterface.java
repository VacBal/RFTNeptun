package com.rft.neptun.web.service;

import com.rft.neptun.web.domain.CreateExamRequest;
import com.rft.neptun.web.domain.ExamView;


import java.util.List;

/**
 * Service object to handle {@link ExamEntity} related logic.
 */
public interface ExamServiceInterface {

    /**
     * Returns a {@link ExamView} with the given id.
     * The exam lists are sorted by name.
     *
     * @param id The id of the exam, cannot be null.
     * @return A {@link ExamView} with the given id or a {@link com.rft.neptun.error.ExamNotFoundException} if the given id is null or non-existing.
     */
    ExamView getExamById(Long id);

    /**
     * Returns all exam in an ordered list.
     * The list is in alphabetical order.
     *
     * @return a {@link List} of {@link ExamView}
     */
    List<ExamView> getAllExam();

    /**
     * Returns all student related exam in an ordered list.The list is in alphabetical order.
     *
     * @param studentId The studentId who applied for the exams.
     * @return a {@link List} of {@link ExamView}
     */
    List<ExamView> getAllStudentRelatedExam(Long studentId);
    
    /**
     * Creates a new exam and saves it.
     *
     * @param request The Exam that should be saved.
     */
    void addExam(CreateExamRequest request);
    
    /**
     * Remove all exam from the database.
     *
     */
    void clearExams();
}
