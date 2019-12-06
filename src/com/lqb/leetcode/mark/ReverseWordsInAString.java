package com.lqb.leetcode.mark;

import org.junit.Test;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例1：
 * 输入:"theskyisblue"
 * 输出: "blueisskythe"
 * <p>
 * 示例2：
 * 输入:" helloworld! "
 * 输出: "world!hello"
 * 解释:输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例3：
 * 输入:"agood example"
 * 输出: "examplegooda"
 * 解释:如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWordsInAString {

    @Test
    public void test() {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("a good  example"));
        System.out.println("a good  example".length());
        System.out.println(reverseWords("a good  example").length());
    }

    /**
     * @author liqibo
     * @date 2019/12/6 19:20
     * @description 空间复杂度O(n)，时间复杂度O(n)
     */
    public String reverseWords(String s) {
        if (s == null || (s = s.trim()).equals("")) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        String[] parts = s.split(" ");
        for (String part : parts) {
            if (part.equals("")) {
                continue;
            }
            //这里可以和下一个解法一样，直接把part添加进来即可，而不是一个字符一个字符添加
            for (int i = part.length() - 1; i >= 0; i--) {
                sb.append(part.charAt(i));
            }
            sb.append(' ');
        }

        return sb.deleteCharAt(sb.length() - 1).reverse().toString();
    }

    /**
     * @author liqibo
     * @date 2019/12/6 19:20
     * @description 网友解法，比上面的快
     */
    public String reverseWords2(String s) {
        StringBuilder ans = new StringBuilder();
        //去掉s的首尾空格 然后将字符串拆分
        String[] s1 = s.trim().split(" ");
        for(int i = s1.length - 1; i >= 0; i--){
            //空格后面的空格会变成空字符串
            if(!s1[i].equals("")) {
                ans.append(s1[i] + " ");
            }
        }
        //去掉最后添加上的空格
        ans = new StringBuilder(ans.toString().trim());
        return ans.toString();
    }

}

