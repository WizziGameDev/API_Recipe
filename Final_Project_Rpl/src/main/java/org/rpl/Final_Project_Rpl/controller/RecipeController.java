package org.rpl.Final_Project_Rpl.controller;

import org.rpl.Final_Project_Rpl.dto.GetAllRecipeResponse;
import org.rpl.Final_Project_Rpl.dto.SearchResponse;
import org.rpl.Final_Project_Rpl.dto.WatchRecipeResponse;
import org.rpl.Final_Project_Rpl.dto.WebResponse;
import org.rpl.Final_Project_Rpl.entity.Recipe;
import org.rpl.Final_Project_Rpl.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    // add recipe
    @PostMapping(
            path = "/api/recipes-add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<String> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return WebResponse.<String>builder().data("Success").build();
    }

    // get display recipe
    @GetMapping(
            path = "/api/recipes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    WebResponse<List<GetAllRecipeResponse>> getAllRecipe() {
        List<GetAllRecipeResponse> allRecipe = recipeService.getAllRecipe();
        return WebResponse.<List<GetAllRecipeResponse>>builder().data(allRecipe).build();
    }

    // watch recipe
    @GetMapping(
            value = "/api/recipe-{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> getDataById(@PathVariable String id, @RequestHeader(name = "X-API-TOKEN") String token) {
        Object watchRecipe = recipeService.watchRecipe(id, token);

        return WebResponse.<Object>builder().data(watchRecipe).build();
    }

    // search recipe
    @GetMapping(
            value = "/api/recipes/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SearchResponse>> searchRecipes(@RequestParam("keyword") String keyword) {
        List<SearchResponse> responses = recipeService.searchRecipe(keyword);
        return WebResponse.<List<SearchResponse>>builder().data(responses).build();
    }
}
