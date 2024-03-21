package com.crio.starterapp.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.crio.starterapp.entity.User;
import com.crio.starterapp.entity.badge;
import com.crio.starterapp.exceptions.maximumBadgeSizeReached;
import com.crio.starterapp.repositories.UserRepository;


@Service
public class UserResistrationImpl implements UserResistration {
    @Autowired
    private final RestTemplate restTemplate;

    public UserResistrationImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private UserRepository userRepository;
    @Override
    public String registerUser(String username, int userId,String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            User user=new User(userId,username);
            userRepository.save((user));
            
            return String.valueOf(response.getStatusCode());
        } catch (HttpStatusCodeException ex) {
            // Handle exceptions such as 4xx or 5xx errors
            return String.valueOf(ex.getStatusCode().value());
        }
      
    }
    @Override
    public String updateScore(int userId,int score) {
        // Optional<User> user1= userRepository.findById(userId);
        // if(user1.isPresent()){
        //     User user=user1.get();
        //     user.setScore(score);
        //     updateBadge(userId, score, user);
        //     userRepository.save(user);
            User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            existingUser.setScore(score);
            updateBadge(userId, score, existingUser);
            // existingUser.setAge(newAge);
            userRepository.save(existingUser);

        // }
        // else{throw new UnsupportedOperationException("Unimplemented method 'updateScore'");}
        return "Updated";
    }
    @Override
    public void updateBadge(int userId, int score ,User user) {
        System.out.println("inside updatebadge");
        if(user.getBadge().size()<3){
            if( 1 <= score && score <= 30){
                if(!user.getBadge().contains(badge.Ninja))
                user.getBadge().add(badge.Ninja);
            }
           else if( 30 <= score && score <= 60){
            if(!user.getBadge().contains(badge.Champ))
                user.getBadge().add(badge.Champ);
            }
           else if( 60 <= score && score <= 100){
            if(!user.getBadge().contains(badge.Master))
                user.getBadge().add(badge.Master);
            }
           
          

        }
        else{
            throw new maximumBadgeSizeReached("No more badge can be allocated");
        }
      //  throw new UnsupportedOperationException("Unimplemented method 'updateBatch'");
    }
    @Override
    public List<User> sortData() {
        List<User> sortedPlayers = userRepository.findAll(Sort.by(Sort.Order.desc("score")));
        return sortedPlayers;
    }
    @Override
    public User getUserWithId(int userId) {
       return userRepository.findById(userId).get();
    }
    @Override
    public User deleteUSER(int userId) {
        User user=userRepository.findById(userId).get();
      userRepository.deleteById(userId);
      return user;
    }
    
    public void deleteAllData() {
        userRepository.deleteAll();
    }
    
}
