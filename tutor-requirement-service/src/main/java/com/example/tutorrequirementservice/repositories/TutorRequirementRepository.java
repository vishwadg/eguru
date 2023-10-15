package com.example.tutorrequirementservice.repositories;

//import the TutorRequirement entity
import com.example.tutorrequirementservice.entity.TutorRequirement;
//import the MongoRepository interface
import org.springframework.data.mongodb.repository.MongoRepository;
//define the repository interface
import org.springframework.stereotype.Repository;

//define the return type of the method
import java.util.List;

//annotate the repository interface
@Repository
public interface TutorRequirementRepository extends MongoRepository<TutorRequirement, String> {
    //define the method to return a list of TutorRequirements associated with a studentUserId
    List<TutorRequirement> findAllByStudentUserId(Long studentUserId);
}