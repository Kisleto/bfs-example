package com.codecool.bfsexample.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class UserNode {

    // feel free to add new properties or methods here

    private long id;
    private static long idCounter = 0;
    private String firstName;
    private String lastName;


    private Set<UserNode> friends = new HashSet<>();

    private int level = 0;   //all user's level is initially 0
    private UserNode parentNode;


    public UserNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(UserNode parentNode) {
        this.parentNode = parentNode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public UserNode(String firstName, String lastName) {
        this.id = idCounter;
        idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;

        }


    public long getId() {
        return id;
    }

    public Set<UserNode> getFriends() {return friends;}

    public void addFriend(UserNode friend) {
        friends.add(friend);
        friend.friends.add(this);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + "_" + lastName + " (" + id + ")";
    }


}
