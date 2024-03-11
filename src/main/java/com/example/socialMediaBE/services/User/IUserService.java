package com.example.socialMediaBE.services.User;

import com.example.socialMediaBE.DTO.SavedPostList;
import com.example.socialMediaBE.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    public SavedPostList registerUser(User user);
    public User findUserById(Integer id);
    public User findUserByEmail( String email );
    public User followUser(Integer id, Integer id2);
    public User updateUser(User user);
    public List<User> searchUser(String lastName, String firstName, String email);
    public List<User> SortingUser(String field);

    public Page<User> PaginationP(int offset, int pageSize, String field);

    public List<User> searchQuery(String query);

    public SavedPostList listSavedPost(Integer id);


}
