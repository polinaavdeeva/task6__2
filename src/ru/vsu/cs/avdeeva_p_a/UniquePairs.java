package ru.vsu.cs.avdeeva_p_a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniquePairs {

    public static List<Integer> findPairs(int[] input, int sum) {
        final List<Integer> allDifferentPairs = new ArrayList<>();
        final Map<Integer, Integer> pairs = new HashMap<>();

        for (int i : input) {
            if (pairs.containsKey(i)) {
                if (pairs.get(i) != null) {
                    allDifferentPairs.add(i);
                }
                pairs.put(sum - i, null);
            } else if (!pairs.containsValue(i)) {
                pairs.put(sum - i, i);
            }
        }
        return allDifferentPairs;
    }
}

