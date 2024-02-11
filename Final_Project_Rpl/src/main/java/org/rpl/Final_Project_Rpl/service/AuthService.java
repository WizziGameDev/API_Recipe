package org.rpl.Final_Project_Rpl.service;

import org.rpl.Final_Project_Rpl.dto.GetUserResponse;
import org.rpl.Final_Project_Rpl.dto.LoginUserRequest;
import org.rpl.Final_Project_Rpl.dto.TokenResponse;
import org.rpl.Final_Project_Rpl.entity.User;
import org.rpl.Final_Project_Rpl.repository.UserRepository;
import org.rpl.Final_Project_Rpl.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(request.getEmail()));

        // memasangkan seluruh data object mongo ke Entity User
        User user = mongoTemplate.findOne(query, User.class);

        if (user != null && BCrypt.checkpw(request.getPassword(), user.getPassword())) {

            String token = UUID.randomUUID().toString();
            long expire = next30Days();

            Update update = new Update();
            update.set("token", token);
            update.set("tokenExpiredAt", expire);

            mongoTemplate.updateFirst(query, update, User.class);

            // Mengembalikan respons token
            return TokenResponse.builder()
                    .token(token)
                    .expireAt(expire)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email or password");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    }

    // Logout
    @Transactional
    public void logOutUser(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));

        // memasangkan seluruh data object mongo ke Entity User
        User user = mongoTemplate.findOne(query, User.class);

        Update update = new Update();
        update.set("token", null);
        update.set("tokenExpiredAt", null);

        mongoTemplate.updateFirst(query, update, User.class);
    }
}
