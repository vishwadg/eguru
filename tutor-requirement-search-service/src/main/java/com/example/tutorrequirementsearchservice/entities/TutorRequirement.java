package com.example.tutorrequirementsearchservice.entities;

//import lombok annotations to allow for constructor and getter/setter methods
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.time.LocalDate and java.time.LocalDateTime to use for date fields
import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data annotation to indicate that the class should be serialized and deserialized
@Data
//@AllArgsConstructor annotation to indicate that the constructor should be used to initialize all fields
@AllArgsConstructor
//@NoArgsConstructor annotation to indicate that the constructor should be used to initialize all fields
@NoArgsConstructor
//public class TutorRequirement to create a class called TutorRequirement
public class TutorRequirement {
    //private String id to create a private field called id of type String
    private String id;
    //private String title to create a private field called title of type String
    private String title;
    //private String description to create a private field called description of type String
    private String description;
    //private Long userId to create a private field called userId of type Long
    private Long userId;
    //private String postedDate to create a private field called postedDate of type String
    private String postedDate;
}