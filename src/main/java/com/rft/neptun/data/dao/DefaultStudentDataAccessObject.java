package com.rft.neptun.data.dao;

import com.rft.neptun.data.domain.ExamEntity;
import com.rft.neptun.data.domain.StudentEntity;
import com.rft.neptun.data.repository.StudentRepository;
import com.rft.neptun.error.StudentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Default implementation of {@link StudentDataAccessObjectInterface}.
 */
@Component
public class DefaultStudentDataAccessObject implements StudentDataAccessObjectInterface{

    private final StudentRepository studentRepository;

    @Autowired
    public DefaultStudentDataAccessObject(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
    }

    @Override
    public Collection<StudentEntity> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(StudentEntity entity) {
        try{            
            studentRepository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }
}