package com.example.socialMediaBE.services.User;

import com.example.socialMediaBE.DTO.SavedPostList;
import com.example.socialMediaBE.entities.Post;
import com.example.socialMediaBE.entities.User;
import com.example.socialMediaBE.repositories.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements IUserService{

    private  final IUserRepo iUserRepo;
    @Override
    public SavedPostList registerUser(User user) {
        User u = new User();

        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        iUserRepo.save(u);

        SavedPostList sp = new SavedPostList();

        sp.setId(user.getId() );
        sp.setEmail(user.getEmail() );
        sp.setFirstName(user.getFirstName() );
        sp.setLastName(user.getLastName() );
        sp.setLikeposts( u.getLikeposts());

        return sp;

    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> u = iUserRepo.findById(id);
       return u.isPresent()?u.get():null;
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> u = iUserRepo.findByEmail(email);
        return u.isPresent()?u.get():null;
    }

    @Override
    public User followUser(Integer id, Integer id2) {

        User user1 = findUserById(id);
        User user2 = findUserById(id2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        iUserRepo.save(user1);
        iUserRepo.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> u = iUserRepo.findById(user.getId());
        if (!u.isPresent()){
            return null;
        }
        if (user.getFirstName() != null) u.get().setFirstName(user.getFirstName());
        if (user.getLastName() != null) u.get().setLastName(user.getLastName());
        if (user.getEmail() != null ) u.get().setEmail(user.getEmail());
        iUserRepo.save( u.get() );
        return u.get();
    }

    @Override
    public List<User> searchUser(String lastName, String firstName, String email) {
        return iUserRepo.findAllByFirstNameContainsOrLastNameContainingOrEmailContains(lastName, firstName, email);
    }

    @Override
    public List<User> SortingUser(String field){
        return iUserRepo.findAll( Sort.by( Sort.Direction.ASC, field ) );
    }


    @Override
    public Page<User> PaginationP( int offset, int pageSize, String field ){
        return iUserRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

    @Override
    public List<User> searchQuery(String query){
            return iUserRepo.searchQuery(query);
    }


    @Override
    public SavedPostList listSavedPost(Integer id){
        Optional<User> u = iUserRepo.findById(id);

        SavedPostList saved = new SavedPostList();

        if( !u.isPresent() ){
            return null;
        }

        saved.setId( u.get().getId() );
        saved.setFirstName( u.get().getFirstName() );
        saved.setLastName( u.get().getLastName() );
        saved.setEmail( u.get().getEmail() );
        saved.setLikeposts( u.get().getLikeposts());  //phai cast data o doan nay vi:

        return saved;




    }


}
