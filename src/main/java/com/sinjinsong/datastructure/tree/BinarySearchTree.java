package com.sinjinsong.datastructure.tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Java实现二叉查找树 使用递归的操作一般设置两个方法，一个方法是public的（用于调用的），一个方法是private（实际递归的）
 *
 * @author New Song
 */
public class BinarySearchTree<E extends Comparable<E>> implements Cloneable {
    private TreeNode<E> root;
    // 用于使用先序遍历字符串建树
    private LinkedList<TreeNode<E>> pathList;
    // 用于输出从根节点到各个叶子结点的所有路径
    private static int index = -1;

    public BinarySearchTree() {
    }

    public BinarySearchTree(TreeNode<E> root) {
        this.root = root;
    }

    // 在node结点下创建子树，node初始值为root，在查找过程中变为root的子节点
    // 如果root为空，那么root的值为data
    // 否则就向下查找，直至找到合适的位置，创建新结点，赋值为data
    // 不存在node的值等于data的情况，如果相等就没有插入的必要了
    public void createBiTree(TreeNode<E> node, E data) {
        if (root == null) {
            root = new TreeNode<E>(data);
        } else {
            if (data.compareTo(node.data) < 0) {
                if (node.leftChild == null) {
                    node.leftChild = new TreeNode<E>(data);
                } else {
                    createBiTree(node.leftChild, data);
                }
            } else {
                if (node.rightChild == null) {
                    node.rightChild = new TreeNode<E>(data);
                } else {
                    createBiTree(node.rightChild, data);
                }
            }
        }
    }

    // 插入一个结点
    public void insert(E data) {
        root = insert(root, data);
        // 注意结果要赋值，否则在调完第二个insert方法后node就回收了，没有把结果赋给root
        // 调用的时候将一个null赋给了node，即使node后来不为null了也没法把引用重新赋给root
    }

    // 也是需要递归进行查找的，递归地找到合适位置后插入，不允许有重复元素
    private TreeNode<E> insert(TreeNode<E> node, E data) {
        if (node == null) {
            node = new TreeNode<>(data);
        } else {
            if (data.compareTo(node.data) < 0) {
                node.leftChild = insert(node.leftChild, data);
            } else if (data.compareTo(node.data) > 0) {
                node.rightChild = insert(node.rightChild, data);
            }
        }
        return node;
    }

    public TreeNode<E> search(E data) {
        return search(root, data);
    }

    // 查找一个结点，使用递归
    // 如果data即为当前结点的值，则返回当前结点
    // 如果data小于当前结点，那么递归查找当前结点的左子树
    // 否则，递归查找当前结点的右子树
    private TreeNode<E> search(TreeNode<E> node, E data) {
        if (node == null) {
            return null;
        }
        int res = data.compareTo(node.data);
        if (res > 0) {
            return search(node.rightChild, data);
        } else if (res < 0) {
            return search(node.leftChild, data);
        } else {
            return node;
        }
    }

    // 删除一个结点
    public void delete(E data) {
        root = delete(root, data);
    }

    private TreeNode<E> delete(TreeNode<E> node, E data) {
        if (node == null) {
            return null;
        }
        int res = data.compareTo(node.data);
        if (res > 0) {
            node.rightChild = delete(node.rightChild, data);
        } else if (res < 0) {
            node.leftChild = delete(node.leftChild, data);
            // 删除一个结点不仅包括将自己赋为null，而且其父节点也要将其左子树/右子树赋为null
            // 以下为找到的情况
            // 如果找到的结点既没有左子树又没有右子树，那么直接设为null
            // 如果只有左子树或只有右子树，那么将当前结点赋值为不为空的那一个子树
            // 如果都有，那么找到当前结点的右子树中值最小的那个结点，删除它（因为它一定没有左子树，所以采用第二种删除策略即可）
            // 然后将当前结点赋值为最小值
        } else if (node.leftChild != null && node.rightChild != null) {
            // 进行一次删除，删除的是以node的右子树为根节点，值为min的这个结点
            // 包含了结点只有左子树或右子树或都没有的情况
            node.data = findMin(node.rightChild).data;
            node.rightChild = delete(node.rightChild, node.data);
        } else {
            node = node.leftChild == null ? node.rightChild : node.leftChild;
        }
        return node;
    }


