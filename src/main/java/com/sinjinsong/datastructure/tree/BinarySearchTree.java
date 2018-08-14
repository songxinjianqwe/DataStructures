package com.sinjinsong.datastructure.tree;

import java.util.*;

/**
 * Java实现二叉查找树 使用递归的操作一般设置两个方法，一个方法是public的（用于调用的），一个方法是private（实际递归的）
 *
 * @author New Song
 */
public class BinarySearchTree<E extends Comparable<E>> implements Cloneable {
    private TreeNode<E> root;
    // 用于使用先序遍历字符串建树
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
    public void createCharBiTree(TreeNode<E> node, E data) {
        if (root == null) {
            root = new TreeNode<E>(data);
        } else {
            if (data.compareTo(node.val) < 0) {
                if (node.left == null) {
                    node.left = new TreeNode<E>(data);
                } else {
                    createCharBiTree(node.left, data);
                }
            } else {
                if (node.right == null) {
                    node.right = new TreeNode<E>(data);
                } else {
                    createCharBiTree(node.right, data);
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
            if (data.compareTo(node.val) < 0) {
                node.left = insert(node.left, data);
            } else if (data.compareTo(node.val) > 0) {
                node.right = insert(node.right, data);
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
        int res = data.compareTo(node.val);
        if (res > 0) {
            return search(node.right, data);
        } else if (res < 0) {
            return search(node.left, data);
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
        int res = data.compareTo(node.val);
        if (res > 0) {
            node.right = delete(node.right, data);
        } else if (res < 0) {
            node.left = delete(node.left, data);
            // 删除一个结点不仅包括将自己赋为null，而且其父节点也要将其左子树/右子树赋为null
            // 以下为找到的情况
            // 如果找到的结点既没有左子树又没有右子树，那么直接设为null
            // 如果只有左子树或只有右子树，那么将当前结点赋值为不为空的那一个子树
            // 如果都有，那么找到当前结点的右子树中值最小的那个结点，删除它（因为它一定没有左子树，所以采用第二种删除策略即可）
            // 然后将当前结点赋值为最小值
        } else if (node.left != null && node.right != null) {
            // 进行一次删除，删除的是以node的右子树为根节点，值为min的这个结点
            // 包含了结点只有左子树或右子树或都没有的情况
            node.val = findMin(node.right).val;
            node.right = delete(node.right, node.val);
        } else {
            node = node.left == null ? node.right : node.left;
        }
        return node;
    }


    // 返回当前子树中的值最小的那个结点
    private TreeNode<E> findMin(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode<E> findMax(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
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

    private int depth(TreeNode<E> curr) {
        if (curr == null) {
            return 0;
            // 空树深度为0，递归结束，开始返回
        } else {
            // 这种情况实际上包含了两种情况：一种是叶子结点，此时返回的是1；另一种是非叶子结点，返回的是较深子树的深度+1
            // 综合起来就是每遇到叶子结点，深度就加1，得到子树的深度，最后加上根节点的深度1
            int leftDepth = depth(curr.left);
            int rightDepth = depth(curr.right);
            return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
        }
    }

    /**
     * 非递归求深度，采用层序遍历的方式
     *
     * @return
     */
    public int depthNoRec() {
        TreeNode<E> curr;
        Deque<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // 一层进行遍历,queue的长度即为该层节点个数
            for (int i = 0; i < levelSize; i++) {
                curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            depth++;
        }
        return depth;
    }

    // 二叉树的结点个数
    public int size() {
        return size(root);
    }

    private int size(TreeNode<E> subTree) {
        if (subTree == null) {
            return 0;
        } else {
            int leftSubTreeSize = size(subTree.left);
            int rightSubTreeSize = size(subTree.right);
            return leftSubTreeSize + rightSubTreeSize + 1;
        }
    }

    public int sizeOfLevel(int k) {
        return sizeOfLevel(root, k);
    }

    /**
     * 第k层节点个数就是第k-1层的孩子数
     * k==0时，返回1或0
     *
     * @param curr
     * @param k
     * @return
     */
    private int sizeOfLevel(TreeNode<E> curr, int k) {
        if (curr == null) {
            return 0;
        }
        if (k == 0) {
            return 1;
        } else {
            return sizeOfLevel(curr.left, k - 1) + sizeOfLevel(curr.right, k - 1);
        }
    }


    public TreeNode<E> parent(TreeNode<E> curr) {
        if (curr == null) {
            return null;
        }
        return parent(root, curr);
    }

    /**
     * 先序遍历
     *
     * @param root
     * @param curr
     * @return
     */
    private TreeNode<E> parent(TreeNode<E> root, TreeNode<E> curr) {
        TreeNode<E> parent;
        if (root == null) {
            return null;
        } else {
            if (root.left == curr || root.right == curr) {
                return root;
            } else {
                parent = parent(root.left, curr);
                // 在左子树中递归搜索element的父节点
                return parent != null ? parent : parent(root.right, curr);
                // 如果在左子树中找到了，就返回；没有找到，就在右子树中搜索
                // 如果都没有找到，就返回null
            }
        }
    }


    private TreeNode<E> leftChild(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        return node.left;
    }

    private TreeNode<E> rightChild(TreeNode<E> node) {
        if (node == null) {
            return null;
        }
        return node.right;
    }

    public TreeNode<E> root() {
        return root;
    }

    // 清空应该每种遍历方式都可行，在这里采用先序遍历
    public void clear(TreeNode<E> subTree) {
        if (subTree != null) {
            subTree.val = null;
            clear(subTree.left);
            clear(subTree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            System.out.print(subTree.val + " ");
            preOrder(subTree.left);
            preOrder(subTree.right);
        }
    }

    public String preOrderToString(TreeNode<E> subTree) {
        StringBuilder sb = new StringBuilder();
        preOrderToString(subTree, sb);
        return sb.toString();
    }

    private void preOrderToString(TreeNode<E> subTree, StringBuilder sb) {
        if (subTree != null) {
            sb.append(subTree.val).append("!");
            preOrderToString(subTree.left, sb);
            preOrderToString(subTree.right, sb);
        } else {
            sb.append("#!");
        }
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            inOrder(subTree.left);
            System.out.print(subTree.val + " ");
            inOrder(subTree.right);
        }
    }

    public List<E> inOrderToList(TreeNode<E> curr) {
        List<E> inOrderList = new ArrayList<>();
        inOrderToList(curr, inOrderList);
        return inOrderList;
    }

    private void inOrderToList(TreeNode<E> subTree, List<E> inOrderList) {
        if (subTree != null) {
            inOrderToList(subTree.left, inOrderList);
            inOrderList.add(subTree.val);
            inOrderToList(subTree.right, inOrderList);
        }
    }


    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(TreeNode<E> subTree) {
        if (subTree != null) {
            postOrder(subTree.left);
            postOrder(subTree.right);
            System.out.print(subTree.val + " ");
        }
    }

    public void noRecPreOrder() {
        noRecPreOrder(root);
    }

    // 非递归 先序遍历
    private void noRecPreOrder(TreeNode<E> curr) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                System.out.print(curr.val + " ");
                stack.push(curr);
                curr = curr.left;// 访问左子树
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                curr = curr.right;// 访问右子树
            }
        }
        System.out.println();
    }

    public void noRecInOrder() {
        noRecInOrder(root);
    }

    // 非递归 中序遍历
    private void noRecInOrder(TreeNode<E> curr) {
        Stack<TreeNode<E>> stack = new Stack<>();
        // subTree不为空，则表示存在，需要进行访问；栈不为空表示没有全部遍历完。只要当前没有遍历完或整体没有遍历完，都进入循环体
        // 如果subTree不为空，则先访问其左子树，在此之前需要将结点入栈（每次访问左子树之前都将当前结点入栈）
        // 如果为空，表示左子树不存在，那么出栈，访问右子树，重新进入循环体
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                // while表示不断地访问其左子树，直至达到左子树的最底端
                stack.push(curr);
                curr = curr.left;// 访问左子树
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                System.out.print(curr.val + " ");// 访问数据域
                curr = curr.right;// 访问右子树
            }
        }
        System.out.println();
    }


    public void noRecPostOrder() {
        noRecPostOrderByTwoStack(root);
    }

    /**
     * 将根结点压入第一个栈stk；弹出stk栈顶的结点，并把该结点压入第二个栈stk2；
     * 1）将当前结点的左孩子和右孩子先后分别入栈stk；当所有元素都压入stk2后，依次弹出stk2的栈顶结点，并访问之。
     * 2）第一个栈的入栈顺序是：根结点，左孩子和右孩子；压入第二个栈的顺序是：根结点，右孩子和左孩子。
     * 3）因此，第二个栈弹出的顺序就是：左孩子，右孩子和根结点。
     *
     * @param curr
     */
    private void noRecPostOrderByTwoStack(TreeNode<E> curr) {
        // 先压根，后弹根(root)，压左孩子，压右孩子
        Stack<TreeNode<E>> vlrStack = new Stack<>();
        // 先压根(root)，压右孩子，压左孩子
        Stack<TreeNode<E>> vrlStack = new Stack<>();
        vlrStack.push(curr);
        while (!vlrStack.isEmpty()) {
            curr = vlrStack.pop();
            if (curr.left != null) {
                vlrStack.push(curr.left);
            }
            if (curr.right != null) {
                vlrStack.push(curr.right);
            }
            vrlStack.push(curr);
        }
        while (!vrlStack.isEmpty()) {
            System.out.print(vrlStack.pop().val + " ");
        }
        System.out.println();
    }


    public int leafSize() {
        return leafSize(root);
    }

    private int leafSize(TreeNode<E> subTree) {
        if (subTree == null) {
            return 0;
        } else if (subTree.left == null && subTree.right == null) {
            return 1;
        } else {
            // 如果不是叶子结点，那么分别求左子树的叶子结点和右子树的叶子结点，相加后返回
            return leafSize(subTree.left) + leafSize(subTree.right);
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
            TreeNode<E> newNode = new TreeNode<>(subTree.val);
            newNode.left = clone(subTree.left);
            newNode.right = clone(subTree.right);
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
        return new BinarySearchTree<>(createCharSubTree(pre, in, 0, 0, pre.length));
    }

    /**
     * 这个算法很垃圾
     */
    private static TreeNode<Character> createCharSubTree(char[] pre, char[] in, int preStart, int inStart, int size) {
        if (size == 0) {
            return null;
        }
        TreeNode<Character> subTree = new TreeNode<>(pre[preStart]);
        int mid = indexOf(in, pre[preStart]);
        if (mid != inStart) {
            subTree.left = createCharSubTree(pre, in, preStart + 1, inStart, mid - inStart);
        }
        // mid-inStart 为左子树的总数
        if (mid != inStart + size - 1) {
            // 两个if是为了跳过没有左子树或没有右子树的情况
            // 没有右子树就是 右子树的开始就是结束 ； 开始是mid+1 ，当mid = inStart+size-1，则 开始为
            // inStart+size，而结束也为它，所以没有必要创建右子树
            subTree.right = createCharSubTree(pre, in, preStart + 1 + (mid - inStart), mid + 1,
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
    public static BinarySearchTree<Character> createCharBiTree(String preStr) {
        char[] pre = preStr.toCharArray();
        BinarySearchTree<Character> tree = new BinarySearchTree<>(createCharSubTree(pre));
        index = -1;
        return tree;
    }

    // 使用先序遍历字符串建树，只能创建存储字符的二叉树
    public static BinarySearchTree<Integer> createIntBiTree(String preStr) {
        char[] pre = preStr.toCharArray();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(createIntSubTree(pre));
        index = -1;
        return tree;
    }

    /**
     * 每个节点数据后加一个！，如果是空节点，那么用#！表示
     * 比如12!3!#!#!#!
     * 12
     * /  \
     * 3   null
     *
     * @param preStr
     * @return
     */
    public static BinarySearchTree<String> createStringBiTree(String preStr) {
        String[] pre = preStr.split("!");
        BinarySearchTree<String> tree = new BinarySearchTree<>(createStringSubTree(pre));
        index = -1;
        return tree;
    }


    /**
     * 使用先序遍历字符串构造二叉树
     * 需要一个全局变量index
     *
     * @param pre
     * @return
     */
    private static TreeNode<Character> createCharSubTree(char[] pre) {
        index++;
        TreeNode<Character> subTree = null;
        if (pre[index] != '#') {
            subTree = new TreeNode<>(pre[index]);
            subTree.left = createCharSubTree(pre);
            subTree.right = createCharSubTree(pre);
        }
        return subTree;
    }

    private static TreeNode<Integer> createIntSubTree(char[] pre) {
        index++;
        TreeNode<Integer> subTree = null;
        if (pre[index] != '#') {
            subTree = new TreeNode<>(pre[index] - '0');
            subTree.left = createIntSubTree(pre);
            subTree.right = createIntSubTree(pre);
        }
        return subTree;
    }

    private static TreeNode<String> createStringSubTree(String[] pre) {
        index++;
        TreeNode<String> subTree = null;
        if (!pre[index].equals("#")) {
            subTree = new TreeNode<>(pre[index]);
            subTree.left = createStringSubTree(pre);
            subTree.right = createStringSubTree(pre);
        }
        return subTree;
    }

    // 输出二叉树从根节点到每个叶子结点的路径
    public void printAllBiTreePaths() {
        printBiTreePath(root, new LinkedList<>());
    }

    /**
     * 类似于先序遍历
     *
     * @param node
     */
    private void printBiTreePath(TreeNode<E> node, LinkedList<TreeNode<E>> pathList) {
        if (node != null) {
            pathList.addLast(node);
            if (node.left == null && node.right == null) {
                for (TreeNode<E> treeNode : pathList) {
                    System.out.print(treeNode.val + " ");
                }
                System.out.println();
                // 输出链表从根节点到叶子的数据域
            } else {
                printBiTreePath(node.left, pathList);
                printBiTreePath(node.right, pathList);
            }
            pathList.removeLast();
            // 处理完当前结点，退出链表
        }
    }

    // 输出二叉树从根节点到每个叶子结点的路径
    public List<List<Integer>> findPathSumEquaiTo(TreeNode<Integer> root, int expectedSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPathSumEqualTo(root, new LinkedList<>(), result, 0, expectedSum);
        return result;
    }


    /**
     * 类似于先序遍历
     *
     * @param node
     */
    private void findPathSumEqualTo(TreeNode<Integer> node, LinkedList<Integer> pathList, List<List<Integer>> result, int currentSum, int expectedSum) {
        if (node != null) {
            pathList.addLast(node.val);
            currentSum += node.val;
            if (node.left == null && node.right == null && currentSum == expectedSum) {
                result.add(new ArrayList<>(pathList));
                System.out.println();
                // 输出链表从根节点到叶子的数据域
            } else {
                findPathSumEqualTo(node.left, pathList, result, currentSum, expectedSum);
                findPathSumEqualTo(node.right, pathList, result, currentSum, expectedSum);
            }
            pathList.removeLast();
            // 处理完当前结点，退出链表
        }
    }

    /**
     * 使用一个标记上一个访问结点的引用来实现非递归后序遍历
     */
    private void noRecPostOrderByOneStack(TreeNode<E> curr) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> lastVisited = null;
        // 这里不需要加上while循环，因为当栈空的时候，就可知遍历已经结束，subTree又回到了根节点
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;// 访问左子树
        }
        while (!stack.isEmpty()) {
            // 在这里，可以保证已经遍历到左子树的底端
            curr = stack.pop();
            // 回到当前结点
            if (curr.right == null || curr.right == lastVisited) {
                // 一个根节点被访问的前提是：无右子树或右子树已被访问过
                System.out.print(curr.val + " ");
                lastVisited = curr;
            } else {
                // 此时在左子树的底端，并且有右子树或者右子树尚未被访问，那么需要访问右子树
                // 访问右子树之前也要入栈，以保证在访问完右子树后能回到当前结点访问数据域
                stack.push(curr);
                curr = curr.right;
                // 进入右子树，并且可以保证右子树不为空，那么在右子树中继续进行后序遍历，先访问左子树直至左子树底端
                // 之后就回到循环体的开始，检查右子树
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;// 访问左子树
                }
            }
        }
    }

    /**
     * 使用tag来标记是第几次访问 tag = 0 是尚未访问 tag = 1 是已经访问过，等待最后的打印
     *
     * @param subTree
     */
    public void noRecPostOrderByTag(TreeNode<E> subTree) {
        Stack<StackNode<E>> stack = new Stack<>();
        StackNode<E> stkPtr = null;
        while (true) {
            while (subTree != null) {
                stkPtr = new StackNode<>(subTree, 0);
                stack.push(stkPtr);
                subTree = subTree.left;
            }
            // 向左移动，并压栈
            stkPtr = stack.pop();
            subTree = stkPtr.ptr;

            // 当tag == 1时
            while (stkPtr.tag == 1) {// 从右子树回来
                System.out.print(subTree.val + " ");
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
            subTree = subTree.right;
        }
    }

    // 层序遍历，使用queue
    public void levelOrder() {
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode<E> node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 层序遍历，并且打印一层就换一行
     * last指向当前行的最后一个结点，如果访问完当前节点，并且将左右孩子入队后，
     * 发现自己就是last，那么将last更新为nextLast（下一行的最后一个结点），并且换行。
     * nextLast在孩子入队时更新，last在当前节点遍历完后有可能会被更新
     */
    public void levelOrderWithLine() {
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        TreeNode<E> node = null;
        // 当前行最右节点
        TreeNode<E> last = root;
        // 下一行最右节点
        TreeNode<E> nextLast = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.val + "   ");
            if (node.left != null) {
                queue.add(node.left);
                nextLast = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLast = node.right;
            }
            // 自己就是当前行的最后一个节点，则更新last为下一行的最后一个节点，并且换行
            if (node == last && nextLast != null) {
                last = nextLast;
                nextLast = null;
                System.out.println();
            }
        }
    }


    /**
     * 节点数不多于500
     *
     * @return
     */
    public List<List<E>> levelOrderToListWithLine() {
        List<List<E>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        TreeNode<E> node = null;
        // 当前行最右节点
        TreeNode<E> last = root;
        // 下一行最右节点
        TreeNode<E> nextLast = null;
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            result.get(level).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLast = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLast = node.right;
            }
            // 自己就是当前行的最后一个节点，则更新last为下一行的最后一个节点，并且换行
            if (node == last && nextLast != null) {
                last = nextLast;
                nextLast = null;
                level++;
                result.add(new ArrayList());
            }
        }
        return result;
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
            subTree.left = createSubTreeUseSubString(pre.substring(1, mid + 1), in.substring(0, mid));
            subTree.right = createSubTreeUseSubString(pre.substring(mid + 1), in.substring(mid + 1));
        }
        return subTree;
    }

