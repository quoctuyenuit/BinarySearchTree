/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author QuocTuyen
 */
public class Node extends JComponent implements MouseListener{
    int key;
    private Node left;
    private Node right;
    private int balFactor; // Chỉ số cân bằng
    private int level;
    private final int width = 60;
    private final int height = 60;
    private Color nodeColor;
    private final Color defaultColor = new Color(200,255,225);
    
    private Thread thread;
    /*
    valueKey: Value of node
    linkPoint: Điểm liên kết giữa các node
    level: Level of node
    isRight: Kiểm tra node thuộc bên phải ko
    */
    Node(int valueKey, boolean isRight){
        super();
        enableInputMethods(true);
        addMouseListener(this);
        
        this.key = valueKey;
        this.left = this.right = null;
        this.setSize(new Dimension(width, height));
        this.nodeColor = defaultColor;       
    }
    
    public Node getLeft(){
        return this.left;
    }
    
    public Node getRight(){
        return this.right;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public int getBalanceFactor(){
        return this.balFactor;
    }
    
    public void setBalanceFactor(int balFactor){
        this.balFactor = balFactor;
    }
    
    public void setRight(Node node){
        this.right = node;
    }
    
    public void setLeft(Node node){
        this.left = node;
    }
    
    public void updateChildrenLocation(boolean animation){
        Node leftNode = this.left;
        Node rightNode = this.right;
        
        int y = this.getLocation().y + 120;
        
        if(leftNode != null){
            int x = this.getLocation().x - 300/level;
//            if(animation){
//                NodeAnimation.Instance().setStart(leftNode);
//                NodeAnimation.Instance().start(new Point(x, y));
//            }
            leftNode.setLocation(x, y);
            leftNode.setLevel(level + 1);
        }
        
        if(rightNode != null){
            int x = this.getLocation().x + 300/level;
//            if(animation){
//                NodeAnimation.Instance().setStart(leftNode);
//                NodeAnimation.Instance().start(new Point(x, y));
//            }
            rightNode.setLocation(x, y);
            rightNode.setLevel(level + 1);
        }
    }
    
    
    private boolean isStopAnimation(Point desLocation){
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        
        return (x >= (desLocation.x - 5) && x <= (desLocation.x + 5) && y >= (desLocation.y - 5) && y <= (desLocation.y + 5));
    }
    
    public void found(){
        this.nodeColor = Color.RED;
        this.repaint();
    }
    
    public void through(){
        this.nodeColor = Color.YELLOW;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!TreeManager.isDeleteNode)
            return;
        
        Object[] options = {"Xoá chứ","Thôi không xoá nữa"};
        int n = JOptionPane.showOptionDialog(null,
                "Xoá thiệt luôn đó nha, Hối hận không?",
                "Hỏi nè",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[1]); 
        
        if(n != 0) return;
        
        TreeManager.Instance().deleteNode(key);
        TreeManager.Instance().updateLocation(false);
        TreeManager.Instance().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int x = 2;
        int y = 2;
        
        
        graphics.setColor(nodeColor);
        graphics.fillArc(x, y, 50, 50, 0, 360);
        
        graphics.setColor(Color.BLACK);
//        graphics.setStroke(new BasicStroke(3));
        graphics.drawArc(x, y, 50, 50, 0, 360);
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F);
        g.setFont(newFont);
        
        String s = this.key+"";
        FontMetrics fm = g.getFontMetrics();
        int xString = width/2 - fm.stringWidth(s)/2 - 5;
        int yString = height/2 + 3;
        
        graphics.setColor(Color.BLACK);
        graphics.drawString(s, xString, yString);
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += size.height;
        return size;
    }
    
    public void refresh(){
        this.nodeColor = defaultColor;
    }
}
