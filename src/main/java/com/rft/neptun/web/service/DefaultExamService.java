package com.rft.neptun.web.service;

import com.rft.neptun.data.dao.ExamDataAccessObjectInterface;
import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.web.domain.CreateExamRequest;
import com.rft.neptun.web.domain.ExamView;
import com.rft.neptun.web.transformer.ExamTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link ExamServiceInterface}.
 */
@Service
public class DefaultExamService implements ExamServiceInterface{

    private final ExamDataAccessObjectInterface examDataAccessObject;
    private final ExamTransformer transformer;

    @Autowired
    public DefaultExamService(ExamDataAccessObjectInterface examDataAccessObject, ExamTransformer transformer) {
        this.examDataAccessObject = examDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public ExamView getExamById(Long id) {
        ExamEntity examEntity = examDataAccessObject.getExamById(id);
        return transformer.transform(examEntity);
    }

    @Override
    public List<ExamView> getAllExam() {
        Collection<ExamEntity> examEntities  = examDataAccessObject.getAllExam();
        List<ExamView> exams = transformer.transform(examEntities);
        return exams.stream()
                .sorted()
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ExamView> getAllStudentRelatedExam(Long studentId) {
        Collection<ExamEntity> examEntities  = examDataAccessObject.getAllStudentRelatedExam(studentId);
        List<ExamView> exams = transformer.transform(examEntities);
        return exams.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addExam(CreateExamRequest request) {
        ExamEntity examEntity = transformer.transform(request);
        examDataAccessObject.addExam(examEntity);
    }
    
    @Override
    public void clearExams() {        
        examDataAccessObject.clearExams();
    }
}