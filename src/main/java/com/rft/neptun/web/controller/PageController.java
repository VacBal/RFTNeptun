package com.rft.neptun.web.controller;

import com.rft.neptun.web.domain.CreateExamRequest;
import com.rft.neptun.web.domain.ExamView;
import com.rft.neptun.web.domain.StudentView;
import com.rft.neptun.web.service.ExamRegistrationServiceInterface;
import com.rft.neptun.web.service.ExamServiceInterface;
import com.rft.neptun.web.service.StudentServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller that returns pages.
 */
@Controller
public class PageController {

    private static final String ROOT_MAPPING = "/";
    private static final String TEACHER_PAGE_MAPPING = "/teacher";
    private static final String EXAMS_PAGE_MAPPING = "/exams";
    private static final String STUDENT_PAGE_MAPPING = "/studentPage/{id}";
    private static final String REGISTER_TO_EXAM_PAGE_MAPPING = "/register/{studentId}/{examId}";

    private final StudentServiceInterface studentService;
    private final ExamServiceInterface examService;
    private final ExamRegistrationServiceInterface examRegistrationService;
    
    @Autowired
    public PageController(StudentServiceInterface studentService, ExamServiceInterface examService, ExamRegistrationServiceInterface examRegistrationService) {
        this.studentService = studentService;
        this.examService = examService;
        this.examRegistrationService = examRegistrationService;
    }

    /**
     * Returns the home page's name.
     *
     * @return The name of the home page html as a string.
     */
    @GetMapping(ROOT_MAPPING)
    public String homePage() {
        studentService.checkDefaultStudent();        
        return "HomePage";
    }

    /**
     * Returns the students page's name and populates it's model.
     *
     * @param id The id of the user.
     * @param model {@link Model} of the view.
     * @return The name of the home page html as a string.
     */
    @GetMapping(STUDENT_PAGE_MAPPING)
    public String studentPage(@PathVariable Long id, Model model){
        List<ExamView> relatedExams = examService.getAllStudentRelatedExam(id);        
        model.addAttribute("relatedExams", relatedExams);
        model.addAttribute("student", studentService.getStudentById(id));
        return "StudentPage";
    }
    
    /**
     * Returns the teacher page's name, where the teachers can create new exams.
     *          
     * @param model {@link Model} of the view.
     * @return The name of the teacher page html as a string.
     */
    @GetMapping(TEACHER_PAGE_MAPPING)
    public String teacherPage(Model model){
        CreateExamRequest createExamRequest = new CreateExamRequest();
        model.addAttribute("exam", createExamRequest);
        return "TeacherPage";
    }
    
    /**
     * Returns the exams page's name and populates it's model.
     *
     * @param model {@link Model} of the view.
     * @return The name of the exams page html as a string.
     */
    @GetMapping(EXAMS_PAGE_MAPPING)
    public String examPage(Model model) {
        List<ExamView> allExam = examService.getAllExam();
        Long id = Long.valueOf("21");        
        List<ExamView> allStudentRelatedExam = examService.getAllStudentRelatedExam(id);
        List<ExamView> allNotRelatedExam = allExam.stream().filter(exam -> !allStudentRelatedExam.contains(exam)).collect(Collectors.toList());
        model.addAttribute("exams", allNotRelatedExam);
        return "ExamsPage";
    }
    /**
     * Returns the students page's name and populates it's model.
     *
     * @param studentId
     * @param examId The ID of the exam you want to apply for.
     
     * @return The name of the home page html as a string.
     */
    @GetMapping(REGISTER_TO_EXAM_PAGE_MAPPING)
    public String registerToExamPage(@PathVariable Long studentId, @PathVariable Long examId){             
        examRegistrationService.registerStudentToExam(studentId, examId);
        return "redirect:/";
    }
}
