package com.example.day3sms.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class StudentModel {
    @Id

    private String id;
    private String name;
    private int age;
    private String email;

}
