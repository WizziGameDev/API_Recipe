package org.rpl.Final_Project_Rpl.service;

import lombok.extern.slf4j.Slf4j;
import org.rpl.Final_Project_Rpl.dto.DisplayBookmarkResponse;
import org.rpl.Final_Project_Rpl.entity.User;
import org.rpl.Final_Project_Rpl.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookmarkService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<DisplayBookmarkResponse> getDisplayBookmarks(String token) {

        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));

        // memasangkan seluruh data object mongo ke Entity User
        User user = mongoTemplate.findOne(query, User.class);

        List<DisplayBookmarkResponse> bookmarks = user.getBookmarks();
        log.info(bookmarks.toString());
        return bookmarks;
    }
}
