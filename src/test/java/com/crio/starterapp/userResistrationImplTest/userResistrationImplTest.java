package com.crio.starterapp.userResistrationImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.crio.starterapp.services.UserResistration;

public class userResistrationImplTest{
    @Autowired
    UserResistration userResistrationImpl;
@Test
    public void registerUserTest(){
        String username="Tusahr";
        int userId=1;
        String url="http://localhost:8080/users?userId=r&username=tushar";
        String ans=userResistrationImpl.registerUser(username,userId,url);
        System.out.println(ans);
        assertEquals(ans,ans);
    }
}