package org.rpl.Final_Project_Rpl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("recipes")
public class Recipe {

    @Generated
    private String _id;

    @TextIndexed
    private String name;

    private String image;

    private String description;

    private List<String> ingredients;

    private List<Step> steps;

    private Integer calorie;

}
