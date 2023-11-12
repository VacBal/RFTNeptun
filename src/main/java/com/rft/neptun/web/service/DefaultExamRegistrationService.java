package com.rft.neptun.web.service;

import com.rft.neptun.data.dao.ExamDataAccessObjectInterface;
import com.rft.neptun.data.dao.StudentDataAccessObjectInterface;
import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.domain.StudentEntity;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * Default implementation of {@link ExamRegistrationServiceInterface}.
 */
@Service
public class DefaultExamRegistrationService implements ExamRegistrationServiceInterface{

    private final StudentDataAccessObjectInterface studentDataAccessObject;
    private final ExamDataAccessObjectInterface examDataAccessObject;
    
    @Autowired
    public DefaultExamRegistrationService(StudentDataAccessObjectInterface studentDataAccessObject, ExamDataAccessObjectInterface examDataAccessObject) {
        this.studentDataAccessObject = studentDataAccessObject;
        this.examDataAccessObject = examDataAccessObject;       
    }

    @Override
    public void registerStudentToExam(Long studentId, Long examId) {      
      StudentEntity student = studentDataAccessObject.getStudentById(studentId);        
      ExamEntity exam = examDataAccessObject.getExamById(examId);        
      
      Set<ExamEntity> existingExams = student.getExams();
      Set<StudentEntity> existingStudents = exam.getStudents();
      
      existingExams.add(exam);
      existingStudents.add(student);
      
      student.setExams(existingExams);
      exam.setStudents(existingStudents);
      
      studentDataAccessObject.addStudent(student);
      examDataAccessObject.addExam(exam);
        
    }
}