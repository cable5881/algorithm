package com.lqb.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 **/
public class GenerateParenthesis {

    @Test
    public void test1() {
        GenerateParenthesis demo = new GenerateParenthesis();
        System.out.println(demo.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesisCore(n, n, new StringBuilder(), ans);
        return ans;
    }

    private void generateParenthesisCore(int leftCount, int rightCount, StringBuilder sb, List<String> parenthesis) {

        if (leftCount < 0 || rightCount < 0) {
            return;
        }

        if (leftCount == 0 && rightCount == 0) {
            parenthesis.add(sb.toString());
            return;
        }

        if (leftCount > 0) {
            generateParenthesisCore(leftCount - 1, rightCount, sb.append('('), parenthesis);
            sb.deleteCharAt(sb.length() - 1);
        }

        //这里不能是rightCount > 0，也不能是leftCount <= rightCount
        //如果是rightCount > 0，则会出现")()()("、"(()))("等等
        //如果是leftCount <= rightCount，则会出现"(()))(", " )()()("等等
        //leftCount < rightCount可以保证")" 有足够的 "(" 去匹配
        if (leftCount < rightCount) {
            generateParenthesisCore(leftCount, rightCount - 1, sb.append(')'), parenthesis);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}
