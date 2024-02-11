package org.rpl.Final_Project_Rpl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rpl.Final_Project_Rpl.entity.Step;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchRecipeResponse {

    private String _id;

    private String name;

    private String image;

    private String description;

    private List<String> ingredients;

    private List<Step> steps;

    private Integer calorie;

    private Integer love;

}
