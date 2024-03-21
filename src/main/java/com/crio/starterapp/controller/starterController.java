package com.crio.starterapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crio.starterapp.entity.User;
import com.crio.starterapp.exchanges.userResponse;
import com.crio.starterapp.repositories.UserRepository;
import com.crio.starterapp.services.UserResistration;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class starterController {
    /*
     * GET /users - Retrieve a list of all registered users
GET /users/{userId} - Retrieve the details of a specific user
POST /users - Register a new user to the contest
PUT /users/{userId} - Update the score of a specific user
DELETE /users/{userId} - Deregister a specific user from the contest

    */
   

    @Autowired
    UserResistration userResistration;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public  List<User> getallusers() {
        return userResistration.sortData();
    }
    
    @GetMapping("/users/userId")
    public ResponseEntity<User> getUser(@RequestParam("userId")int userId){
        return ResponseEntity.ok().body(userResistration.getUserWithId(userId));
    }

    @PostMapping("/users")
    public ResponseEntity<String> postMethodName(@RequestParam("username") String username,
    @RequestParam("userId") int userId,HttpServletRequest request) {
        String url = request.getRequestURL().toString();
     return ResponseEntity.ok().body(userResistration.registerUser(username,userId,url));
    }


    @PutMapping("/users/userId/score")
    public void updateScore(@RequestParam("userId") int userId,@RequestParam("score") int score){
     userResistration.updateScore(userId,score);
    }
    
@DeleteMapping("/users/userId")
public User deleteUser(@RequestParam("userId") int userId){
    return userResistration.deleteUSER(userId);
}
@DeleteMapping("/users")
public void deleteall(){
userResistration.deleteAllData();
}
}
