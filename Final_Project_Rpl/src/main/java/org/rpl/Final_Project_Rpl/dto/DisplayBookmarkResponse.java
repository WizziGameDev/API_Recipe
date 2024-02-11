package org.rpl.Final_Project_Rpl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisplayBookmarkResponse {

    private String _id;

    private String name;

    private String image;
}
