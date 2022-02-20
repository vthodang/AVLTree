/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex1_avltree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *  Construct a BST Tree and convert to an AVL Tree
 * @author VNHS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a = {25,10,30,5,20,10,15,10};
        
        AVLTree t = new AVLTree();
        for (int x: a) {
            t.insert(x);
        }
        
//        t.preOrderTraverse();
//        t.inOrderTraverse();
//        t.greaterSearch(10);
//        System.out.println("Count equal " + t.countEqual(20));
        
                    //        t.AVLify();
//        t.inOrderTraverse();
//        System.out.println(t.isAVL());
//        System.out.println(t.getRoot().getHeight());

        try {
            PrintStream myconsole = new PrintStream(new FileOutputStream("output.txt", true));
            System.setOut(myconsole);
            //t.preOrderTraverse();
            t.inOrderTraverse();
            t.greaterSearch(10);
            System.out.println("Count equal " + t.countEqual(20));
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");;
        }
    }
    
    public static void SaveOutputToFile(String filename) {
        String str = System.console().readLine();
        System.out.println(str);
    }
    
}
