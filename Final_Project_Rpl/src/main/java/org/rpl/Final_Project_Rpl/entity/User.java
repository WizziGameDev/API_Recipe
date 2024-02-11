package org.rpl.Final_Project_Rpl.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.rpl.Final_Project_Rpl.dto.DisplayBookmarkResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {

    @Id
    @Generated
    private String _id;

    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String token;

    private Long tokenExpiredAt;

    private List<DisplayBookmarkResponse> bookmarks;
}
