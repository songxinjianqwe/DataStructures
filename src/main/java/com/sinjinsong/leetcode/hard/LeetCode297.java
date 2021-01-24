package com.sinjinsong.leetcode.hard;

import com.sinjinsong.leetcode.TreeNode;

import java.util.*;

public class LeetCode297 {
    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(TreeNode root, StringBuilder ans) {
            // 先序遍历
            if(root == null) {
                ans.append("None,");
            } else {
                ans.append(root.val + ",");
                serialize(root.left, ans);
                serialize(root.right, ans);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] list = data.split(",");
            return deserialize(new ArrayList<>(Arrays.asList(list)));
        }

        private TreeNode deserialize(List<String> list) {
            // 先序遍历
            if(list.isEmpty()) {
                return null;
            }
            if(list.get(0).equals("None")) {
                list.remove(0);
                return null;
            }
            String val = list.remove(0);
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = deserialize(list);
            root.right = deserialize(list);
            return root;
        }
    }

    public static void main(String[] args) {
        LeetCode297 leetCode297 = new LeetCode297();
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("1,2,3,None,None,4,5,None,None,None,None,");
        String res = codec.serialize(node);
        System.out.println(res);
    }
}
