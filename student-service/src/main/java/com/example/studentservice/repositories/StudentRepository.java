package com.example.studentservice.repositories;

import com.example.studentservice.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    // This interface extends the MongoRepository class to create a repository for the Student entity
}