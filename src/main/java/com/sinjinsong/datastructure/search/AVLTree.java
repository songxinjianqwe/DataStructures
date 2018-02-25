package com.sinjinsong.datastructure.search;

public class AVLTree<E extends Comparable<E>> {
    private AVLTreeNode<E> root;

    public AVLTreeNode<E> search(E data) {
        return search(root, data);
    }

    // 查找一个结点，使用递归
    // 如果data即为当前结点的值，则返回当前结点
    // 如果data小于当前结点，那么递归查找当前结点的左子树
    // 否则，递归查找当前结点的右子树
    private AVLTreeNode<E> search(AVLTreeNode<E> node, E data) {
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

    public int height(AVLTreeNode<E> node) {
        return node == null ? 0 : node.height;
    }

    //   o
    //  o
    // o 
    // LL型：当插入后最小不平衡子树（左右子树盖度相差2）沿着新增节点向下经过两条边形成（左下向右上的）一条线时，需要进行单向右旋
    // 用返回的左子树（新的根节点）代替原来的根结点
    // 方法名表示用做左子树代替根节点
    private AVLTreeNode<E> rotateWithLeftChild(AVLTreeNode<E> parent) {
        AVLTreeNode<E> leftChild = parent.leftChild;
        parent.leftChild = leftChild.rightChild;
        leftChild.rightChild = parent;
        // 调整完后重新求当前结点和左孩子的高度
        parent.height = Math.max(height(parent.leftChild), height(parent.rightChild)) + 1;
        leftChild.height = Math.max(height(leftChild.leftChild), height(leftChild.rightChild)) + 1;
        return leftChild;
    }
    
    // o
    //  o
    //   o
    // RR型：当插入后最小不平衡子树（左右子树盖度相差2）沿着新增节点向下经过两条边形成上图时，需要进行单向左旋
    // 用返回的Rchild（新的根节点）代替原来的根结点
    private AVLTreeNode<E> rotateWithRightChild(AVLTreeNode<E> parent) {
        AVLTreeNode<E> rightChild = parent.rightChild;
        parent.rightChild = rightChild.leftChild;
        rightChild.leftChild = parent;
        // 调整完后重新求当前结点和左孩子的高度
        parent.height = Math.max(height(parent.leftChild), height(parent.rightChild)) + 1;
        rightChild.height = Math.max(height(rightChild.leftChild), height(rightChild.rightChild)) + 1;
        return rightChild;
    }

    //  o
    // o
    //  o
    // LR型：当插入后最小不平衡子树（左右子树盖度相差2）沿着新增节点向下经过两条边形成上图时，需要进行先左旋后右旋
    private AVLTreeNode<E> doubleRotateWithLeftChild(AVLTreeNode<E> parent) {
        parent.leftChild = rotateWithRightChild(parent.leftChild);
        return rotateWithLeftChild(parent);
    }
    
    // o
    //  o
    // o
    // RL型：当插入后最小不平衡子树（左右子树盖度相差2）沿着新增节点向下经过两条边形成上图时，需要进行先右旋后左旋
    private AVLTreeNode<E> doubleRotateWithRightChild(AVLTreeNode<E> parent) {
        parent.rightChild = rotateWithLeftChild(parent.rightChild);
        return rotateWithRightChild(parent);
    }
    
    // 将以上各种情况统一起来
    private AVLTreeNode<E> rotate(AVLTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        if (height(node.leftChild) - height(node.rightChild) == 2) {
            // 如果不平衡，再检测属于在左孩子上插在了左子树or右子树上
            if (height(node.leftChild.leftChild) >= height(node.leftChild.rightChild)) {
                // 如果插在了左子树上，那么属于LL型
                node = rotateWithLeftChild(node);
            } else {
                // 如果插在了右子树上，那么属于LR型
                node = doubleRotateWithLeftChild(node);
            }
        } else if (height(node.rightChild) - height(node.leftChild) == 2) {
            // 如果不平衡，再检测属于在左孩子上插在了左子树or右子树上
            if (height(node.rightChild.leftChild) >= height(node.rightChild.rightChild)) {
                // 如果插在了左子树上，那么属于RL型
                node = doubleRotateWithLeftChild(node);
            } else {
                // 如果插在了右子树上，那么属于RR型
                node = rotateWithRightChild(node);
            }
        }
        return node;
    }
    
    // 插入一个结点
    public void insert(E data) {
        root = insert(root, data);
        // 注意结果要赋值，否则在调完第二个insert方法后node就回收了，没有把结果赋给root
        // 调用的时候将一个null赋给了node，即使node后来不为null了也没法把引用重新赋给root
    }

    // 也是需要递归进行查找的，递归地找到合适位置后插入，不允许有重复元素
    private AVLTreeNode<E> insert(AVLTreeNode<E> node, E data) {
        if (node == null) {
            node = new AVLTreeNode<>(data);
        } else {
            if (data.compareTo(node.data) < 0) {
                node.leftChild = insert(node.leftChild, data);
                if (height(node.leftChild) - height(node.rightChild) == 2) {
                    if (data.compareTo(node.leftChild.data) < 0) {
                        // 如果插在了左子树上，那么属于LL型
                        node = rotateWithLeftChild(node);
                    } else {
                        // 如果插在了右子树上，那么属于LR型
                        node = doubleRotateWithLeftChild(node);
                    }
                }
            } else if (data.compareTo(node.data) > 0) {
                node.rightChild = insert(node.rightChild, data);
                if (height(node.rightChild) - height(node.leftChild) == 2) {
                    if (data.compareTo(node.rightChild.data) < 0) {
                        // 如果插在了左子树上，那么属于RL型
                        node = doubleRotateWithRightChild(node);
                    } else {
                        // 如果插在了右子树上，那么属于RR型
                        node = rotateWithRightChild(node);
                    }
                }
            }
        }
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
        return node;
    }

    // 返回当前子树中的值最小的那个结点
    private AVLTreeNode<E> findMin(AVLTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        if (node.leftChild != null) {
            return findMin(node.leftChild);
        } else {
            return node;
        }
    }

    // 删除一个结点
    public void delete(E data) {
        root = delete(root, data);
    }

    //无论插入还是删除，操作结束后都需要更新height值
    //删除后为了保持ABL树的特性，如果不平衡的话，需要对当前结点的左子树、右子树和自己进行旋转
    private AVLTreeNode<E> delete(AVLTreeNode<E> node, E data) {
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
            node.data = findMin(node.rightChild).data;
            node.rightChild = delete(node.rightChild, node.data);
            // 进行一次删除，删除的是以node的右子树为根节点，值为min的这个结点
            // 包含了结点只有左子树或右子树或都没有的情况
        } else {
            node = node.leftChild == null ? node.rightChild : node.leftChild;
        }
        if (node != null) {
            node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
            if (node.leftChild != null) {
                node.leftChild = rotate(node.leftChild);
            }
            if (node.rightChild != null) {
                node.rightChild = rotate(node.rightChild);
            }
            node = rotate(node);
        }
        return node;
    }

