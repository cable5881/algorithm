package com.lqb.leetcode.mark;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TopKFrequentElements {

    @Test
    public void test() {
        TopKFrequentElements demo = new TopKFrequentElements();
        System.out.println(demo.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println(demo.topKFrequent2(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }

    /**
     * 官网解法一:
     * Map存储每个元素出现的次数, 用优先队列存储(次数多的排在前面), 然后取出前K个返回
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        //o1是要添加的元素, o2是最底部元素
        //如果o1比o2大, 也就是count.get(o2) - count.get(o1) < 0
        //那么o1会从底部往前晋升
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((o1, o2) -> count.get(o2) - count.get(o1));

        //最大堆
        for (int n: count.keySet()) {
            heap.add(n);
        }

        ArrayList<Integer> top_k = new ArrayList<>(k);
        while (!heap.isEmpty() && k-- > 0) {
            top_k.add(heap.poll());
        }

        return top_k;
    }

    /**
     * 官网解法二:
     * 桶排序
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length+1];
        for(int key : map.keySet()){
            // 获取出现的次数作为下标
            int i = map.get(key);
            if(list[i] == null){
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) {
                continue;
            }
            res.addAll(list[i]);
        }
        return res;
    }
}