    /**
     * 二叉树反转
     *
     * @param node
     * @return
     */
    public TreeNode<E> invert(TreeNode<E> node) {
        if (node != null) {
            if (node.left != null) {
                node.left = invert(node.left);
            }
            if (node.right != null) {
                node.right = invert(node.right);
            }
            TreeNode<E> temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return node;
    }

    public boolean isSubTree(BinarySearchTree<E> tree) {
        return isSubTree(root, tree.root);
    }

    /**
     * 判断rhs是否是lhs的一棵子树（一直到叶）
     * 注意：
     * {1}不是{1,2,3,4,5,6,7}（层序遍历）的子树
     * {1,#,1}是{1,1,1,#,1,1,#}的子树
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private boolean isSubTree(TreeNode<E> lhs, TreeNode<E> rhs) {
        // 空树是任何树的子树
        if (rhs == null) {
            return true;
            // lhs为空且rhs不为空，那么一定不是子树
        } else if (lhs == null) {
            return false;
            // 从当前节点开始判断是否是相同树
        } else if (lhs.val.equals(rhs.val) && isSame(lhs.left, rhs.left) && isSame(lhs.right, rhs.right)) {
            return true;
        } else {
            // 判断左孩子或右孩子的子树关系
            return isSubTree(lhs.left, rhs) || isSubTree(lhs.right, rhs);
        }
    }

    /**
     * 判断两棵子树是否是一样的
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private boolean isSame(TreeNode<E> lhs, TreeNode<E> rhs) {
        if (lhs == null && rhs == null) {
            return true;
        } else if (lhs != null && rhs != null) {
            return lhs.val.equals(rhs.val) && isSame(lhs.left, rhs.left) && isSame(rhs.right, rhs.right);
        } else {
            return false;
        }
    }


    public boolean isSubTreeBySerialization(BinarySearchTree<E> tree) {
        return preOrderToString(root).indexOf(preOrderToString(tree.root)) != -1;
    }


    /**
     * 统计完全二叉树的节点个数，时间复杂度为O(log n)
     *
     * @return
     */
    public int countCompleteBinaryTree() {
        return countCompleteBinaryTree(root);
    }

    /**
     * 判断是否是完全二叉树
     * 算法：
     * 1）采用层序遍历的方式
     * 2）如果左子树为空，且右子树不为空，则返回false
     * 3）如果左子树不为空，且右子树为空，则后续节点必须全为叶子节点
     * 4）循环结束后返回true
     *
     * @return
     */
    public boolean isCompleteBinaryTree() {
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode<E> curr;
        boolean nextLeaf = false;
        while (!queue.isEmpty()) {
            // 访问节点，检查一些要求
            curr = queue.poll();
            // 不符合完全二叉树的定义
            if (curr.left == null && curr.right != null) {
                return false;
            }
            // 如果要求本身是叶子节点，但并不是，则退出
            if (nextLeaf && (curr.left != null || curr.right != null)) {
                return false;
            }
            // 左孩子不为空，右孩子为空，则后续节点都必须为叶子节点
            if (curr.left != null && curr.right == null) {
                nextLeaf = true;
            }

            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
        return true;
    }

    /**
     * 给定一颗完全二叉树的头节点head，返回这棵树的节点个数。如果完全二叉树的节点数为N，实现时间复杂度为O(h^2)/O(logn^2)的解法。
     * 完全二叉树要求插入和删除都必须在叶节点、插入时叶节点满则新增一层。
     * count(treenode)
     * 1）找到二叉树左子树最左节点，同时统计二叉树高度leftH
     * 2）找到二叉树右子树最右节点，同时统计二叉树高度rightH
     * 3）如果两个高度相同，那么说明左子树是一棵满二叉树，已知高度，节点数为2^h -1，再加上头节点的1，剩余的节点（右子树）可以采用递归的方法计算。求和即为总数。
     * 4）如果两个高度不同，那么说明右子树也是一棵满二叉树，只是比左子树少一层。可以根据公式求得右子树的个数，加上头节点的1，剩余的节点（左子树）可以采用递归的方法计算，求和即为总数。
     *
     * @param root
     * @return
     */
    private int countCompleteBinaryTree(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        TreeNode<E> leftSubTree = root.left;
        int leftHeight = 0;
        while (leftSubTree != null) {
            leftHeight++;
            leftSubTree = leftSubTree.left;
        }
        TreeNode<E> rightSubTree = root.right;
        int rightHeight = 0;
        while (rightSubTree != null) {
            rightHeight++;
            rightSubTree = rightSubTree.left;
        }
        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) + countCompleteBinaryTree(root.right);
        } else {
            return (int) Math.pow(2, rightHeight) + countCompleteBinaryTree(root.left);
        }
    }

