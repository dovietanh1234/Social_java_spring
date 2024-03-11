package com.example.socialMediaBE.controllers;

import com.example.socialMediaBE.DTO.APIResponse;
import com.example.socialMediaBE.DTO.SavedPostList;
import com.example.socialMediaBE.entities.User;
import com.example.socialMediaBE.repositories.IUserRepo;
import com.example.socialMediaBE.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
public class HomeController {

    private final IUserRepo iUserRepo;
    private final IUserService iUserService;

//    @PostMapping("/create/{userId}")
//    public User getUserById(@PathVariable("userId") Integer id, @RequestBody User user){
//
//        iUserRepo.save(user);
//
//        return user;
//    }

    @GetMapping("/getAll/{field}")
    public ResponseEntity<?> getUsers(@PathVariable String field){
        List<User> u = iUserRepo.findAll();
      //  new APIResponse<>(u.size(), u);
        return ResponseEntity.ok( new APIResponse<>(u.size(), u));
    }

    @GetMapping("/sorting/{field}")
    public ResponseEntity<?> sort(@PathVariable String field){

        List<User> u = iUserService.SortingUser(field);
        //  new APIResponse<>(u.size(), u);
        return ResponseEntity.ok( new APIResponse<>(u.size(), u));
    }

    // pagination/0/10 -> lay ra gia tri cua 10 elements trang 0.
    @GetMapping("/pagination&sort/{offset}/{pageSize}/{field}")
    public ResponseEntity<?> pagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<User> u = iUserService.PaginationP(offset, pageSize, field);
        return ResponseEntity.ok( new APIResponse<>(u.getSize(), u));
    }



    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user){
       return ResponseEntity.ok(iUserService.updateUser(user));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.ok( iUserService.registerUser(user) );
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        Optional<User> u = iUserRepo.findById(id);
      return u.isPresent()?ResponseEntity.ok(u.get()):ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found the element");

    }

    // u.get() -> lấy ra đối tượng

    // get filter instance - paginate filter
    @GetMapping("/filter")
    public ResponseEntity<?> filter(){
        return null;
    }

    @PutMapping("/users/{id}/{id2}")
    public ResponseEntity<?> followUserHandler(@PathVariable("id") Integer id, @PathVariable("id2") Integer id2){
        User user = iUserService.followUser(id, id2);
        return ResponseEntity.ok( user );
    }

    @GetMapping("/searchQuery/{query}")
    public ResponseEntity<?> searchQuery(@PathVariable("query") String query){
        List<User> users = iUserService.searchQuery(query);
        return ResponseEntity.ok( users );
    }


    @GetMapping("/list/saved/{userId}")
    public ResponseEntity<?> allPropertiesU(@PathVariable("userId") Integer userId){
        SavedPostList saved = iUserService.listSavedPost(userId);

        return ResponseEntity.ok( saved );
    }


}
