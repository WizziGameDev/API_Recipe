package org.rpl.Final_Project_Rpl.service;

import lombok.extern.slf4j.Slf4j;
import org.rpl.Final_Project_Rpl.dto.GetUserResponse;
import org.rpl.Final_Project_Rpl.dto.RegisterUserRequest;
import org.rpl.Final_Project_Rpl.entity.User;
import org.rpl.Final_Project_Rpl.repository.UserRepository;
import org.rpl.Final_Project_Rpl.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    MongoTemplate mongoTemplate;

    // menambahkan user
    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

            userRepository.insert(user);

        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already registered");
        }
    }

    // mengambil user
    public GetUserResponse getUser(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));

        // memasangkan seluruh data object mongo ke Entity User
        User user = mongoTemplate.findOne(query, User.class);

        if (user == null) {
            // error if token not in database
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");

        } else if (user.getUsername() == null || user.getTokenExpiredAt() < System.currentTimeMillis()) {
            // error if token expire
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expired");

        } else {
            return GetUserResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
    }
}