    /**
     * 判断是否是平衡二叉树
     *
     * @return
     */
    public boolean isAVL() {
        return isAVL(root);
    }

    private boolean isAVL(TreeNode<E> curr) {
        if (curr == null) {
            return true;
        }
        // 先判断左右孩子是否是AVL，如果不是，那么直接返回false
        // 如果左右孩子都是AVL，那么判断自己是否是AVL
        if (isAVL(curr.left) && isAVL(curr.right)) {
            return Math.abs(depth(curr.left) - depth(curr.right)) <= 1;
        } else {
            return false;
        }
    }

    /**
     * 是否是二叉搜索树
     *
     * @return
     */
    public boolean isBST() {
        return isBSTByInOrder(root);
    }


//    // 这个算法是错误的，因为左孩子<根<右孩子并不能证明max(左子树)<根<min(右孩子)
//    private boolean isBSTByQueryingMaxMin(TreeNode<E> curr) {
//        if (curr == null) {
//            return true;
//        }
//        if (isBSTByQueryingMaxMin(curr.left) && isBSTByQueryingMaxMin(curr.right)) {
//            return (curr.left != null ? curr.val.compareTo(curr.left.val) > 0 : true) &&
//                    (curr.right != null ? curr.val.compareTo(curr.right.val) < 0 : true);
//        } else {
//            return false;
//        }
//    }

