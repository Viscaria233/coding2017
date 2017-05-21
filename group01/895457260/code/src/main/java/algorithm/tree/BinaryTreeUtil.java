package algorithm.tree;

import datastructure.basic.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            result.add(root.getData());
            result.addAll(preOrderVisit(root.getLeft()));
            result.addAll(preOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inOrderVisit(root.getLeft()));
            result.add(root.getData());
            result.addAll(inOrderVisit(root.getRight()));
        }
        return result;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> postOrderVisit(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root != null) {
            result.addAll(postOrderVisit(root.getLeft()));
            result.addAll(postOrderVisit(root.getRight()));
            result.add(root.getData());
        }
        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<T> node = stack.pop();
            result.add(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }

        return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static <T> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<T> node = stack.peek();
            if (node.getLeft() != null && !result.contains(node.getLeft().getData())) {
                stack.push(node.getLeft());
                continue;
            }
            stack.pop();
            result.add(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }

        return result;
    }

    public static <T> TreeInfo layerOrder(BinaryTreeNode<T> root, Visitor<T> visitor) {
        TreeInfo treeInfo = new TreeInfo();
        if (root == null) {
            return treeInfo;
        }

        Queue queue = new Queue();
        queue.enQueue(root);
        queue.enQueue(null);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = (BinaryTreeNode<T>) queue.deQueue();
            if (node == null) {
                treeInfo.layerCount++;
                if(!queue.isEmpty()) {
                    queue.enQueue(null);
                }
            } else {
                treeInfo.nodeCount++;
                if (visitor != null) {
                    visitor.visit(node);
                }
                if (node.left != null) {
                    queue.enQueue(node.left);
                }
                if (node.right != null) {
                    queue.enQueue(node.right);
                }
            }
        }
        return treeInfo;
    }

    public interface Visitor<T> {
        void visit(BinaryTreeNode<T> node);
    }

    public static class TreeInfo {
        int layerCount;
        int nodeCount;

        public int getLayerCount() {
            return layerCount;
        }

        public int getNodeCount() {
            return nodeCount;
        }
    }
}
