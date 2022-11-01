package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    private static final Map<String, Integer> userToScore = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Set<String> myFriends = new HashSet<>();

        friends.stream()
                .filter(friend -> friend.contains(user))
                .forEach(myFriends::addAll);

        friends.stream()
                .filter(friend -> myFriends.contains(friend.get(0)) || myFriends.contains(friend.get(1)))
                .forEach(friend -> {
                    userToScore.put(friend.get(0), userToScore.getOrDefault(friend.get(0), 0) + 10);//score += 10
                    userToScore.put(friend.get(1), userToScore.getOrDefault(friend.get(1), 0) + 10);
                });

        visitors.forEach(visitor -> userToScore.put(visitor, userToScore.getOrDefault(visitor, 0) + 1));//score += 1

        return userToScore.entrySet().stream().filter(e -> !myFriends.contains(e.getKey()) && e.getValue() > 0)
                .sorted(Map.Entry.comparingByKey())//key 정렬
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//value 정렬
                .map(Map.Entry::getKey)//key 값만
                .limit(5)//max 5
                .collect(Collectors.toList());//List 변환
    }

}