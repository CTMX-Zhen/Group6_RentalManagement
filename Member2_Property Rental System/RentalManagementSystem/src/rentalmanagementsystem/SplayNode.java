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


public class SplayNode {
    public Rental rental;
    public SplayNode left, right;

    public SplayNode(Rental rental) {
        this.rental = rental;
        this.left = null;
        this.right = null;
    }
}