    /**
     * 时间复杂度是 O(n * h)
     * 原因是每做一次递归（向下走），我们都要去查找最大值和最小值。
     *
     * @param curr
     * @return
     */
    private boolean isBSTByQueryingMaxMin(TreeNode<E> curr) {
        if (curr == null) {
            return true;
        }
        if (curr.left != null && findMax(curr.left).val.compareTo(curr.val) > 0) {
            return false;
        }
        if (curr.right != null && findMin(curr.right).val.compareTo(curr.val) < 0) {
            return false;
        }
        return isBSTByQueryingMaxMin(curr.left) && isBSTByQueryingMaxMin(curr.right);
    }

    /**
     * 不必每次都要去查找最小值和最大值，可以采用参数传递的方式
     * 时间复杂度为O(n)
     * min和max，对于Integer而言，是-2147483648~2147483647
     *
     * @param curr
     * @return
     */
    public boolean isBSTByPassingMaxMin(TreeNode<E> curr, E min, E max) {
        if (curr == null) {
            return true;
        }
        if (min.compareTo(curr.val) > 0 || max.compareTo(curr.val) < 0) {
            return false;
        }
        // 对于左子树而言，其最大值为根；对于右子树而言，其最小值为根
        return isBSTByPassingMaxMin(curr.left, min, curr.val) && isBSTByPassingMaxMin(curr.right, curr.val, max);
    }