    public AVLTreeNode<E> root() {
        return root;
    }

    public void preOrder(AVLTreeNode<E> subTree) {
        if (subTree != null) {
            System.out.print(subTree.data + " ");
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    public void inOrder(AVLTreeNode<E> subTree) {
        if (subTree != null) {
            inOrder(subTree.leftChild);
            System.out.print(subTree.data + " ");
            inOrder(subTree.rightChild);
        }
    }

    public void postOrder(AVLTreeNode<E> subTree) {
        if (subTree != null) {
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            System.out.print(subTree.data + " ");
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
//		Random r = new Random();
//        int integer = 0;
//        for (int i = 1; i < 10; i++) {
////			integer = r.nextInt(20);
////			System.out.print(integer+" ");
//            tree.insert(i);
//        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
//        tree.inOrder(tree.root());
        tree.insert(1);
        tree.insert(26);
        tree.insert(3);
        tree.insert(23);
        tree.insert(4);
//        tree.insert(24);
//        tree.insert(25);
        
        tree.inOrder(tree.root);
        System.out.println();
        tree.preOrder(tree.root);
        
		/*System.out.println();
		tree.preOrder(tree.root());
		System.out.println();
		tree.inOrder(tree.root());
		
		tree.delete(9);
		System.out.println();
		tree.preOrder(tree.root());
		System.out.println();
		tree.inOrder(tree.root());
		System.out.println();
		System.out.println(tree.search(2));*/
    }
}

class AVLTreeNode<E extends Comparable<E>> {
    E data;
    AVLTreeNode<E> leftChild;
    AVLTreeNode<E> rightChild;
    int height;

    public AVLTreeNode(E data) {
        this.data = data;
    }

    public AVLTreeNode(E data, AVLTreeNode<E> leftChild, AVLTreeNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
