package com.rft.neptun.web.controller;

import com.rft.neptun.web.domain.CreateExamRequest;
import com.rft.neptun.web.domain.ExamView;
import com.rft.neptun.error.ExamNotFoundException;
import com.rft.neptun.web.service.ExamServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST Controller that handles Exam related operations.
 */
@RestController
public class ExamController {

    private static final String GET_EXAM_MAPPING = "/exam/{id}";
    private static final String GET_ALL_EXAM_MAPPING = "/exam/exams";
    private static final String ADD_EXAM = "/exam/add";

    private final ExamServiceInterface examService;

    @Autowired
    public ExamController(ExamServiceInterface examService) {
        this.examService = examService;
    }

    /**
     * Returns a {@link ExamView} as JSON.
     *
     * @param id The id of the exam.
     * @return A {@link ExamView} with the given id or {@link ExamNotFoundException} if no exam with the given id exists.
     */
    @GetMapping(path = GET_EXAM_MAPPING)
    public ExamView getExam(@PathVariable @NotNull Long id){
        return examService.getExamById(id);
    }

    /**
     * Returns all exams as JSON.
     *
     * @return A {@link List} of {@link ExamView}.
     */
    @GetMapping(path = GET_ALL_EXAM_MAPPING)
    public List<ExamView> getAllExam(){
        List<ExamView> allExam = examService.getAllExam();        
        return allExam;
    }


    /**
     * Adds a exam to the DB.* 
     *
     * @param request A {@link CreateExamRequest} that will be added to the DB.
     * @return ExamSuccessPage with {@link ModelAndView}, where the user get a success message, the created exam details
     * and a link back to teacher' page.
     * 
     * @throws java.lang.Exception
     */
    @PostMapping(path = ADD_EXAM)
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView addExam(@ModelAttribute("exam") @Valid CreateExamRequest request) throws Exception {
        try {            
            examService.addExam(request);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw new Exception(ex);
        }
        
        return new ModelAndView("ExamSuccessPage", "exam", request);
    }
    
    @GetMapping(path = "/clear_exams")
    @ResponseStatus(HttpStatus.OK)
    public void clearExam() throws Exception {
        try {            
            examService.clearExams();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw new Exception(ex);
        }
    }
}