package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
public class StudentService {
    private final  StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents () {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        var studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent())
            throw new IllegalStateException("Email taken");

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        this.existByStudentId(studentId);

        var student = studentRepository.findById(studentId).get();

        System.out.println("New Name "+ name);
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);
        else System.out.println("Name didn't work" + student.getName());

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            var studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent())
                throw new IllegalStateException("Email taken");

            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId) {
        this.existByStudentId(studentId);

        studentRepository.deleteById(studentId);
    }


    private void existByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId))
            throw new IllegalStateException("student with id "+ studentId + " does not exist");
    }
}
