package com.example.security_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.security_demo.model.Student;

@Service
public class StudentService {
    List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student("ankit", 10, 1));
        students.add(new Student("bina", 20, 2));
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> addStudent(Student student) {
        students.add(student);
        return students;
    }
}
