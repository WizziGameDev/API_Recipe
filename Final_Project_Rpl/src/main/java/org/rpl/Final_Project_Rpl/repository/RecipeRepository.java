package org.rpl.Final_Project_Rpl.repository;

import org.bson.types.ObjectId;
import org.rpl.Final_Project_Rpl.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
}
