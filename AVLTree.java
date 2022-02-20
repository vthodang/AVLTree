/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1_avltree;

import static java.lang.Math.abs;

/**
 * A BST tree that can be converted to AVL Tree
 * @author VNHS
 */
public class AVLTree {
    private TreeNode root;
    
    public void insert(int data) {
        if (this.root == null)
            this.root = new TreeNode(data);
        else 
            root.insert(new TreeNode(data)); // start insert from the root
            //root = root.rebalance(); // SPECIAL: AVL construc at once
    }
    
    public boolean isAVL() {
        if (this.root == null)
            return true;
        return root.isAVL();
    }
    
    // make the tree to AVL
    public void AVLify() {
        if (this.root == null)
            return;
        while (!isAVL()) 
            this.root = this.root.balancify();
    }
    
    public TreeNode getRoot() {
        return this.root;
    }
    
    // pre-order traverse
    public void preOrderTraverse() {
        System.out.println("Pre-order Traverse:");
        if (root == null)
            return;
        root.preOrderTraverse();
        System.out.println();
    }
    // in-order traverse
    public void inOrderTraverse() {
        System.out.println("In-order Traverse:");
        if (root == null)
            return;
        root.inOrderTraverse();
        System.out.println();
    }
    
    // greater Search
    public void greaterSearch(int x) {
        System.out.println("Elements bigger than " + x + ":");
        if (root == null)
            return;
        root.greaterSearch(x);  
        System.out.println();
    }
    
    // count equal
    public int countEqual(int x) {
        if (root == null) return 0;
        return root.countEqual(x);
    }
}

class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;
    private int height = 0; // initially zero
    
    // constructor
    public TreeNode(int data) {
        this.data = data;
    }
    
    // search count equal
    public int countEqual(int x) {
        if (x < this.data) {
            if (this.left != null) return this.left.countEqual(x);
            else return 0;
        }
        if (x > this.data) {
            if (this.right != null) return this.right.countEqual(x);
            else return 0;
        }
        if (this.right != null) return 1 + this.right.countEqual(x);
        else return 1;
    }
    //greater search
    public void greaterSearch(int x) {
        if (x < this.data) {
            if (this.left != null) this.left.greaterSearch(x);
            System.out.print(this.data + " ");
        }
        if (this.right != null) this.right.greaterSearch(x);
    }

    // preOrderTraverse
    public void preOrderTraverse() {
        System.out.print(this.data + " ");
        if (this.left != null) this.left.preOrderTraverse();
        if (this.right != null) this.right.preOrderTraverse();
    }
    
        // inOrderTraverse
    public void inOrderTraverse() {
        if (this.left != null) this.left.inOrderTraverse();
        System.out.print(this.data + " ");
        if (this.right != null) this.right.inOrderTraverse();
    }

    // check AVL
    public boolean isAVL() {
        // leaft or there is height 1 then always AVL
        if (this.height < 2)
            return true;
        // heigh above 1 with only 1 side - not AVL
        if (this.left == null || this.right == null)
            return false;
        // left and right both exist and balance factor ok then fall next
        if (abs(this.left.getHeight() - this.right.getHeight()) <= 1)
            return this.left.isAVL() && this.right.isAVL();
        return false;
    }
    
    // an balancify from top to the root of the tree
    public TreeNode balancify() {
        if (this.left != null && this.left.getHeight() > 1) this.left = this.left.balancify();
        if (this.right != null && this.right.getHeight() >1) this.right = this.right.balancify();
        this.setHeight();
        return this.rebalance();
    }
    
    // private TreeNode rebalance()
    public TreeNode rebalance() {
        if (this.height < 2)
            return this;
        if (this.left == null)
            return rebalanceLeft();
        if (this.right == null)
            return rebalanceRight();
        if (this.left.getHeight() > this.right.getHeight() + 1)
            return rebalanceRight();
        if (this.left.getHeight() + 1 < this.right.getHeight())
            return rebalanceLeft();
        return this;
    }
    
    private TreeNode rebalanceLeft() {
        TreeNode right = this.right;
        if ((right.left != null) && (right.right == null || right.left.getHeight() > right.right.getHeight())) {
            this.right = left.rotateRight();
        }
        return this.rotateLeft();
    }
    
    private TreeNode rebalanceRight() {
        TreeNode left = this.left;
        if ((left.right != null) && (left.left == null || left.right.getHeight() > left.left.getHeight())) {
            this.left = left.rotateLeft();
        }
        return this.rotateRight();
    }
    
    private TreeNode rotateLeft() {
        TreeNode b = this.right;
        b.setParent(this.parent);
        this.setRight(b.left);
        if (b.left != null) b.left.setParent(this);
        b.setLeft(this);
        this.setParent(b);
        // reset the height
        this.setHeight();
        b.setHeight();
        return b;
    }
    
    private TreeNode rotateRight() {
        TreeNode b = this.left;
        b.setParent(this.parent);
        this.setLeft(b.right);
        if (b.right != null) b.right.setParent(this);
        b.setRight(this);
        this.setParent(b);
        // reset height
        this.setHeight();
        b.setHeight();
        return b;
    }
    // insert
    public void insert(TreeNode node) {
        if (this.data <= node.getData()) { // if new node data >= this to the right
            if (this.right == null) {  // if null then insert child and connect parent
                this.right = node;
                node.parent = this; // set parent
            }
            else {
                this.right.insert(node);
                //this.right = this.right.rebalance();  // SPECIAL: AVL construc at once
            }
        } else {
            if (this.left == null) {
                this.left = node;
                node.parent = this;
            }
            else {
                this.left.insert(node);
                //this.left = this.left.rebalance();   // SPECIAL: AVL construc at once
            }
        }
        // update height after insert
        this.setHeight();
    }
    
    //getters
    public int getData() {
        return this.data;
    }
    public int getHeight() {
        return this.height;
    }
    
    // set height of a node = 1 + max(left height, right height)
    public void setHeight() {
        if (this.left == null && this.right == null)
            this.height = 0;
        else if (this.left == null)        
            this.height = 1 + this.right.getHeight();
        else if (this.right == null)
            this.height = 1 + this.left.getHeight();
        else if (this.right.getHeight() > this.left.getHeight())
            this.height = 1 + this.right.getHeight();
        else
            this.height = 1 + this.left.getHeight();
    }
    
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }

}