package com.rft.neptun.data.dao;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.error.ExamNotFoundException;

import java.util.Collection;

/**
 * DAO interface to access to {@link ExamEntity}.
 */
public interface ExamDataAccessObjectInterface {
    /**
     * Returns a {@link ExamEntity} with the given id.
     *
     * @param examId The id of the exam.
     * @return A {@link ExamEntity} with the given id or {@link ExamNotFoundException} if the given id not exists.
     */
    ExamEntity getExamById(Long examId);

    /**
     * Returns a Collection of all exam.
     *
     * @return a {@link Collection} of {@link ExamEntity}
     */
    Collection<ExamEntity> getAllExam();
    
    /**
     * Returns a Collection of all student related exam.
     *
     * @param studentId The studentId who applied for the exams.
     * @return a {@link Collection} of {@link ExamEntity}
     */
    Collection<ExamEntity> getAllStudentRelatedExam(Long studentId);

    /**
     * Adds the given exam to the database.
     *
     * @param entity The entity that will be saved.
     * 
     */
    void addExam(ExamEntity entity);
    
    /**
     * Clear all exam from the database.
     *     
     */
    void clearExams();
}