    /**
     * 中序遍历得到一个序列，如果是有序的，那么一定是BST
     * 时间复杂度为O(n)，空间复杂度为O(n)
     *
     * @param curr
     * @return
     */
    private boolean isBSTByInOrderSequence(TreeNode<E> curr) {
        List<E> inOrderList = inOrderToList(curr);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i - 1).compareTo(inOrderList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 可以在中序遍历的过程中进行判断，时间复杂度为O(n)，空间复杂度为O(1)
     *
     * @param curr
     * @return
     */
    private boolean isBSTByInOrder(TreeNode<E> curr) {
        E lastValue = null;
        Stack<TreeNode<E>> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                // while表示不断地访问其左子树，直至达到左子树的最底端
                stack.push(curr);
                curr = curr.left;// 访问左子树
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                if (lastValue != null && curr.val.compareTo(lastValue) >= 0) {
                    return false;
                }
                lastValue = curr.val;
                curr = curr.right;// 访问右子树
            }
        }
        return true;
    }

    /**
     * 一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再是搜索二叉树，请找到这两个错误节点并返回他们的值。保证二叉树中结点的值各不相同。
     * 给定一棵树的根结点，请返回两个调换了位置的值
     * 1）中序遍历收集到数组中
     * 2）遍历数组，如果是第一次 前一个元素大于后一个元素，那么记录第一个错误节点为前一个（较大的），第二个错误节点为后一个（较小的）。如果是第二次，那么仅记录第二个错误节点为后一个。
     *
     * @return
     */
    public E[] findBSTErrorNodes() {
        List<E> inOrderList = inOrderToList(root);
        E first = null;
        E second = null;
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i - 1).compareTo(inOrderList.get(i)) > 0) {
                if (first == null) {
                    first = inOrderList.get(i - 1);
                }
                second = inOrderList.get(i);
            }
        }
        return (E[]) new Object[]{first, second};
    }

    private static class Distance {
        int max;
        int maxFromChild;

        public Distance(int max, int maxFromChild) {
            this.max = max;
            this.maxFromChild = maxFromChild;
        }
    }

    public int getLongestDistanceBetweenNodes() {
        return getLongestDistanceBetweenNodes(root).max;
    }

    /**
     * 空节点的max为0，maxFromChild为0
     * 叶子节点的max为1，maxFromChild为1
     * 每个节点的max = MAX(leftMax,rightMax,1+ maxFromLeft + maxFromRight)
     * 每个节点的maxFromChild = MAX(leftMax,rightMax) = 1
     *
     * @param curr
     * @return
     */
    private Distance getLongestDistanceBetweenNodes(TreeNode<E> curr) {
        if (curr != null) {
            Distance leftDistance = getLongestDistanceBetweenNodes(curr.left);
            Distance rightDistance = getLongestDistanceBetweenNodes(curr.right);
            return new Distance(
                    Math.max(
                            Math.max(leftDistance.max, rightDistance.max),
                            leftDistance.maxFromChild + rightDistance.maxFromChild + 1),
                    Math.max(leftDistance.maxFromChild, rightDistance.maxFromChild) + 1);
        } else {
            return new Distance(0, 0);
        }
    }

    static class MaxBSTInfo {
        TreeNode<Integer> maxSubBSTRoot;
        int size;
        int min;
        int max;

        public MaxBSTInfo() {
        }

        public MaxBSTInfo(TreeNode<Integer> maxSubBSTRoot, int size, int min, int max) {
            this.maxSubBSTRoot = maxSubBSTRoot;
            this.size = size;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "MaxBSTInfo{" +
                    "maxSubBSTRoot=" + (maxSubBSTRoot != null ? maxSubBSTRoot.val : "null") +
                    ", size=" + size +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    /**
     * 1）收集左子树的四个信息（最大搜索二叉子树的头结点，节点数，树上最小值，树上最大值），
     * 收集右子树的四个信息（最大搜索二叉子树的头结点，节点数，树上最小值，树上最大值）。
     * 2）更新根节点的最小值和最大值
     * 3）判断是否整体都是搜索二叉树，如果是，则返回根（先更新MaxSubBSTRoot和size）；如果不是，就返回左子树和右子树各自的最大搜索二叉树中，节点数较多的那个树的（MaxSubBSTRoot和size）。
     *
     * @param curr
     * @return
     */
    public MaxBSTInfo getMaxSubBSTInfo(TreeNode<Integer> curr) {
        if (curr == null) {
            return new MaxBSTInfo(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        MaxBSTInfo leftInfo = getMaxSubBSTInfo(curr.left);
        MaxBSTInfo rightInfo = getMaxSubBSTInfo(curr.right);
        MaxBSTInfo rootInfo = new MaxBSTInfo();
        rootInfo.max = Math.max(rightInfo.max, curr.val);
        rootInfo.min = Math.min(leftInfo.min, curr.val);

        if (leftInfo.maxSubBSTRoot == curr.left && rightInfo.maxSubBSTRoot == curr.right &&
                leftInfo.max < curr.val && curr.val < rightInfo.min) {
            rootInfo.maxSubBSTRoot = curr;
            rootInfo.size = leftInfo.size + rightInfo.size + 1;
            return rootInfo;
        } else {
            if (leftInfo.size >= rightInfo.size) {
                rootInfo.maxSubBSTRoot = leftInfo.maxSubBSTRoot;
                rootInfo.size = leftInfo.size;
                return rootInfo;
            } else {
                rootInfo.maxSubBSTRoot = rightInfo.maxSubBSTRoot;
                rootInfo.size = rightInfo.size;
                return rootInfo;
            }
        }
    }

    /**
     * 是否是对称的二叉树
     * 先序遍历
     * 注意对称不指的是左右孩子值相同，而是左孩子的左孩子值等于右孩子的右孩子值。左孩子的右孩子值等于右孩子的左孩子值
     *
     * @return
     */
    public boolean isSymmetrical() {
        return isSymmetrical(root, root);
    }

    private boolean isSymmetrical(TreeNode<E> currA, TreeNode<E> currB) {

        if (currA == null && currB == null) {
            return true;
        }
        if (currA == null || currB == null) {
            return false;
        }
        if (currA.val != currB.val) {
            return false;
        }
        return isSymmetrical(currA.left, currB.right) && isSymmetrical(currA.right, currB.left);
    }

    /**
     * 类似于层序遍历，但是队列的入队、出队规则不同
     *
     * @return
     */
    public List<List<E>> printZ() {
        TreeNode<E> curr;
        Deque<TreeNode<E>> queue = new ArrayDeque<>();
        List<List<E>> result = new ArrayList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<E> levelResult = new ArrayList<>();
            // 一层进行遍历,queue的长度即为该层节点个数
            for (int i = 0; i < levelSize; i++) {
                if (depth % 2 == 0) {
                    // 如果是偶数层，那么从队尾取出节点，先左后右，加入到队头
                    curr = queue.pollLast();
                    if (curr.left != null) {
                        queue.offerFirst(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offerFirst(curr.right);
                    }
                } else {
                    // 如果是奇数层，那么从队头取出节点，先右后左，加入到队尾
                    curr = queue.pollFirst();
                    if (curr.right != null) {
                        queue.offerLast(curr.right);
                    }
                    if (curr.left != null) {
                        queue.offerLast(curr.left);
                    }
                }
                levelResult.add(curr.val);
            }
            result.add(levelResult);
            depth++;
        }
        return result;
    }

    /**
     * 二叉搜索树转为有序双向链表
     * 非递归中序遍历
     *
     * @param curr
     * @return
     */
    public TreeNode<E> BST2DoubleLinkedList(TreeNode<E> curr) {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> pre = null;
        TreeNode<E> head = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                // 新增部分代码
                if (head == null) {
                    head = curr;
                }
                // 重建指针
                curr.left = pre;
                if (pre != null) {
                    // 重建指针
                    pre.right = curr;
                }
                pre = curr;
                // 结束
                curr = curr.right;
                ;// 访问右子树
            }
        }
        return head;
    }

    /**
     * 中序遍历 求从小到大的第k个node
     *
     * @param k
     * @return
     */
    public E kthNodeInBST(int k) {
        TreeNode<E> curr = root;
        Stack<TreeNode<E>> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                // while表示不断地访问其左子树，直至达到左子树的最底端
                stack.push(curr);
                curr = curr.left;// 访问左子树
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                k--;
                if (k == 0) {
                    return curr.val;
                }
                curr = curr.right;// 访问右子树
            }
        }
        return null;
    }

    public List<E> leftSideView() {
        List<E> result = new ArrayList<>();
        TreeNode<E> curr;
        Deque<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // 一层进行遍历,queue的长度即为该层节点个数
            for (int i = 0; i < levelSize; i++) {
                curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                if (i == 0) {
                    result.add(curr.val);
                }
            }
        }
        return result;
    }


    public TreeNode<E> LCA(TreeNode<E> root, TreeNode<E> p, TreeNode<E> q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? right : left;
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
    E val;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E val) {
        this.val = val;
    }

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
