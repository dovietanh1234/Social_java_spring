package com.example.socialMediaBE.controllers;

import com.example.socialMediaBE.entities.Post;
import com.example.socialMediaBE.models.PostModel;
import com.example.socialMediaBE.services.Post.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/post")
public class PostController {

    private final IPostService _iPostService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createPost(@PathVariable Integer userId, @RequestBody PostModel postModel){
        return ResponseEntity.ok( _iPostService.createPost(postModel, userId) );
    }

    @DeleteMapping("/delete/{postId}/{userId}")
    public ResponseEntity<?> deletePost( @PathVariable Integer postId, @PathVariable Integer userId ){
         return ResponseEntity.ok( _iPostService.deletePost(postId, userId) );
    }

    @GetMapping("/findbyid/{postId}")
    public ResponseEntity<?> findPostByIdHandler( @PathVariable Integer postId ){
        Post post = _iPostService.findPostById(postId);

        return ResponseEntity.ok( post );

    }

    @GetMapping("/find/user/{userId}")
    public ResponseEntity<?> findUserPost(@PathVariable Integer userId){
       return ResponseEntity.ok( _iPostService.findPostByUser(userId) );
    }

// USER SAVED POSTS ( user so 2 se luu post id so 1 ) post so 1 -> luu vao user 2
    @PutMapping("/list/saved/{postId}/{userId}")
    public ResponseEntity<?> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId){
        return ResponseEntity.ok( _iPostService.savePost(postId, userId) );
    }

   @GetMapping("/getall")
    public ResponseEntity<?> findAllPosts(){
        return ResponseEntity.ok( _iPostService.findAllPost() );
    }

    //POST SAVED USER'S LIKE | post cua user so 1 nay da duoc user so 2, 3 like ...
// number users liked post
//    @PutMapping("/number/liked/{postId}/{userId}")
//    public ResponseEntity<?> numberLikedPost(@PathVariable Integer postId, @PathVariable Integer userId){
//        return ResponseEntity.ok( _iPostService.likePost(postId, userId) );
//    }







}
