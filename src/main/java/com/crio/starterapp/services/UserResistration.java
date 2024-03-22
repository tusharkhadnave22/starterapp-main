package com.crio.starterapp.services;

import java.util.List;


import com.crio.starterapp.entity.User;
import com.crio.starterapp.exceptions.MaximumBadgeSizeReached;

public interface UserResistration {
    public String registerUser(String username, int userId,String url);
    public String updateScore(int userId,int score) throws MaximumBadgeSizeReached;
    public void updateBadge(int userId, int score,User user) throws MaximumBadgeSizeReached;
    public List<User> sortData();
    public User getUserWithId(int userId);
    public User deleteUSER(int userId);
    public void deleteAllData();

}
