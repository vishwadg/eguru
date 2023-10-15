package com.example.studentservice.entities;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Builder
@Builder
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    //@Id
    @Id
    private String id;
    private String description;
    private Long userId;

}