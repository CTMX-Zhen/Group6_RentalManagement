/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmanagementsystem;

import rentalmanagementsystem.Rental;

/**
 *
 * @author User
 */
public class SplayTree {

    private SplayNode root;

    private SplayNode rightRotate(SplayNode x) {
        SplayNode y = x.left;
        if (y == null) {
            return x;
        }
        x.left = y.right;
        y.right = x;
        return y;
    }

    private SplayNode leftRotate(SplayNode x) {
        SplayNode y = x.right;
        if (y == null) {
            return x;
        }
        x.right = y.left;
        y.left = x;
        return y;
    }

    private SplayNode splay(SplayNode root, long key) {
        if (root == null || root.rental.getId() == key) {
            return root;
        }

        if (key < root.rental.getId()) {
            if (root.left == null) {
                return root;
            }

            if (key < root.left.rental.getId()) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } else if (key > root.left.rental.getId()) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = leftRotate(root.left);
                }
            }
            return (root.left == null) ? root : rightRotate(root);
        } else {
            if (root.right == null) {
                return root;
            }

            if (key > root.right.rental.getId()) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            } else if (key < root.right.rental.getId()) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rightRotate(root.right);
                }
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    public void insert(Rental rental) {
        if (root == null) {
            root = new SplayNode(rental);
            return;
        }

        root = splay(root, rental.getId());

        if (root.rental.getId() == rental.getId()) {
            return;
        }

        SplayNode newNode = new SplayNode(rental);

        if (rental.getId() < root.rental.getId()) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
    }

    public Rental search(long key) {
        root = splay(root, key);
        return (root != null && root.rental.getId() == key) ? root.rental : null;
    }

    public void delete(long key) {
        if (root == null) {
            return;
        }

        root = splay(root, key);

        if (root.rental.getId() != key) {
            System.out.println("Property not found!");
            return;
        }

        if (root.left == null) {
            root = root.right;
        } else {
            SplayNode temp = root.right;
            root = root.left;
            root = splay(root, key);
            root.right = temp;
        }

        System.out.println("Property deleted successfully!");
    }

    public boolean update(long key, Rental updatedRental) {
        root = splay(root, key);
        if (root != null && root.rental.getId() == key) {
            root.rental = updatedRental;
            System.out.println("Property updated successfully!");
            return true;
        }
        System.out.println("Property not found!");
        return false;
    }

    public void display() {
        System.out.println("Current Properties:");
        inorder(root);
    }

    private void inorder(SplayNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.rental);
        inorder(node.right);
    }

    public SplayNode getRoot() {
        return root;
    }
}
