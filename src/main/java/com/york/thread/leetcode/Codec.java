package com.york.thread.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author york
 * @create 2019-11-19 22:23
 **/
public class Codec {

    // 递归
    //1。 处理结束的状态
    // 2. 当前层处理
    // 3。递归下一城
    // 4. 清除状态
    public String serialize(TreeNode root) {
        List<String> result = new ArrayList<>();
        helper(root, result);
        StringBuilder resStr = new StringBuilder("[");
        for (String s : result) {
            resStr.append(s).append(",");
        }
        resStr.append("]");
        return resStr.toString().replace(",]", "]");
    }

    private void helper(TreeNode root, List<String> result) {
        if (root != null) {
            result.add(String.valueOf(root.val));
            helper(root.left, result);
            helper(root.right, result);
        } else {
            result.add("null");
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String s = data.replace("[", "").replace("]", "");
        String[] datas = s.split(",");
        if (datas.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(datas[0]));
        return node;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
