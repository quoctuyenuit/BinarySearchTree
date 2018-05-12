/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author QuocTuyen
 */

public class TreeManager {
    
    public static boolean isDeleteNode = false; //Biến cờ hiệu cho phép xoá node hay không.
    
    private Node root;
    private Container container;
    private Point rootLocation;
    
    private TreeManager(){
        this.root = null;
        this.rootLocation = new Point(800, 100);
    }
    
    private static TreeManager _instance;
    
    public static TreeManager Instance(){
        if(_instance == null)
            _instance = new TreeManager();
        return _instance;
    }
    
    public void setRootLocation(Point location){
        this.rootLocation = location;
    }
    
    void setContainer(Container container) {
        this.container = container;
    }
    
    public void buildTreeFromArray(int[] array){
        for (int i : array) {
            this.addNode(i);
        }
        this.updateLocation(false);
    }
    
    public void buildTreeFromList(ArrayList<Integer> list){
        for (int i : list) {
            this.addNode(i);
        }
        this.updateLocation(false);
    }
    
    //==========================================================================
    public boolean isEmpty(){
        return (this.root == null);
    }
    
    public void addNode(int key){
    	Node cour = this.root;

        while(cour != null)
    	{
            if(cour.key < key) {
                
                if(cour.getRight() == null) {
                    Node newNode = new Node(key, true);
                    cour.setRight(newNode);
                    
                    this.container.add(newNode);
                    return;
                }
                
                cour = cour.getRight();
            }
            else if(cour.key > key) {
                
                 if(cour.getLeft() == null) {
                    Node newNode = new Node(key, false);
                    cour.setLeft(newNode);
                    
                    this.container.add(newNode);
                    return;
                }
                 
                 cour = cour.getLeft();
            }
            else return;
    	}
    	
        root = new Node(key, true);
        root.setLocation(rootLocation);
        root.setLevel(1);
        this.container.add(root);
    }
    
    public void destroy(){
        this.destroy(this.root);
        this.root = null;
    }
    
    private void destroy(Node node){
        if(node == null)
            return;
        this.destroy(node.getLeft());
        this.destroy(node.getRight());
        this.container.remove(node);
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
        this.getNLR(result,cour.getLeft());
        this.getNLR(result, cour.getRight());
    }
    
    private void getLNR(ArrayList<Integer> result, Node cour) {
    	if(cour == null) return;
    	
    	this.getLNR(result, cour.getLeft());
    	result.add(cour.key);
    	this.getLNR(result, cour.getRight());
    }
    
    private void getLRN(ArrayList<Integer> result, Node cour) {
    	if(cour == null) return;
    	
    	this.getLRN(result, cour.getLeft());
    	this.getLRN(result, cour.getRight());	
    	result.add(cour.key);
    }
    
    //==========================================================================
    public boolean find(int key){
        return find(key, this.root);
    }
    
