package com.example.socialMediaBE.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="user_id")
    private User user;

 //saved post
    // (fetch = FetchType.LAZY)
  @ManyToMany(mappedBy = "likeposts")
   // @JsonBackReference  // jsonBackReference -> day la ben nghich (inverse side)
    private List<User> liked; // su dung SET hop ly hon



    private LocalDateTime createAt;



}
