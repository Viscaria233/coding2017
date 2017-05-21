package algorithm.tree;

import com.sun.istack.internal.NotNull;

public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        BinaryTreeNode<T> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    public T findMax() {
        BinaryTreeNode<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        return BinaryTreeUtil.layerOrder(root,null).getLayerCount();
    }

    public int size() {
        if (root == null) {
            return 0;
        }
        return BinaryTreeUtil.layerOrder(root,null).getNodeCount();
    }

    public void remove(T e) {
        BinaryTreeNode<T> node = findNode(e, false);
        if (node != null) {
            remove(node);
        }
    }

    private void remove(BinaryTreeNode<T> node) {
        if (node.left == null && node.right == null) {
            BinaryTreeNode<T> parent = findNode(node.data, true);
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else {
            BinaryTreeNode<T> replacement = getReplacement(node);
            if (replacement != null) {
                node.data = replacement.data;
                remove(replacement);
            }
        }
    }

    private BinaryTreeNode<T> getReplacement(BinaryTreeNode<T> node) {
        int lHeight = BinaryTreeUtil.layerOrder(node.left,null).getLayerCount();
        int rHeight = BinaryTreeUtil.layerOrder(node.right,null).getLayerCount();
        if (lHeight <= 0 && rHeight <= 0) {
            return null;
        }

        boolean left = true;
        if (rHeight > lHeight) {
            left = false;
        }

        if (left) {
            BinaryTreeNode<T> closedLess = node.left;
            while (closedLess.right != null) {
                closedLess = closedLess.right;
            }
            return closedLess;
        } else {
            BinaryTreeNode<T> closedGreater = node.right;
            while (closedGreater.left != null) {
                closedGreater = closedGreater.left;
            }
            return closedGreater;
        }
    }

    private BinaryTreeNode<T> findNode(T e, boolean findParent) {
        BinaryTreeNode<T> child = root;
        BinaryTreeNode<T> parent = null;
        while (child != null) {
            int compare = child.data.compareTo(e);
            if (compare > 0) {
                child = child.left;
                parent = parent == null ? root : parent.left;
            } else if (compare < 0) {
                child = child.right;
                parent = parent == null ? root : parent.right;
            } else {
                return findParent ? parent : child;
            }
        }
        return null;
    }
}

