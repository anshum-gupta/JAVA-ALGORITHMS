package dp;

import java.util.*;

public class MatrixChainMultiply {
	
	// gives the minimum number of matrix multiplications
	// required to multiply the sequence
	
    public static int solveIterative(int[] input) {
        int inputLength = input.length - 1;
        int[][] p = new int[inputLength][inputLength];
        int[][] m = new int[inputLength][inputLength];
        for (int len = 2; len <= inputLength; len++) {
            for (int a = 0; a + len <= inputLength; a++) {
                int b = a + len - 1;
                m[a][b] = Integer.MAX_VALUE;
                for (int c = a; c < b; c++) {
                    int v = m[a][c] + m[c + 1][b] + input[a] * input[c + 1] * input[b + 1];
                    if (m[a][b] > v) {
                        m[a][b] = v;
                        p[a][b] = c;
                    }
                }
            }
        }
        return m[0][inputLength - 1];
    }

    public static int solveRecursive(int[] input) {
        int inputLength = input.length - 1;
        int[][] cache = new int[inputLength][inputLength];
        for (int[] x : cache) Arrays.fill(x, INF);
        int[][] p = new int[inputLength][inputLength];
        return rec(0, inputLength - 1, input, p, cache);
    }

    static final int INF = Integer.MAX_VALUE / 3;

    static int rec(int i, int j, int[] s, int[][] p, int[][] cache) {
        if (i == j)
            return 0;
        int res = cache[i][j];
        if (res != INF)
            return res;
        for (int k = i; k < j; k++) {
            int v = rec(i, k, s, p, cache) + rec(k + 1, j, s, p, cache) + s[i] * s[k + 1] * s[j + 1];
            if (res > v) {
                res = v;
                p[i][j] = k;
            }
        }
        return cache[i][j] = res;
    }

    // test
    public static void main(String[] args) {
        Random rnd = new Random(1);
        for (int step = 0; step < 1000; step++) {
            int n = rnd.nextInt(6) + 2;
            int[] s = rnd.ints(n, 1, 11).toArray();
            int res1 = solveIterative(s);
            int res2 = solveRecursive(s);
            if (res1 != res2) {
                System.out.println(res1 + " " + res2);
                System.out.println(Arrays.toString(s));
            }
        }
    }
}
