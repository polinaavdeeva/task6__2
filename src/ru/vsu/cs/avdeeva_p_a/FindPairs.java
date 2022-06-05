package ru.vsu.cs.avdeeva_p_a;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindPairs {
    public static void findPairs(int[] input, int sum) {
        System.out.println(" Given sum: " + sum);
        System.out.println("~ All pairs ~");
        getDifferentPairs(input, sum);

    }

    public static List<Integer> getDifferentPairs(int[] input, int sum) {
        List<Integer> pairs = new ArrayList<>();

        pairs = UniquePairs.findPairs(input, sum);

        final StringBuilder output2 = new StringBuilder();
        pairs.forEach((pair) -> output2.append("{" + pair + ", " + (sum - pair) + "}, "));

        System.out.println(output2.toString().substring(0, output2.length() - 2));

        return pairs;
    }

    public static int readSum() {
        System.out.println("Enter the value of the sum S: ");
        Scanner scan = new Scanner(System.in);
        int s = scan.nextInt();
        return s;
    }
}