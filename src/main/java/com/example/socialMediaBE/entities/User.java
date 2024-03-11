package com.example.socialMediaBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"followers", "following", "likeposts"}) // 3 thuoc tinh nay se chi duoc goi khi ta can!
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ElementCollection
    private List<Integer> followers;
    @ElementCollection
    private List<Integer> following;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"liked", "user"})
    private List<Post> posts;

    @ManyToMany
  //  @JsonIgnore  (fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"liked", "user"})
   // @JsonManagedReference // o ben chu -> giup giai quyet vong lap JSON
    @JoinTable(
            name = "user_likes_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
   private List<Post> likeposts; // this is saved posts -> SU DUNG SET HOP LY HON



}
