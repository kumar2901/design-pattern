package com.kumar.design.pattern.leetcode;

import java.util.*;

public class Solution {

    // Return the maximum length of a subsequence with at most k unequal adjacent pairs
    // (i.e., at most k transitions => at most k+1 runs).
    static int findMaxLength(int[] skills, int k) {
        int n = skills.length;
        if (n == 0) return 0;
        int groups = Math.min(k + 1, n);   // at most n runs

        // --- Coordinate compress skill values to 0..U-1 ---
        int[] copy = skills.clone();
        Arrays.sort(copy);
        int U = 0;
        int[] uniq = new int[n == 0 ? 1 : n];
        for (int x : copy) {
            if (U == 0 || x != uniq[U - 1]) uniq[U++] = x;
        }
        uniq = Arrays.copyOf(uniq, U);
        int[] comp = new int[n];
        for (int i = 0; i < n; i++) {
            int id = Arrays.binarySearch(uniq, skills[i]);
            comp[i] = id; // 0..U-1
        }

        // DP arrays: prev = best using g runs for first i elements
        // 1-index positions for convenience: dp[i] considers skills[0..i-1]
        int[] prev = new int[n + 1];

        // frequency array over compressed values; reused per (i) inner loop
        int[] freq = new int[U];

        for (int g = 1; g <= groups; g++) {
            int[] curr = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                int maxFreq = 0;
                // we only reset indices we touch
                ArrayList<Integer> touched = new ArrayList<>();

                // extend the last segment to start at j and end at i
                for (int j = i; j >= 1; j--) {
                    int v = comp[j - 1];
                    if (freq[v] == 0) touched.add(v);
                    freq[v]++;
                    if (freq[v] > maxFreq) maxFreq = freq[v];

                    // prev[j-1] = best for first (j-1) elements using (g-1) runs
                    int candidate = prev[j - 1] + maxFreq;
                    if (candidate > curr[i]) curr[i] = candidate;

                    // an early stop is not strictly necessary; left in simple form
                }

                // reset only what we touched
                for (int idx : touched) freq[idx] = 0;
            }

            prev = curr; // roll DP
        }

        return prev[n];
    }

    public static int findMaxLengthV1(int[] skills, int k) {
        // Step 1: run-length encode groups
        List<Integer> groups = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < skills.length; i++) {
            if (skills[i] == skills[i - 1]) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count);

        // Step 2: sliding window to take max sum of (k+1) groups
        int windowSize = k + 1;
        int n = groups.size();
        int left = 0;
        int sum = 0, maxLen = 0;

        for (int right = 0; right < n; right++) {
            sum += groups.get(right);

            // shrink if window too big
            if (right - left + 1 > windowSize) {
                sum -= groups.get(left++);
            }

            maxLen = Math.max(maxLen, sum);
        }

        return maxLen;
    }

    static int minDeliveryTime(int n, List<Integer> orderList) {
        int[] count = new int[n + 1];
        for (int order : orderList) {
            count[order] += 1;
        }
        System.out.println(Arrays.toString(count));
        int low = 1, high = orderList.size(), ans = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            boolean canDistribute = canDistribute(count, n, mid);
            System.out.println("mid=" + mid + " canDistribute=" + canDistribute);
            if (canDistribute) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static boolean canDistribute(int[] count, int n, int mid) {
        long excess = 0, capacity = 0;

        for (int i = 1; i <= n; i++) {
            if (count[i] > mid) {
                // Orders beyond D must be offloaded
                excess += (count[i] - mid);
            } else {
                // Free slots can accept offloaded orders, 2 days per slot
                capacity += (mid - count[i]) / 2;
            }
        }
        System.out.println("excess=" + excess + " capacity=" + capacity + " mid=" + mid);
        return excess <= capacity;
    }


    // ---- Demo ----
    public static void main(String[] args) {
        System.out.println(minDeliveryTime(3, Arrays.asList(1, 1, 1, 3, 1)));
        //System.out.println(minDeliveryTime(4,Arrays.asList(1,2,3,4)));
        //System.out.println(minDeliveryTime(4,Arrays.asList(1,1,2,3,3,3,4,4)));
    }


}
