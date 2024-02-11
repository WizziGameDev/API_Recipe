package org.rpl.Final_Project_Rpl.service;

import org.rpl.Final_Project_Rpl.dto.DisplayBookmarkResponse;
import org.rpl.Final_Project_Rpl.dto.GetAllRecipeResponse;
import org.rpl.Final_Project_Rpl.dto.SearchResponse;
import org.rpl.Final_Project_Rpl.dto.WatchRecipeResponse;
import org.rpl.Final_Project_Rpl.entity.Recipe;
import org.rpl.Final_Project_Rpl.entity.User;
import org.rpl.Final_Project_Rpl.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    MongoTemplate mongoTemplate;

    // add recipe
    @Transactional
    public void addRecipe(Recipe recipe) {
        validationService.validate(recipe);
        recipeRepository.save(recipe);
    }

    // get all data recipe
    public List<GetAllRecipeResponse> getAllRecipe() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<GetAllRecipeResponse> recipeSummaries = new ArrayList<>();

        for (Recipe recipe : recipes) {
            GetAllRecipeResponse summaryDTO = new GetAllRecipeResponse();
            summaryDTO.set_id(recipe.get_id().toString());
            summaryDTO.setName(recipe.getName());
            summaryDTO.setImage(recipe.getImage());
            recipeSummaries.add(summaryDTO);
        }

        return recipeSummaries;
    }

    // watch recipe
    public Object watchRecipe(String recipe_id, String token) {
        Query queryRecipe = new Query();
        queryRecipe.addCriteria(Criteria.where("_id").is(recipe_id));

        Query queryUser = new Query();
        queryUser.addCriteria(Criteria.where("token").is(token));

        Recipe recipe = mongoTemplate.findOne(queryRecipe, Recipe.class);
        User user = mongoTemplate.findOne(queryUser, User.class);

        if (user != null && user.getBookmarks() != null) {
            WatchRecipeResponse recipeResponse = new WatchRecipeResponse();

            for (DisplayBookmarkResponse bookmark : user.getBookmarks()) {
                if (bookmark.get_id().equals(recipe.get_id())) {
                    recipeResponse.set_id(recipe.get_id());
                    recipeResponse.setName(recipe.getName());
                    recipeResponse.setImage(recipe.getImage());
                    recipeResponse.setDescription(recipe.getDescription());
                    recipeResponse.setIngredients(recipe.getIngredients());
                    recipeResponse.setSteps(recipe.getSteps());
                    recipeResponse.setCalorie(recipe.getCalorie());
                    recipeResponse.setLove(1);
                    return recipeResponse; // jika cocok return
                }
            }

            // jika tidak ada yang cocok
            return recipe;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }

    // search recipe
    public List<SearchResponse> searchRecipe (String nameRecipe) {

        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(nameRecipe);
        Query query = TextQuery.queryText(criteria).sortByScore();

        List<Recipe> recipes = mongoTemplate.find(query, Recipe.class);
        List<SearchResponse> responses = new ArrayList<>();

        for (Recipe recipe : recipes) {
            SearchResponse searchResponse = new SearchResponse();
            searchResponse.set_id(recipe.get_id().toString());
            searchResponse.setName(recipe.getName());
            searchResponse.setImage(recipe.getImage());
            responses.add(searchResponse);
        }

        return responses;
    }
}
