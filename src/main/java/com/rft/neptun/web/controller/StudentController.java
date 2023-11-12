package com.rft.neptun.web.controller;

import com.rft.neptun.web.domain.StudentView;
import com.rft.neptun.error.StudentNotFoundException;
import com.rft.neptun.web.service.StudentServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST Controller that handles Student related operations.
 */
@RestController
public class StudentController {

    private static final String GET_STUDENT_MAPPING = "/student/{id}";
    private static final String GET_ALL_STUDENT_MAPPING = "/student/students";    

    private final StudentServiceInterface studentService;

    @Autowired
    public StudentController(StudentServiceInterface studentService) {
        this.studentService = studentService;
    }

    /**
     * Returns a {@link StudentView} as JSON.
     *
     * @param id The id of the student.
     * @return A {@link StudentView} with the given id or {@link StudentNotFoundException} if no student with the given id exists.
     */
    @GetMapping(path = GET_STUDENT_MAPPING)
    public StudentView getStudent(@PathVariable @NotNull Long id){
        return studentService.getStudentById(id);
    }

    /**
     * Returns all students as JSON.
     *
     * @return A {@link List} of {@link StudentView}.
     */
    @GetMapping(path = GET_ALL_STUDENT_MAPPING)
    public List<StudentView> getAllStudent(){
        List<StudentView> allStudent = studentService.getAllStudent();        
        return allStudent;
    }
}