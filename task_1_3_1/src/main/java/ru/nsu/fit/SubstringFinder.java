package ru.nsu.fit;

import java.util.List;

public class SubstringFinder {
    private int R;
    private int[] right;
    private String pattern;

    public SubstringFinder(String pattern) {
        this.R = 256;
        this.pattern = pattern;
        
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            right[pattern.charAt(i)] = i;
        }
    }

    public List<String> readFile(String filename) {
        //todo
        return null;
    }

    public int search(String text) {
        int m = pattern.length();
        int n = text.length();
        int skip;
        for (int i = 0; i <= n - m ; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0 ; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return n;
    }
}
