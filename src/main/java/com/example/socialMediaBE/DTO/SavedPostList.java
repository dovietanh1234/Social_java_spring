package com.example.socialMediaBE.DTO;

import com.example.socialMediaBE.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedPostList {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Post> likeposts;

}
