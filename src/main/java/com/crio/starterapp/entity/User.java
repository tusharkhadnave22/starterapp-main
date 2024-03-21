package com.crio.starterapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component

@Document(collection = "User")
public class User 
{  @Id
    private int id;
    private String name;
    @Min(value = 0)
    @Max(value = 100)
    private int score;
    private List<badge> badge=new ArrayList<>();  
    public User(){}
    public User(int userId, String UserName){
        this.id=userId;
        this.name=UserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<badge> getBadge() {
        return badge;
    }

    public void setBadge(List<badge> badge) {
        this.badge = badge;
    }
   
}
