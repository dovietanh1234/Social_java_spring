package com.example.socialMediaBE.services.Post;

import com.example.socialMediaBE.entities.Post;
import com.example.socialMediaBE.models.PostModel;

import java.util.List;

public interface IPostService {

    Post createPost(PostModel post, Integer userId);

    String deletePost(Integer postId, Integer userId);

    List<Post> findPostByUser(Integer userId);

    Post findPostById(Integer postId);

    List<Post> findAllPost();

    //cái này xem nos cấu hình: 3h19'
    Post savePost(Integer postId, Integer userId);

    Post likePost(Integer postId, Integer userId);
}
