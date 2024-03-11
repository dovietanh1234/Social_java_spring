package com.example.socialMediaBE.repositories;

import com.example.socialMediaBE.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {

@Query("SELECT p from Post p where p.user.id=:userId")
    List<Post> findPostByUserId(@Param("userId") Integer userId);
}
