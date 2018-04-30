/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author QuocTuyen
 */

public class TreeManager {
    
    private Node root;
    private Container contains;
    
    private TreeManager(){
        this.root = null;
    }
    
    private static TreeManager _instance;
    
    public static TreeManager Instance(){
        if(_instance == null)
            _instance = new TreeManager();
        return _instance;
    }
    
    public void addNode(int key){
    	Node cour = this.root;
    	Node preCour = this.root;
        int level = 0;
    	while(cour != null)
    	{
            level++;
            preCour = cour;
            if(cour.key < key) {
                cour = cour.right;
                if(cour == null) {
                    cour = new Node(key, preCour.location(), level, true);
                    this.contains.add(cour);
                    preCour.right = cour;
                    return;
                }
            }
            else if(cour.key > key) {
                cour = cour.left;
                if(cour == null) {
                    cour = new Node(key, preCour.location(), level, false);
                    this.contains.add(cour);
                    preCour.left = cour;
                    return;
            }
        }
        else return;
    	}
    	cour = new Node(key, null, 0, false);
        this.contains.add(cour);
    	root = cour;
    	
    }
    
    public ArrayList<Integer> getNLR(){
        ArrayList<Integer> result = new ArrayList<>();
        this.getNLR(result, this.root);
        return result;
    }
    
    public ArrayList<Integer> getLNR(){
    	ArrayList<Integer> result = new ArrayList<>();
        this.getLNR(result, this.root);
        return result;
    }
    
    public ArrayList<Integer> getLRN(){
    	ArrayList<Integer> result = new ArrayList<>();
        this.getLRN(result, this.root);
        return result;
    }
    
    private void getNLR(ArrayList<Integer> result, Node cour){
        if(cour == null) return;
        
        result.add(cour.key);
        this.getNLR(result,cour.left);
        this.getNLR(result, cour.right);
    }
    
    private void getLNR(ArrayList<Integer> result, Node cour) {
    	if(cour == null) return;
    	
    	this.getLNR(result, cour.left);
    	result.add(cour.key);
    	this.getLNR(result, cour.right);
    }
    
    private void getLRN(ArrayList<Integer> result, Node cour) {
    	if(cour == null) return;
    	
    	this.getLRN(result, cour.left);
    	this.getLRN(result, cour.right);	
    	result.add(cour.key);
    }

    void setContains(Container contains) {
        this.contains = contains;
    }
}
