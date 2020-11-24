package com.york.thread.leetcode;

import java.util.*;

/**
 * @author york
 * @create 2019-11-02 18:19
 **/
class Solution {


    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        searchMatrix(matrix, 3);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //前半部分有序,注意此处用小于等于
            if (nums[start] <= nums[mid]) {
                //target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int col = matrix.length;
        int row = matrix[0].length;
        int left = 0;
        int right = col * row - 1;
        while (left <= right) {
            int cur = (left + right) / 2;
            int mid = matrix[cur / row][cur % row];
            if (mid == target) {
                return true;
            } else if (mid < target) {
                System.out.println(cur - 1);
                right = cur - 1;
            } else {
                left = cur + 1;
            }
        }
        return false;
    }

    public int mySqrt(int x) {
        long left = 0;
        long right = x + 1;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    /**
     * 55
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        return recur(nums, 0);
    }

    private boolean recur(int[] nums, int n) {
        if (n == nums.length - 1) {
            return true;
        }
        for (int i = 1; i <= Math.min(nums[n] + n, nums.length); i++) {
            if (recur(nums, n + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 122
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 455
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        // 贪心算法，当前最优解法
        int count = 0;
        Arrays.sort(s);
        Arrays.sort(g);
        int curG = 0;
        int curS = 0;
        while (curG < g.length && curS < s.length) {
            if (s[curS] >= g[curG]) {
                curG++;
                curS++;
            } else {
                curS++;
            }
        }
        return curG;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> lists = new ArrayList<>();
        Queue<Integer> queue = new LinkedList();
        recuration(n, 0, 0, queue, lists);
        return lists;
    }

    private void recuration(int n, int i, int j, Queue<Integer> queue, List<List<String>> lists) {
        if (i == n) {
            return;
        }
        if (j == n) {

            // 成功
            return;
        }

        // 判断当前层逻辑
        // 不符合，返回
        if (queue.contains(i) || queue.contains(j) || queue.contains(i + j) || queue.contains(Math.abs(j - i))) {
            return;
        }
        queue.add(i);
        queue.add(j);
        for (int k = j; k < n; k++) {
            queue.add(i + k);
            queue.add(i - k);
        }
        for (int k = i; k < n; k++) {
            queue.add(Math.abs(k - j));
            queue.add(Math.abs(k + j));
        }
//        recuration(n, i + 1, j, )
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[i].length - 1; j++) {
                if (grid[i][j] == 1) {
                    // 深度优先
                    count++;
//                    dfs(grid,i,j);
                    // 广度优先
                    Queue<Character> queue = new LinkedList<>();
                    grid[i][j] = '0';
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }


    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        List<String> result = new ArrayList<>();
        helper3(digits.toCharArray(), map, "", result);
        return result;
    }

    private static void helper3(char[] digits, Map<Character, List<String>> map, String s, List<String> result) {
        if (s.length() == digits.length) {
            result.add(s);
            return;
        }
        int curLength = s.length();
        List<String> list = map.get(digits[curLength]);
        for (String string : list) {
            String s1 = s + string;
            helper3(digits, map, s1, result);
        }
    }

    /**
     * 169
     * @param nums
     * @return
     */
//    public int majorityElement(int[] nums) {
//        if (nums.length < 3) {
//            return nums[0];
//        }
//        int cur = nums[0];
//        for (int i = 0; i < nums.length/2 + 1; i++) {
//            int max = helper(nums,0,nums.length - 1,nums[i]);
//        }
//    }
//
//    private int helper(int[] nums,int start,int end,int cur) {
//        if (end == start) {
//            return nums[start];
//        }
//    }

    /**
     * 78
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // 1.迭代
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(new ArrayList<>());
//        for (int i = 0; i < nums.length; i++) {
//            int size = result.size();
//            for (int j = 0; j < size; j++) {
//                List<Integer> list = new ArrayList<>(result.get(j));
//                list.add(nums[i]);
//                result.add(list);
//            }
//        }
//        return result;
        // 2.dp
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        dp(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void dp(int[] nums, int i, List<Integer> list, List<List<Integer>> result) {
        if (i == nums.length) {
            return;
        }
        dp(nums, i + 1, list, result);
        List<Integer> list1 = new ArrayList<>(list);
        list1.add(nums[i]);
        result.add(list1);
        dp(nums, i + 1, list1, result);
    }

    /**
     * 50
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (x < 0) {
            return 1 / myPow(x, -n);
        }
        for (int i = n; i > 0; i = i / 2) {
            if (i % 2 == 0) {
                return myPow(x, i);
            }
            return myPow(x, i) * x;
        }
        return 0.0;
    }

    /**
     * 226
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 236
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * 104
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 111
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        } else if (root.right == null && root.left != null) {
            return minDepth(root.left) + 1;
        } else if (root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        } else {
            return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
        }
    }


    /**
     * 22
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateAll(0, 0, n, "", result);
        return null;
    }

    private static void generateAll(int left, int right, int n, String s, List<String> result) {
        if (right == n && left == n) {
            result.add(s);
            return;
        }
        if (left < n) {
            generateAll(left + 1, right, n, s + "(", result);
        }
        if (right < left) {
            generateAll(left, right + 1, n, s + ")", result);
        }
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        // 递归 O（N）
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        // 用栈来解决
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            list.add(curNode.val);
            curNode = curNode.right;
        }
        return list;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }


    public static boolean isAnagram(String s, String t) {
        // 1.暴力，排序后比较，O(NlogN)
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < chars1.length; i++) {
            if (map.get(chars1[i]) == null) {
                map.put(chars1[i], 1);
            } else {
                map.put(chars1[i], map.get(chars1[i] + 1));
            }
        }
        for (int i = 0; i < chars2.length; i++) {
            if (map.get(chars2[i]) == null || map.get(chars2[i]) == 0) {
                return false;
            }
            map.put(chars2[i], map.get(chars2[i] - 1));
        }
        Collection<Integer> values = map.values();
        for (int i : values) {
            if (i != 0) {
                return false;
            }
        }

        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return String.valueOf(chars1).equals(String.valueOf(chars2));
        // 使用hashmap
    }

    /**
     * 42
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int res = 0;
        // 1.暴力，找出每根棒子的最左边和最右边
        int[] maxL = new int[height.length];
        maxL[0] = height[0];
        int[] maxR = new int[height.length];
        maxR[0] = height[height.length - 1];
        for (int i = 1; i < height.length - 1; i++) {
            maxL[i] = Math.max(maxL[i - 1], height[i]);
            maxR[height.length - i - 1] = Math.max(maxR[height.length - i], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            maxR[i] = Math.max(maxR[i + 1], height[i]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int leftHeight = 0;
            int rightHeight = 0;
            int j = i;
            while (j >= 0) {
                leftHeight = Math.max(leftHeight, height[j]);
                j--;
            }
            int k = i;
            while (k < height.length) {
                rightHeight = Math.max(rightHeight, height[k]);
                k++;
            }
            res += Math.min(rightHeight, leftHeight) - height[i];
        }
        // 动态规划
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 239
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k];
//        Deque<Integer> deque = new LinkedList<Integer>();
//        int max = Integer.MIN_VALUE;
//        for (int i = 0,index = 0; i < k; i++) {
//            if (deque.isEmpty()) {
//                deque.push(i);
//            }
//            if (deque.pe)
//        }
        return res;
    }

    /**
     * 189
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        // 暴力
        int prev = nums[nums.length - 1];
        int cur = nums[0];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < nums.length; j++) {
                cur = nums[j];
                nums[j] = prev;
                prev = cur;
            }
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int p = 0, q = 1;
        for (; q < nums.length; q++) {
            if (nums[p] != nums[q]) {
                p++;
                nums[p] = nums[q];
            }
        }
        return p + 1;
    }

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int n1 = 1;
        int n2 = 2;
        for (int i = 3; i <= n; i++) {
            int t1 = n1;
            n1 = n2;
            n2 = t1 + n1;
        }
//        return climbStairs(n - 1) + climbStairs(n - 2);
        return n2;
    }

    /**
     * 84
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        // 题目和maxArea不同，要找到中间最小的值才为高度
//        if (heights.length == 1) {
//            return heights[0] * 1;
//        }
//        for (int i = 0,j = heights.length - 1; i < j;) {
//            int minHeight = heights[i] < heights[j] ? heights[i++] : heights[j--];
//            int curArea = minHeight * (j - i + 1);
//            maxArea = Math.max(maxArea,curArea);
//        }
        // 遍历每一个元素，以当前元素为最左边的棒子，找出右边最矮的高度，来求值
        for (int i = 0; i < heights.length; i++) {
            int minRight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minRight = Math.min(heights[j], minRight);
                maxArea = Math.max(maxArea, minRight * (j - i + 1));
            }
        }
        return maxArea;
    }

    public static int maxArea(int[] height) {
        // 暴力破解，找出高和宽最大值，宽为两个数相隔的距离
        int result = 0;
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int h = Math.min(height[i],height[j]);
//                int w = j - i;
//                result = Math.max(result,h * w);
//            }
//        }
        // i从头开始，j从尾部开始，先用最大的宽度来计算，当找到i == j时，则找出了最大结果
        for (int i = 0, j = height.length - 1; j > i; ) {
            int min = height[i] < height[j] ? height[i] : height[j];
            result = Math.max(min * (j - i), result);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }

//            if (height[i] < height[j]) {
//                i++;
//            } else {
//                j--;
//            }
//            System.out.println("下一个最大" + i + "," + j +"," + Math.min(height[i],height[j]) * (j - i + 1));
//            result = Math.max(result,Math.min(height[i],height[j]) * (j - i + 1));
//            System.out.println(result);
        }
        return result;
    }

    public static int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }
        int[] mem = new int[2];
        mem[0] = 0;
        mem[1] = 1;
        for (int i = 3; i <= N; i++) {
            int cur = mem[0];
            mem[0] = mem[1];
            mem[1] = cur + mem[1];
        }
        return mem[0] + mem[1];
    }


    public static int minPathSum(int[][] grid) {
        int i = grid.length;
        int j = grid[0].length;
        int minPath = grid[i - 1][j - 1];
        return minPath(grid, i - 1, j - 1, 0);
    }

    public static int minPath(int[][] grid, int i, int j, int pathSum) {
        if (i == 0 && j == 0) {
            return pathSum;
        }
        if (i == 0) {
            return grid[i][j] + minPath(grid, i, j - 1, pathSum);
        }
        if (j == 0) {
            return grid[i][j] + minPath(grid, i - 1, j, pathSum);
        }
        int iMinPath = minPath(grid, i - 1, j, pathSum);
        int jMinPath = minPath(grid, i, j - 1, pathSum);
        pathSum = pathSum + Math.min(iMinPath, jMinPath);
        return pathSum;
    }

    public static int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum = sum + num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return Math.max(ans, sum);
    }


    public boolean isValidBST(TreeNode root) {
        // 递归
        boolean result = isValidSubBST(root, null, null);
        // 中序遍历，
        Stack<Integer> stack = new Stack();
        helper1(root, stack);
        int last = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (cur <= last) {
                return false;
            }
        }
        return result;
    }

    // 中序遍历 左中右
    private void helper1(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return;
        }
        helper1(root.left, stack);
        stack.add(root.val);
        helper1(root.right, stack);
    }

    private boolean isValidSubBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        if (min != null && val <= min) {
            return false;
        }
        if (max != null && val >= max) {
            return false;
        }
        if (!isValidSubBST(root.left, min, val)) {
            return false;
        }
        if (!isValidSubBST(root.right, val, max)) {
            return false;
        }
        return true;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length < 3) {
            return results;
        }
        //1。 暴力    存在重复情况
//        for (int i = 0;i < nums.length - 2;i++) {
//            for (int j = i + 1; j < nums.length - 1;j++) {
//                for (int k = j + 1;k < nums.length;k++) {
//                    if (nums[i] + nums[j] + nums[k] == 0) {
//                        List<Integer> list = Arrays.asList(nums[i],nums[j],nums[k]);
//                        results.add(list);
//                    }
//                }
//            }
//        }
        // 2. 转化为twosum
        // 3.双指针,先排序，跳过重复元素
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int head = i + 1;
                int tail = nums.length - 1;
                int sum = nums[i];
                while (head < tail) {
                    if (nums[head] + nums[tail] + nums[i] > 0) {
                        tail--;
                    } else if (nums[head] + nums[tail] + nums[i] < 0) {
                        head++;
                    } else {
                        results.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                        while (head < tail && nums[head] == nums[head + 1]) {
                            head++;
                        }
                        while (head < tail && nums[tail] == nums[tail - 1]) {
                            tail--;
                        }
                        head++;
                        tail--;
                    }
                }
            }
        }
        return results;
    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (char c : chars) {
            if (!map.containsValue(c)) {
                if (!map.containsKey(c) || stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 判断单链表是否有环存在
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
//        Map<ListNode,Integer> map = new HashMap<>();
//        while (head != null) {
//            if (map.get(head) != null) {
//                return true;
//            }
//            map.put(head,1);
//            head = head.next;
//        }
        // 快慢指针实现
        ListNode node1 = head;
        ListNode node2 = head;
        while (node1 != null && node2 != null) {
            if (node2.next == null) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next.next;
            if (node1.equals(node2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode newNode = null;
        ListNode node = head;
        ListNode prev = null;

        while (node != null && node.next != null) {
            ListNode nextNode = node.next;
            prev = node.next;
            prev.next = node.next;
            node = node.next;
        }
        return newNode;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean bigThanTen = false;
        while (l1 != null) {
            while (l2 != null) {
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        return null;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
//        for (int i = 0; i < nums.length; i++ ) {
//            Integer mapnum = map.get(target - nums[i]);
//            if ( mapnum != null && i != mapnum.intValue()) {
//                return new int[] {i,mapnum};
//            }
////            for (int j = i + 1; j < nums.length; j++) {
////                if (nums[i] + nums[j] == target) {
////                    return new int[] {i,j};
////                }
////            }
//        }
        return new int[0];
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
