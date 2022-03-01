package net.longvan.training.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import net.longvan.training.spring.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{email:'?0'}")
	User findByEmail(String email); 
}