    private boolean find(int key, Node node){
        if(node == null)
            return false;
        
        node.through();
        
        if(node.key == key) {
            node.found();
            return true;
        }
        
        if(key > node.key) return find(key, node.getRight());
        
        return find(key, node.getLeft());
    }
     //==========================================================================
    public void balanceTheTree(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                balanceTheTree(root, null, true);
                updateLocation(true);
                repaint();
            }
        });
        
        thread.start();
    }
    
    //Truyền thêm hai biến parentNode và isRight để nắm bắt và thay đổi cấu trúc cây sau hàm
    private void balanceTheTree(Node node, Node parentNode, boolean isRight){
        if(node == null) return;
        
        this.balanceTheTree(node.getLeft(), node, false);
        this.balanceTheTree(node.getRight(), node, true);
        
        //===========================================
        //Tính lại chỉ số cân bằng
        int leftHeight = (node.getLeft() != null)? treeHeight(node.getLeft()): 0;
        int rightHeight = (node.getRight() != null)? treeHeight(node.getRight()) :0;
        
        node.setBalanceFactor(rightHeight - leftHeight);
        
        //===========================================

        //Lệch phải
        if(node.getBalanceFactor() > 1){
            if(node.getRight().getBalanceFactor() <= -1)
                this.RL(node, parentNode, isRight);
            else
                this.RR(node, parentNode, isRight);
        }
        else
        //Lệch trái
        if(node.getBalanceFactor() < -1){
            if(node.getLeft().getBalanceFactor() >= 1)
                this.LR(node, parentNode, isRight);
            else
                this.LL(node, parentNode, isRight);
        }
    }
    
    private void LL(Node T, Node parentNode, boolean isRight){
        Node T1 = T.getLeft();
        Node R1 = T1.getRight();
        
        if(parentNode != null)
            this.replaceChild(parentNode, T1, isRight);
         else
        {
            this.root = T1;
            this.root.setLocation(this.rootLocation);
        }
        T1.setRight(T);
        T.setLeft(R1);
        
        switch(T1.getBalanceFactor()){
            case -1: {
                T.setBalanceFactor(0);
                T1.setBalanceFactor(0);
                break;
            }
            case 0: {
                T.setBalanceFactor(-1);
                T1.setBalanceFactor(1);
                break;
            }
        }
    }
    
    private void LR(Node T, Node parentNode, boolean isRight){
        Node T1 = T.getLeft();
        Node T2 = T1.getRight();
        Node L21 = T2.getLeft();
        Node R21 = T2.getRight();
        
        if(parentNode != null)
            this.replaceChild(parentNode, T2, isRight);
        else
        {
            this.root = T2;
            this.root.setLocation(this.rootLocation);
        }
            
        T2.setLeft(T1);
        T2.setRight(T);
        
        T1.setRight(L21);
        T.setLeft(R21);
        
        
        switch(T2.getBalanceFactor()){
            case -1:{
                T.setBalanceFactor(1);
                T1.setBalanceFactor(0);
                break;
            }
            case 0:{
                T.setBalanceFactor(0);
                T1.setBalanceFactor(0);
                break;
            }
            case 1:{
                T.setBalanceFactor(0);
                T1.setBalanceFactor(-1);
                break;
            }
        }
        
        T2.setBalanceFactor(0);
    }
    
    private void RR(Node T, Node parentNode, boolean isRight){
        Node T1 = T.getRight();
        Node L1 = T1.getLeft();
        
        if(parentNode != null)
            this.replaceChild(parentNode, T1, isRight);
        else
        {
            this.root = T1;
            this.root.setLocation(this.rootLocation);
        }
        
        T1.setLeft(T);
        T.setRight(L1);
        
        switch(T1.getBalanceFactor()){
            case 1:{
                T.setBalanceFactor(0);
                T1.setBalanceFactor(0);
                break;
            }
            case 0:{
                T.setBalanceFactor(1);
                T1.setBalanceFactor(-1);
                break;
            }
        }
    }
    
    private void RL(Node T, Node parentNode, boolean isRight){
        
        Node T1 = T.getRight();
        Node T2 = T1.getLeft();
        Node L21 = T2.getLeft();
        Node R21 = T2.getRight();
        
        if(parentNode != null)
            this.replaceChild(parentNode, T2, isRight);
        else
        {
            this.root = T2;
            this.root.setLocation(this.rootLocation);
        }
        
        T2.setLeft(T);
        T2.setRight(T1);
        
        T.setRight(L21);
        T1.setLeft(R21);
        
        switch(T2.getBalanceFactor()){
            case 1:{
                T.setBalanceFactor(-1);
                T1.setBalanceFactor(0);
                break;
            }
            case 0:{
                T.setBalanceFactor(0);
                T1.setBalanceFactor(0);
                break;
            }
            case -1:{
                T.setBalanceFactor(0);
                T1.setBalanceFactor(1);
            }
        }
        
        T2.setBalanceFactor(0);
    }
    
    //Tính chiều cao tạo một node
    private int treeHeight(Node node){
        if(node == null) return 0;
        
        
        int leftHeight = treeHeight(node.getLeft());
        int rightHeight = treeHeight(node.getRight());
        
        return 1 + ((leftHeight > rightHeight)? leftHeight : rightHeight);
    }
    
    //==========================================================================
    public void refresh(){
        this.refresh(this.root);
        this.repaint();
    }
    
    private void refresh(Node node){
        if(node == null) return;
        node.refresh();
        this.refresh(node.getLeft());
        this.refresh(node.getRight());
    }
    
    public void paintLink(Graphics g){
        this.paintLink(g, root);
    }
    
    private void paintLink(Graphics g, Node node){
        if(node == null) return;
        int widthNode = node.getSize().width;
        int heightNode = node.getSize().height;
        Point startPoint = new Point(node.getLocation().x + widthNode/2, node.getLocation().y + heightNode - 5);
        
        Node leftNode = node.getLeft();
        Node rightNode = node.getRight();
        
        if(leftNode != null)
            g.drawLine(startPoint.x, startPoint.y, leftNode.getLocation().x + widthNode/2, leftNode.getLocation().y);
        
        if(rightNode != null)
            g.drawLine(startPoint.x, startPoint.y, rightNode.getLocation().x + widthNode/2, rightNode.getLocation().y);
        
        this.paintLink(g, node.getLeft());
        this.paintLink(g, node.getRight());
    }
    //==========================================================================
    
    public void updateLocation(boolean animation){
        this.root.setLevel(1);
        this.updateLocation(root, animation);
    }
    
    private void updateLocation(Node node, boolean animation){
        if(node == null) return;
        
        node.updateChildrenLocation(animation);
        this.updateLocation(node.getLeft(), animation);
        this.updateLocation(node.getRight(), animation);
    }
    //==========================================================================
    
    public void deleteNode(int key){
        this.deleteNode(key, root, null, true);
    }
    
    private boolean deleteNode(int key, Node node, Node parentNode, boolean isRight){
        if(node == null) return false;

        if(node.key < key)
            return deleteNode(key, node.getRight(), node, true);
        else if(node.key > key)
            return deleteNode(key, node.getLeft(), node, false);
        
        this.deleteNode(node, parentNode, isRight);
        return true;
    }
    
    private void replaceChild(Node parentNode, Node childNode, boolean isRight){
        if(isRight)
            parentNode.setRight(childNode);
        else
            parentNode.setLeft(childNode);
    }
    
    private void deleteNode(Node node, Node parentNode, boolean isRight){
        if(node.getLeft() == null && node.getRight() == null){
            if(parentNode != null)
                this.replaceChild(parentNode, null, isRight);
        }
        
        if(node.getLeft() == null && node.getRight() != null){
            if(parentNode != null)
                this.replaceChild(parentNode, node.getRight(), isRight);
            else
            {
                this.root = node.getRight();
                this.root.setLocation(this.rootLocation);
            }
        }
        
        if(node.getLeft() != null && node.getRight() == null){
            if(parentNode != null)
            this.replaceChild(parentNode, node.getLeft(), isRight);
            else
            {
                this.root = node.getLeft();
                this.root.setLocation(this.rootLocation);
            }
        }
        
        if(node.getLeft() != null && node.getRight() != null)
            node = searchStandFor(node.getLeft(), node, node, false);
        
        this.container.remove(node);
    }
    
    //Biến parentP dùng để set lại vị trí left
    private Node searchStandFor(Node p, Node q, Node parentP, boolean isRight){
        if(p.getRight() != null)
            return this.searchStandFor(p.getRight(), q, p, true);
        
        q.key = p.key;
        this.replaceChild(parentP, p.getLeft(), isRight);
        
        return p;
    }
    
    public void repaint(){
        this.container.repaint();
    }
}
