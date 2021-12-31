package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private  final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> get () {
        return studentService.getStudents();
    }

    @PostMapping
    public void create(@RequestBody Student student){
         studentService.createStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void update(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
            System.out.println("this is the name " + studentId);
            studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void delete(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
}
