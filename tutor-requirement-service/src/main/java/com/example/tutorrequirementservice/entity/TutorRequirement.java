package com.example.tutorrequirementservice.entity;

//import lombok annotations to enable lombok features
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//create a Document object to map the TutorRequirement class to the MongoDB database
@Document
//annotate the TutorRequirement class with the lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorRequirement {
    //annotate the id field with the lombok annotation
    @Id
    private String id;
    //create a String field to store the title of the tutor requirement
    private String title;
    //create a String field to store the description of the tutor requirement
    private String description;
    //create a Long field to store the user id of the student who posted the tutor requirement
    private Long studentUserId;
    //create a String field to store the date the tutor requirement was posted
    private String postedDate;
}