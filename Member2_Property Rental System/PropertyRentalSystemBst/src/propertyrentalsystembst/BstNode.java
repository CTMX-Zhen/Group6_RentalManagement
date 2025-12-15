/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

/**
 *
 * @author User
 */
public class BstNode<K extends Comparable<K>, V> {
    K key;
    V value;
    BstNode<K, V> left;
    BstNode<K, V> right;

    BstNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
