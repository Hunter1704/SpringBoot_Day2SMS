package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.exception.StudentNotFoundException;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    //public StudentModel addStudent(StudentModel student){
    //return repository.save(student);
    //}

    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getAge()
        );
    }

    //Create
    public List<StudentResponseDto> getStudents(){
        return repository.findAll()
                .stream()
                .map( s-> new StudentResponseDto(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getAge()

        )).toList();


    }

    //Update
    public StudentResponseDto updateStudent(String id, StudentRequestDto student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student Found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        StudentModel savedStudent =  repository.save(existingStudent);

        return new StudentResponseDto(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getAge()
        );
    }

    //Delete
    public void deleteStudent(String id) {
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: "+ id));
        repository.deleteById(id);
    }
}