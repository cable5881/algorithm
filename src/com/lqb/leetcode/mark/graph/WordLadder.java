package com.lqb.leetcode.mark.graph;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordLadder {

    @Test
    public void test() {
        System.out.println(ladderLength3("hit", "cog", Lists.newArrayList("hot","dot","dog","lot","log","cog")));
        System.out.println(ladderLength3("hit", "cog", Lists.newArrayList("hot","dot","dog","lot","log")));
        System.out.println(ladderLength3("hot", "dog", Lists.newArrayList("hot", "dog")));
        System.out.println(ladderLength3("qa", "sq", Lists.newArrayList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye")));
    }


    //Set<String> paths = new HashSet<>();
    int min;

    /**
     * 自己的解法1，有死循环没有检查出原因
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        min = Integer.MAX_VALUE;

        if (wordList.isEmpty() || endWord.equals(beginWord)) {
            return 0;
        }

        ladderLength(beginWord, endWord, wordList, 1, new int[wordList.size()]);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 自己的解法1：递归核心
     * @param beginWord
     * @param endWord
     * @param wordList
     * @param tmpLen
     * @param v
     */
    private void ladderLength(String beginWord, String endWord, List<String> wordList, int tmpLen, int[] v) {
        if (endWord.equals(beginWord)) {
            min = Math.min(tmpLen, min);
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (v[i] != 1 && canBeTransformed(beginWord, wordList.get(i))) {
                v[i] = 1;
                //String path = beginWord + " -> " + wordList.get(i);
                //                //if (paths.contains(path)) {
                //                //    System.out.println("$$$$$$$$$$$$$$$$$$ " + path);
                //                //} else {
                //                //    paths.add(path);
                //                //    System.out.println(path);
                //                //}
                ladderLength(wordList.get(i), endWord, wordList, tmpLen + 1, v);
                v[i] = 0;
            }
        }
    }

    private boolean canBeTransformed(String beginWord, String endWord) {
        int diff = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    /**
     * 自己的解法二(稍微看了一点点答案)：构建一个图，仍然超出时间限制
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        min = Integer.MAX_VALUE;

        Map<String, LadderObj> wordMap = new HashMap<>(wordList.size() + 1);
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            wordMap.put(word, new LadderObj(getVars(word), i));
        }

        if (!wordMap.containsKey(endWord)) {
            return 0;
        }

        for (Map.Entry<String, LadderObj> entry1 : wordMap.entrySet()) {
            for (Map.Entry<String, LadderObj> entry2 : wordMap.entrySet()) {
                if (entry1 == entry2) {
                    continue;
                }

                LadderObj L1 = entry1.getValue();
                for (String varWord : L1.vars) {
                    LadderObj L2 = entry2.getValue();
                    if (L2.vars.contains(varWord)) {
                        L1.similars.add(L2);
                        break;
                    }
                }
            }
        }

        LadderObj beginLadder = new LadderObj(getVars(beginWord), -1);
        for (Map.Entry<String, LadderObj> entry : wordMap.entrySet()) {
            LadderObj L1 = entry.getValue();
            for (String varWord : L1.vars) {
                if (beginLadder.vars.contains(varWord)) {
                    beginLadder.similars.add(L1);
                    break;
                }
            }
        }

        ladderLength2(beginLadder, wordMap.get(endWord), wordMap, 1);

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public void ladderLength2(LadderObj beginLadder, LadderObj endLadder, Map<String, LadderObj> wordMap, int tmpLen) {
        if (beginLadder == endLadder) {
            min = Math.min(min, tmpLen);
            return;
        }

        if (tmpLen > min) {
            return;
        }

        beginLadder.visited = true;
        for (LadderObj similar : beginLadder.similars) {
            if (!similar.visited) {
                ladderLength2(similar, endLadder, wordMap, tmpLen + 1);
            }
        }
        beginLadder.visited = false;
    }

    private Set<String> getVars(String s) {
        char[] chars = s.toCharArray();
        Set<String> set = new HashSet<>(chars.length);
        for (int j = 0; j < chars.length; j++) {
            char c = chars[j];
            chars[j] = '*';
            set.add(new String(chars));
            chars[j] = c;
        }
        return set;
    }

    /**
     * 官方解法1：广度优先搜索
     * 1.对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
     * 2.将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
     * 3.为了防止出现环，使用访问数组记录。
     * 4.当队列中有元素的时候，取出第一个元素，记为 current_word。
     * 5.找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
     * 6.从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
     * 7.对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
     * 8.最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        //构建一个模糊匹配Map<d*t, [dot, dat..]>
        HashMap<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations =
                                allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        //Pair<单词,转换长度>
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        //添加第一个
        Q.add(new Pair(beginWord, 1));

        //记录已经访问过的单词
        HashMap<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {

                // 遍历每一种可能的模糊词
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // 去模糊词Map里找单词列表
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    //如果找到肯定是最短的，因为是广度优先不是深度优先
                    //广度优先是从每一条可能的路径的起点一个个遍历，再下一个个可能第二起点，也就是说同时遍历多条，因此先找到的肯定是最短的。
                    //而深度优先则是先遍历完一条路线再第二条。
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // 没有访问过的添加到队列中
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }
}

class LadderObj {
    public Set<String> vars;
    public int idx;
    public List<LadderObj> similars = new ArrayList<>();
    public boolean visited;

    public LadderObj(Set<String> vars, int idx) {
        this.vars = vars;
        this.idx = idx;
    }
}
