package com.lqb.leetcode;

import java.util.HashSet;

public class JewelsAndStone {

    public int numJewelsInStones(String J, String S) {
        char[] Jchars = J.toCharArray();
        char[] Schars = S.toCharArray();

        HashSet<Character> characters = new HashSet<>();
        for (char jchar : Jchars) {
            characters.add(jchar);
        }

        int count = 0;

        for (char schar : Schars) {
            if (characters.contains(schar)) {
                count++;
            }
        }

        return count;
    }

}
