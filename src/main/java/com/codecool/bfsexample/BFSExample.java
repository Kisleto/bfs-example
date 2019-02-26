package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BFSExample {

    private static void populateDB() {

        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        //2nd Exercise
        GraphPlotter graphPlotter = new GraphPlotter(users);
        UserNode startNode = users.get(15);
        int range = 2;
        Set<UserNode> friendsInDistance = getFriendsAtDistance(startNode, range);
        graphPlotter.highlightNodes(friendsInDistance, startNode);
        System.out.println(friendsInDistance);

        //1st exercise
        GraphPlotter graphPlotter2 = new GraphPlotter(users);
        UserNode startUser = users.get(1);
        UserNode goalUser = users.get(35);
        Set<UserNode> friends = new HashSet<>();
        friends.add(startUser);
        friends.add(goalUser);
        graphPlotter2.highlightNodes(friends, startUser);
        int distance = getDistanceBetweenUsers(startUser, goalUser);
        System.out.println("The distance between " + startUser.getFirstName() + " " + startUser.getLastName() + " and " + goalUser.getFirstName() + " " + goalUser.getLastName() + " is " + distance);

        System.out.println("Done!");
    }

    public static Set<UserNode> getFriendsAtDistance(UserNode startUser, int range) {

        ArrayList<UserNode> visited = new ArrayList<>();
        LinkedList<UserNode> queue = new LinkedList<>();
        Set<UserNode> friends = new HashSet<>();

        visited.add(startUser);
        queue.add(startUser);

        while (!queue.isEmpty()) {
            UserNode current = queue.poll();
            if (!visited.contains(current) || current.equals(startUser)) {
                for (UserNode friend : current.getFriends()) {
                    if (friend.getLevel() == 0) {
                        friend.setLevel(current.getLevel() + 1);
                        queue.add(friend);
                    }
                }

                if (current.getLevel() == range)
                    friends.add(current);

                if (!visited.contains(current))
                    visited.add(current);
            }
        }

        return friends;
    }

    public static int getDistanceBetweenUsers(UserNode startUser, UserNode goalUser) {
        Queue<UserNode> queue = new LinkedList<>();
        ArrayList<UserNode> visited = new ArrayList<>();

        // Mark the starting node as visited and enqueue it
        queue.add(startUser);
        visited.add(startUser);

        int distance = 0;
        while (!queue.isEmpty()) {
            UserNode current = queue.poll();   //return and remove the head node from queue

            if (!visited.contains(current) || current.equals(startUser)) {
                if (current.equals(goalUser)) {
                    distance = current.getLevel();
                    return distance;
                } else if (!current.getFriends().isEmpty()) {
                    for (UserNode friend : current.getFriends()) {
                        friend.setParentNode(current);
                        friend.setLevel(current.getLevel() + 1);
                        queue.add(friend);
                    }
                }
            }
            if (!visited.contains(current)) {
                visited.add(current);
            }
        }
        return distance;
    }


    public static void main(String[] args) {
        populateDB();

    }
}
