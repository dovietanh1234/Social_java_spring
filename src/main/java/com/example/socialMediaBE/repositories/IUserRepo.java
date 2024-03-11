package com.example.socialMediaBE.repositories;

import com.example.socialMediaBE.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAllByFirstNameContainsOrLastNameContainingOrEmailContains( String lastName, String firstName, String email );

    @Query("SELECT u FROM users u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% or u.email LIKE %:query%" )
    List<User> searchQuery(@Param("query") String query);

}
