package org.rpl.Final_Project_Rpl.repository;

import org.bson.types.ObjectId;
import org.rpl.Final_Project_Rpl.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
