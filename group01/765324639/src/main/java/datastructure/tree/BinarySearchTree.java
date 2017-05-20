package datastructure.tree;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		return findMin(root).data;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
	    if (node.left == null) {
	        return node;
	    }
	    return findMin(node.left);
	}
	
	public T findMax(){
	    return findMax(root).data;
	}
	
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
	    if (node.right == null) {
	        return node;
	    }
	    return findMax(node.right);
	}
	
	public int height() {
	    return height(root);
	}
	
	private int height(BinaryTreeNode<T> node) {
	    if (node == null) {
	        return 0;
	    }
	    int leftHeight = height(node.left);
	    int rightHeight = height(node.right);
	    if (leftHeight > rightHeight) {
	        return leftHeight + 1;
	    } else {
	        return rightHeight + 1;
	    }
	}
	
	public int size() {
	    return size(root);
	}
	
	private int size(BinaryTreeNode<T> node) {
	    if (node == null) {
	        return 0;
	    }
	    return size(node.left) + size(node.right) + 1;
	}
	
	public void remove(T e){
	    root = remove(root, e);
	}
	
	private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T e) {
	    if (node == null) {
	        return null;
	    }
	    int cmp = e.compareTo(node.getData());
	    if (cmp < 0) {
	        node.left = remove(node.left, e);
	    } else if (cmp > 0) {
	        node.right = remove(node.right, e);
	    } else {
	        if (node.right == null) {
	            return node.left;
	        }
	        if (node.left == null) {
	            return node.right;
	        }
	        BinaryTreeNode<T> temp = node;
	        node = findMin(node.right);
	        node.right = deleteMin(temp.right);
	        node.left = temp.left;
	    }
	    return node;
	}
	
	private BinaryTreeNode<T> deleteMin(BinaryTreeNode<T> node) {
	    if (node.left == null) {
	        return node.right;
	    }
	    node.left = deleteMin(node.left);
	    return node;
	}
}

