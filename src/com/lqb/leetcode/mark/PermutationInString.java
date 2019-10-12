package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * <p>
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PermutationInString {

    //解法1：先将s1排序，然后s2中每次取s1长度的字符串排序（即为s3），然后比较s1和s3是否相同
    //时间复杂度O(L1*log(L1) + (L2-L1) * L1 * log(L1))
    //注意，如果不截取s2直接排序，再s2远大于s1的情况下，时间复杂度是要大于每次只截取s1长度的。

    //解法2：先获取s1的map映射<每个字母，出现次数>，然后s2中每次取s1长度的字符串也做成map映射，然后比较这两个映射是否相同
    //时间复杂度O(L1 + (L2-L1) * (L1 + 26))
    //空间复杂度：O(1)。使用 s1maps1map 和 s2maps2map，大小为 26。
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解法三：滑动窗口
     * 我们可以为 s2中的第一个窗口创建一次哈希表，而不是为 s2中考虑的每个窗口重新生成哈希表。
     * 然后，稍后当我们滑动窗口时，我们知道我们添加了一个前面的字符并将新的后续字符添加到所考虑的新窗口中。
     * 因此，我们可以通过仅更新与这两个字符相关联的索引来更新哈希表。
     * 同样，对于每个更新的哈希表，我们将哈希表的所有元素进行比较以获得所需的结果。
     * <p>
     * 时间复杂度O(L1 + (L2-L1) * 26)
     **/
    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)) {
                return true;
            }
            //移除窗口最左的字符
            s2map[s2.charAt(i) - 'a']--;
            //添加字符到窗口最右
            s2map[s2.charAt(i + s1.length()) - 'a']++;
        }

        //最后一次循环,添加完元素到窗口已经退出了循环,这里来多最后一次
        return matches(s1map, s2map);
    }

    private boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法四：滑动窗口的优化
     * 上一种方法可以优化，如果不是比较每个更新的 s2map 的哈希表的所有元素，
     * 而是对应于 s2 考虑的每个窗口，我们会跟踪先前哈希表中已经匹配的元素数量
     * 当我们向右移动窗口时，只更新匹配元素的数量。
     * 为此，我们维护一个 count 变量，该变量存储字符数（26个字母表中的数字），
     * 这些字符在 s1 中具有相同的出现频率，当前窗口在 s2 中。当我们滑动窗口时，
     * 如果扣除最后一个元素并添加新元素导致任何字符的新频率匹配，
     * 我们将 count 递增1.如果不是，我们保持 count 完整。
     * 但是，如果添加频率相同的字符（添加和删除之前）相同的字符，现在会导致频率不匹配，
     * 这会通过递减相同的 count 变量来考虑。
     * 如果在移动窗口后，count 的计算结果为26，则表示所有字符的频率完全匹配。所以，我们立即返回一个True。
     *
     **/
    public boolean checkInclusion4(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            //记录s1中字母出现的次数
            s1map[s1.charAt(i) - 'a']++;

            //记录在s2中的前s1长度个字母出现的次数
            s2map[s2.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            //统计s1和s2中，出现次数相同的字母。
            //如果s1和s2都没出现的字母，则两个map中均为0，也是表示该字母在s1和s2中出现次数相同
            if (s1map[i] == s2map[i]) {
                count++;
            }
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            //滑动窗口向右移动一位
            int r = s2.charAt(i + s1.length()) - 'a';
            int l = s2.charAt(i) - 'a';

            if (count == 26) {
                return true;
            }

            //count的增和减分为3种情况，当滑动窗口向右移动1格时
            //1.s1map[x]和s2map[x]本来不相等的，现在相等了，count + 1
            //2.s1map[x]和s2map[x]本来是相等的，现在移动后相差1了，所以count - 1
            //3.s1map[x]和s2map[x]本来不相等的，现在还是不相等，count 保持不变

            //r指向的字母在s2中出现次数 + 1
            s2map[r]++;
            if (s2map[r] == s1map[r]) {
                count++;
            }
            //说明在s2map[r]++前，s2map[r] == s1map[r]，此时两者已经不等了，所以count需要减1
            else if (s2map[r] == s1map[r] + 1) {
                count--;
            }

            //窗口向右移动了一位，因此原窗口最左的字母出现次数 - 1
            s2map[l]--;
            if (s2map[l] == s1map[l]) {
                count++;
            } else if (s2map[l] == s1map[l] - 1) {
                count--;
            }
        }

        //最后一次循环count++ 或者 count-- 后，就退出循环了，因此最后return前需要判断最后一次
        return count == 26;
    }

    @Test
    public void test() {
        //true
        //System.out.println(checkInclusion2("ab", "eidbaooo"));
        //
        ////false
        //System.out.println(checkInclusion2("ab", "eidboaoo"));
        //
        ////true
        //System.out.println(checkInclusion2("abc", "bbbca"));
        //
        ////true
        //System.out.println(checkInclusion2("adc", "dcda"));

        //false
        System.out.println(checkInclusion4("hello", "ooolleohooleh"));
    }

}
