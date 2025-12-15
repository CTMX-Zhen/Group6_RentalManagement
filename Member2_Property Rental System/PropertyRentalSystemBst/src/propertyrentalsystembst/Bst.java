/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Bst<K extends Comparable<K>, V> {

    static class BSTNode<K extends Comparable<K>, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode<K, V> root;

    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    private BSTNode<K, V> insertRec(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            return new BSTNode<K, V>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertRec(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V search(K key) {
        BSTNode<K, V> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.value;
            }
            x = (cmp < 0) ? x.left : x.right;
        }
        return null;
    }

    public boolean delete(K key) {
        int before = size();
        root = deleteRec(root, key);
        int after = size();
        return after < before;
    }

    private BSTNode<K, V> deleteRec(BSTNode<K, V> node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteRec(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, key);
        } else {
            // Found the node to delete
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Two children: replace with inorder successor (smallest in right subtree)
            BSTNode<K, V> succ = minNode(node.right);
            node.key = succ.key;
            node.value = succ.value;
            node.right = deleteRec(node.right, succ.key);
        }
        return node;
    }

    private BSTNode<K, V> minNode(BSTNode<K, V> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public List<V> inorderValues() {
        List<V> res = new ArrayList<V>();
        inorderRec(root, res);
        return res;
    }

    private void inorderRec(BSTNode<K, V> node, List<V> out) {
        if (node == null) return;
        inorderRec(node.left, out);
        out.add(node.value);
        inorderRec(node.right, out);
    }

    public int size() {
        return countRec(root);
    }

    private int countRec(BSTNode<K, V> node) {
        if (node == null) return 0;
        return 1 + countRec(node.left) + countRec(node.right);
    }

    BSTNode<K, V> getRoot() {
        return root;
    }
}
