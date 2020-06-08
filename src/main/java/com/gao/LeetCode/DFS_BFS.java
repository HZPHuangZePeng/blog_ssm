package com.gao.LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS_BFS {
    public static void main(String[] args) throws IOException {
        DFS_BFS dfs_bfs = new DFS_BFS();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String begin = bufferedReader.readLine();
        String end = bufferedReader.readLine();
        List<String> wordlist = Arrays.asList(bufferedReader.readLine().split(","));

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        boolean[] used = new boolean[wordList.size()];
        int layer = 1;
        while (!queue.isEmpty()) {
            layer++;
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < wordList.size(); i++) {
                    if (used[i]) continue;
                    String dic = wordList.get(i);
                    if (convert(cur, dic)) {
                        if (endWord.equals(dic)) return layer;
                        queue.add(dic);
                        used[i] = true;
                    }
                }
            }

        }
        return 0;
    }

    private boolean convert(String cur, String dic) {
        int diff = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != dic.charAt(i)) diff++;
        }
        return diff <= 1;
    }
}
