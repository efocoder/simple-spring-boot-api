package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.JUNE;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           var clem =  new Student(
                    "Clem",
                    "clem@gmail.com",
                    LocalDate.of(2000, APRIL,19)
            );
            var sedem =  new Student(
                    "Sedem",
                    "sedem@gmail.com",
                    LocalDate.of(2001, JUNE,02)
            );

            repository.saveAll(
                    List.of(clem, sedem)
            );
        };
    };
}
