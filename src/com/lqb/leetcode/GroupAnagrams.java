package com.lqb.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class GroupAnagrams {

    @Test
    public void test() {
        GroupAnagrams demo = new GroupAnagrams();
        System.out.println(demo.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(demo.groupAnagrams(new String[] {"eat"}));
        System.out.println(demo.groupAnagrams(new String[] {"eat", "bat"}));
    }

    /**
     * @description 自己的解法：先排序，相同顺序的放进一个List中.
     * 时间复杂度：O(NKlogK)
     * 空间复杂度：O(NK)
     * @author liqibo
     * @date 2019/7/8 16:31
     **/
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length < 1) {
            return Collections.emptyList();
        }

        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> group = new ArrayList<>();
                group.add(str);
                map.put(key, group);
                ans.add(group);
            }
        }

        return ans;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

}
