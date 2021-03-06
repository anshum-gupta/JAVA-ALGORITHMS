package dp;

import java.util.Arrays;

// https://en.wikipedia.org/wiki/Longest_increasing_subsequence  in O(n * log(n))
public class Lis {

    // returns the longest increasing subsequence 
    // and traces that as well
    public static int[] lis(int[] a) {
        int n = a.length;
        int[] tail = new int[n];
        int[] prev = new int[n];

        int len = 0;
        for (int i = 0; i < n; i++) {
            int pos = lower_bound(a, tail, len, a[i]);
            len = Math.max(len, pos + 1);
            prev[i] = pos > 0 ? tail[pos - 1] : -1;
            tail[pos] = i;
        }

        int[] res = new int[len];
        for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
            res[--len] = a[i];
        }
        return res;
    }


    // returns length of longest increasing subsequence
    public static int getLisLength(int[] a){
        int[] lis = lis(a);
        return lis.length;
    }


    // unlike C++, java does not have an inbuilt lower_bound
    // although we do have binary search, but decided to impelement
    // it myself
    static int lower_bound(int[] a, int[] tail, int len, int key) {
        int lo = -1;
        int hi = len;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (a[tail[mid]] < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    // Usage example
    public static void main(String[] args) {
        int[] lis = lis(new int[] {1, 10, 2, 11, 3});
        int lisLength = getLisLength(new int[] {1, 10, 2, 11, 3});
        System.out.println("lis length = " + lisLength);
        System.out.println(Arrays.toString(lis));
    }
}
