package com.rft.neptun.data.dao;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.repository.ExamRepository;
import com.rft.neptun.error.ExamNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Default implementation of {@link ExamDataAccessObjectInterface}.
 */
@Component
public class DefaultExamDataAccessObject implements ExamDataAccessObjectInterface{

    private final ExamRepository examRepository;

    @Autowired
    public DefaultExamDataAccessObject(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public ExamEntity getExamById(Long examId) {
        return examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + examId));
    }

    @Override
    public Collection<ExamEntity> getAllExam() {        
        return examRepository.findAll();
    }
    
    @Override
    public Collection<ExamEntity> getAllStudentRelatedExam(Long studentId) {
        return examRepository.findExamsByStudentsId(studentId);
    }

    @Override
    public void addExam(ExamEntity entity) {
        try{
            examRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }
    
    @Override
    public void clearExams() {
        try{
            examRepository.deleteAll();
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }    
}