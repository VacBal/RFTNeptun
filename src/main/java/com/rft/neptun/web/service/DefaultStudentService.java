package com.rft.neptun.web.service;

import com.rft.neptun.data.dao.StudentDataAccessObjectInterface;
import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.domain.StudentEntity;
import com.rft.neptun.web.domain.StudentView;
import com.rft.neptun.web.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link StudentServiceInterface}.
 */
@Service
public class DefaultStudentService implements StudentServiceInterface{

    private final StudentDataAccessObjectInterface studentDataAccessObject;
    private final StudentTransformer transformer;

    @Autowired
    public DefaultStudentService(StudentDataAccessObjectInterface studentDataAccessObject, StudentTransformer transformer) {
        this.studentDataAccessObject = studentDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public StudentView getStudentById(Long id) {
        StudentEntity studentEntity = studentDataAccessObject.getStudentById(id);
        return transformer.transform(studentEntity);
    }

    @Override
    public List<StudentView> getAllStudent() {
        Collection<StudentEntity> studentEntities  = studentDataAccessObject.getAllStudent();
        List<StudentView> students = transformer.transform(studentEntities);
        return students.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void checkDefaultStudent() {
       Collection<StudentEntity> studentEntities  = studentDataAccessObject.getAllStudent();
       if(!studentEntities.isEmpty()) {
           return;
       }
       StudentEntity student = new StudentEntity();       
       student.setUserName("student");
       student.setEmailAddress("student@email.com");       
       studentDataAccessObject.addStudent(student);
    }    
}