package com.example.tutorservice.repositories;

import com.example.tutorservice.entities.Tutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The TutorRepository interface provides data access operations for managing tutors in the database.
 * It extends the MongoRepository to perform CRUD operations on the 'Tutor' entity.
 */
@Repository
public interface TutorRepository extends MongoRepository<Tutor, String> {
}

