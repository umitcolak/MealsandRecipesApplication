package com.sabanciuniv.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password); 
    
}
