package com.example.socialMediaBE.services.Post;

import com.example.socialMediaBE.entities.Post;
import com.example.socialMediaBE.entities.User;
import com.example.socialMediaBE.models.PostModel;
import com.example.socialMediaBE.repositories.IPostRepo;
import com.example.socialMediaBE.repositories.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImplement implements IPostService{

    private final IPostRepo _iPostRepo;
    private final IUserRepo _iUserRepo;

    @Override
    public Post createPost(PostModel post, Integer userId) {

        Post p = new Post();
        p.setCaption(post.getCaption());
        p.setCreateAt(LocalDateTime.now());
        p.setImage( post.getImage() );
        p.setVideo(post.getVideo());

        Optional<User> u = _iUserRepo.findById(userId);
        if (u.isPresent()){
            p.setUser( u.get() );
        }else {
            p.setUser(null);
        }

        _iPostRepo.save(p);

        return p;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {

        Optional<Post> p = _iPostRepo.findById(postId);
        Optional<User> u = _iUserRepo.findById(userId);

        if( p.get().getUser().getId() == u.get().getId() ){
                _iPostRepo.delete(p.get());
                return "delete success";
        }


        return "delete fail";

    }

    @Override
    public List<Post> findPostByUser(Integer userId) {
        return _iPostRepo.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) {

        Optional<Post> p = _iPostRepo.findById(postId);

        return p.isPresent()?p.get():null;
    }

    @Override
    public List<Post> findAllPost() {
        try {
            return _iPostRepo.findAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public Post savePost(Integer postId, Integer userId) {
        Optional<Post> p = _iPostRepo.findById(postId);
        Optional<User> u = _iUserRepo.findById(userId);

        if(p.isPresent() && u.isPresent()){
            if( !u.get().getLikeposts().contains( p.get() ) ){
                u.get().getLikeposts().add(p.get());
            }else{
                u.get().getLikeposts().remove(p.get());
            }

//            if( u.get().getLikeposts().contains(p.get()) ){
//                u.get().getLikeposts().remove(p.get());
//            }else {
//               u.get().getLikeposts().add(p.get());
//            }

            // Minh dang ko the insert vao table user_liked_post -> vi ly do

            _iUserRepo.save(u.get());

            return p.get();
        }
        return null;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) {
        Optional<Post> p = _iPostRepo.findById(postId);
        Optional<User> u = _iUserRepo.findById(userId);

        if( p.isPresent() && u.isPresent() ){
            if( !p.get().getLiked().contains( u.get() ) ){

                //u.get().getLikeposts().add(p.get());
                p.get().getLiked().add(u.get());

            }else{
                p.get().getLiked().remove(u.get());

            }
                _iPostRepo.save(p.get());

            return p.get();
        }

        return null;
    }

    

}

// getLikeposts() sẽ thay the cho getSavedPost()