    // 返回当前子树中的值最小的那个结点
    private TreeNode<E> findMin(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // 后序遍历二叉树的深度
    public int depth() {
        return depth(root);
    }

    private int depth(TreeNode<E> subTree) {
        if (subTree == null) {
            return 0;
            // 空树深度为0，递归结束，开始返回
        } else {
            // 这种情况实际上包含了两种情况：一种是叶子结点，此时返回的是1；另一种是非叶子结点，返回的是较深子树的深度+1
            // 综合起来就是每遇到叶子结点，深度就加1，得到子树的深度，最后加上根节点的深度1
            int leftDepth = depth(subTree.leftChild);
            int rightDepth = depth(subTree.rightChild);
            return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
            /*
             * }else if(subTree.leftChild == null || subTree.rightChild == null){
             * return 1; }
             */
        }
    }

    // 二叉树的结点个数
    public int size() {
        return size(root);
    }

    private int size(TreeNode<E> subTree) {
        if (subTree == null) {
            return 0;
        } else {
            int leftSubTreeSize = size(subTree.leftChild);
            int rightSubTreeSize = size(subTree.rightChild);
            return leftSubTreeSize + rightSubTreeSize + 1;
        }
    }

    public TreeNode<E> parent(TreeNode<E> element) {
        if (element == null) {
            return null;
        }
        return parent(root, element);
    }

    // 第一个参数subTree是指该结点之下进行搜索，第二个参数element是要找父节点的子节点
    private TreeNode<E> parent(TreeNode<E> subTree, TreeNode<E> element) {
        TreeNode<E> parent;
        if (subTree == null) {
            return null;
        } else {
            if (subTree.leftChild == element || subTree.rightChild == element) {
                return subTree;
            } else {
                parent = parent(subTree.leftChild, element);
                // 在左子树中递归搜索element的父节点
                return parent != null ? parent : parent(subTree.rightChild, element);
                // 如果在左子树中找到了，就返回；没有找到，就在右子树中搜索
                // 如果都没有找到，就返回null
            }
        }
    }

    private TreeNode<E> leftChild(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        return node.leftChild;
    }

    private TreeNode<E> rightChild(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        return node.rightChild;
    }

    public TreeNode<E> root() {
        return root;
    }

    // 清空应该每种遍历方式都可行，在这里采用先序遍历
    public void clear(TreeNode<E> subTree) {
        if (subTree != null) {
            subTree.data = null;
            clear(subTree.leftChild);
            clear(subTree.rightChild);
        }
    }

    public void preOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            System.out.print(subTree.data + " ");
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    public void inOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            inOrder(subTree.leftChild);
            System.out.print(subTree.data + " ");
            inOrder(subTree.rightChild);
        }
    }

    public void postOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            System.out.print(subTree.data + " ");
        }
    }

    // 非递归 先序遍历
    public void noRecPreOrder(TreeNode<E> subTree) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (subTree != null || !stack.isEmpty()) {
            while (subTree != null) {
                System.out.print(subTree.data + " ");
                stack.push(subTree);
                subTree = subTree.leftChild;// 访问左子树
            }
            if (!stack.isEmpty()) {
                subTree = stack.pop();
                subTree = subTree.rightChild;// 访问右子树
            }
        }
    }

    // 非递归 中序遍历
    public void noRecInOrder(TreeNode<E> subTree) {
        Stack<TreeNode<E>> stack = new Stack<>();
        // subTree不为空，则表示存在，需要进行访问；栈不为空表示没有全部遍历完。只要当前没有遍历完或整体没有遍历完，都进入循环体
        // 如果subTree不为空，则先访问其左子树，在此之前需要将结点入栈（每次访问左子树之前都将当前结点入栈）
        // 如果为空，表示左子树不存在，那么出栈，访问右子树，重新进入循环体
        while (subTree != null || !stack.isEmpty()) {
            while (subTree != null) {
                // while表示不断地访问其左子树，直至达到左子树的最底端
                stack.push(subTree);
                subTree = subTree.leftChild;// 访问左子树
            }
            if (!stack.isEmpty()) {
                subTree = stack.pop();
                System.out.print(subTree.data + " ");// 访问数据域
                subTree = subTree.rightChild;// 访问右子树
            }
        }
    }

    // 使用一个标记上一个访问结点的引用来实现非递归后序遍历
    public void noRecPostOrder(TreeNode<E> subTree) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> lastVisited = null;
        // 这里不需要加上while循环，因为当栈空的时候，就可知遍历已经结束，subTree又回到了根节点
        while (subTree != null) {
            stack.push(subTree);
            subTree = subTree.leftChild;// 访问左子树
        }
        while (!stack.isEmpty()) {
            // 在这里，可以保证已经遍历到左子树的底端
            subTree = stack.pop();
            // 回到当前结点
            if (subTree.rightChild == null || subTree.rightChild == lastVisited) {
                // 一个根节点被访问的前提是：无右子树或右子树已被访问过
                System.out.print(subTree.data + " ");
                lastVisited = subTree;
            } else {
                // 此时在左子树的底端，并且有右子树或者右子树尚未被访问，那么需要访问右子树
                // 访问右子树之前也要入栈，以保证在访问完右子树后能回到当前结点访问数据域
                stack.push(subTree);
                subTree = subTree.rightChild;
                // 进入右子树，并且可以保证右子树不为空，那么在右子树中继续进行后序遍历，先访问左子树直至左子树底端
                // 之后就回到循环体的开始，检查右子树
                while (subTree != null) {
                    stack.push(subTree);
                    subTree = subTree.leftChild;// 访问左子树
                }
            }
        }
    }

    public int leafSize() {
        return leafSize(root);
    }

    private int leafSize(TreeNode<E> subTree) {
        if (subTree == null) {
            return 0;
        } else if (subTree.leftChild == null && subTree.rightChild == null) {
            return 1;
        } else {
            // 如果不是叶子结点，那么分别求左子树的叶子结点和右子树的叶子结点，相加后返回
            return leafSize(subTree.leftChild) + leafSize(subTree.rightChild);
        }
    }

    // 二叉树的复制，返回整棵一模一样的二叉树
    // 采用先序遍历的方式
    public BinarySearchTree<E> clone() {
        BinarySearchTree<E> tree = new BinarySearchTree<>();
        tree.root = clone(root);
        return tree;
    }

    private TreeNode<E> clone(TreeNode<E> subTree) {
        if (subTree != null) {
            TreeNode<E> newNode = new TreeNode<>(subTree.data);
            newNode.leftChild = clone(subTree.leftChild);
            newNode.rightChild = clone(subTree.rightChild);
            return newNode;
        } else {
            return null;
        }
    }

    // 使用先序遍历的字符串和中序遍历的字符串建树，不依赖于空白字符/特殊字符
    // 只能创建存储字符的二叉树
    public static BinarySearchTree<Character> preInCreateBiTree(String preStr, String inStr) {
        char[] pre = preStr.toCharArray();
        char[] in = inStr.toCharArray();
        return new BinarySearchTree<>(createSubTree(pre, in, 0, 0, pre.length));
    }

    /**
     * 这个算法很垃圾
     */
    private static TreeNode<Character> createSubTree(char[] pre, char[] in, int preStart, int inStart, int size) {
        if (size == 0) {
            return null;
        }
        TreeNode<Character> subTree = new TreeNode<>(pre[preStart]);
        int mid = indexOf(in, pre[preStart]);
        if (mid != inStart) {
            subTree.leftChild = createSubTree(pre, in, preStart + 1, inStart, mid - inStart);
        }
        // mid-inStart 为左子树的总数
        if (mid != inStart + size - 1) {
            // 两个if是为了跳过没有左子树或没有右子树的情况
            // 没有右子树就是 右子树的开始就是结束 ； 开始是mid+1 ，当mid = inStart+size-1，则 开始为
            // inStart+size，而结束也为它，所以没有必要创建右子树
            subTree.rightChild = createSubTree(pre, in, preStart + 1 + (mid - inStart), mid + 1,
                    size - (mid - inStart) - 1);
        }
        // size - (mid -inStart) - 1)是右子树的总数，是所有结点总数size减去左子树的总数，再减去开头创建的根节点1个
        return subTree;
    }

    private static int indexOf(char[] arr, char ch) {
        for (int i = 0; i < arr.length; i++) {
            if (ch == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    // 使用先序遍历字符串建树，只能创建存储字符的二叉树
    public static BinarySearchTree<Character> createBiTree(String preStr) {
        char[] pre = preStr.toCharArray();
        BinarySearchTree<Character> tree = new BinarySearchTree<>(createSubTree(pre));
        index = -1;
        return tree;
    }

    private static TreeNode<Character> createSubTree(char[] pre) {
        index++;
        TreeNode<Character> subTree = null;
        if (pre[index] != '#') {
            subTree = new TreeNode<>(pre[index]);
            subTree.leftChild = createSubTree(pre);
            subTree.rightChild = createSubTree(pre);
        }
        return subTree;
    }

    // 输出二叉树从根节点到每个叶子结点的路径
    public void printAllBiTreePaths() {
        if (pathList == null) {
            pathList = new LinkedList<>();
        }
        printBiTreePath(root);
        pathList.clear();
    }

    private void printBiTreePath(TreeNode<E> node) {
        if (node != null) {
            pathList.addLast(node);
            if (node.leftChild == null && node.rightChild == null) {
                for (TreeNode<E> treeNode : pathList) {
                    System.out.print(treeNode.data + " ");
                }
                System.out.println();
                // 输出链表从根节点到叶子的数据域
            } else {
                printBiTreePath(node.leftChild);
                printBiTreePath(node.rightChild);
            }
            pathList.removeLast();
            // 处理完当前结点，退出链表
        }
    }

    /**
     * 使用tag来标记是第几次访问 tag = 0 是尚未访问 tag = 1 是已经访问过，等待最后的打印
     *
     * @param subTree
     */
    public void noRecPostOrderUseTag(TreeNode<E> subTree) {
        Stack<StackNode<E>> stack = new Stack<>();
        StackNode<E> stkPtr = null;
        while (true) {
            while (subTree != null) {
                stkPtr = new StackNode<>(subTree, 0);
                stack.push(stkPtr);
                subTree = subTree.leftChild;
            }
            // 向左移动，并压栈
            stkPtr = stack.pop();
            subTree = stkPtr.ptr;

            // 当tag == 1时
            while (stkPtr.tag == 1) {// 从右子树回来
                System.out.print(subTree.data + " ");
                if (stack.isEmpty()) {
                    return;
                } else {
                    stkPtr = stack.pop();
                    subTree = stkPtr.ptr;
                }
            }
            // 当tag == 0时
            // 从左子树回来
            stkPtr.tag = 1;
            stack.push(stkPtr);
            subTree = subTree.rightChild;
        }
    }

    // 层序遍历，使用queue
    public void levelOrder() {
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        TreeNode<E> node = root;
        while (node != null) {
            System.out.print(node.data + " ");
            if (node.leftChild != null) {
                queue.add(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild);
            }
            if (!queue.isEmpty()) {
                node = queue.poll();
            } else {
                return;
            }
        }
        System.out.println();
    }


    public static BinarySearchTree<Character> preInCreateBiTreeUseSubString(String pre, String in) {
        return new BinarySearchTree<>(createSubTreeUseSubString(pre, in));
    }

    // 这个算法更好，不使用下标，直接取子串
    private static TreeNode<Character> createSubTreeUseSubString(String pre, String in) {
        TreeNode<Character> subTree = null;
        if (pre.length() == 0) {
            return null;
        } else {
            subTree = new TreeNode<>(pre.charAt(0));
            int mid = in.indexOf(pre.charAt(0));
            subTree.leftChild = createSubTreeUseSubString(pre.substring(1, mid + 1), in.substring(0, mid));
            subTree.rightChild = createSubTreeUseSubString(pre.substring(mid + 1), in.substring(mid + 1));
        }
        return subTree;
    }
}

class StackNode<E extends Comparable<E>> {
    TreeNode<E> ptr;
    int tag;// 用于非递归后序遍历
    // 第一次压栈tag = 0
    // 第二次压栈tag = 1

    public StackNode(TreeNode<E> ptr, int tag) {
        this.ptr = ptr;
        this.tag = tag;
    }

}

class TreeNode<E extends Comparable<E>> {
    E data;
    TreeNode<E> leftChild;
    TreeNode<E> rightChild;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode(E data, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
