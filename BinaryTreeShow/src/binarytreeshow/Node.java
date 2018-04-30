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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author QuocTuyen
 */
public class Node extends JComponent implements MouseListener{
    int key;
    Node left;
    Node right;
    Point parentPoint;// Điểm liên kết giữa node cha và node con
    final int width = 60;
    final int height = 60;
    
    /*
    valueKey: Value of node
    linkPoint: Điểm liên kết giữa các node
    level: Level of node
    isRight: Kiểm tra node thuộc bên phải ko
    */
    Node(int valueKey, Point parentPoint, int level, boolean isRight){
        super();
        enableInputMethods(true);
        addMouseListener(this);
        this.key = valueKey;
        this.left = this.right = null;
        this.parentPoint = parentPoint;
        this.setSize(new Dimension(width, height));
        
        if(parentPoint == null){
            this.setLocation(800, 100);
            return;
        }
        
        int x = (isRight == true)? (parentPoint.x + 300/level): (parentPoint.x - 300/level);
        int y = parentPoint.y + 120;
        this.setLocation(x,y);
    }
     
    public Node(){
        super();
        enableInputMethods(true);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Clicked");
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
        
        
        graphics.setColor(new Color(131, 204, 201));
        graphics.fillArc(x, y, 50, 50, 0, 360);
        graphics.setColor(Color.BLUE);
        graphics.setStroke(new BasicStroke(3));
        
        
        graphics.drawArc(x, y, 50, 50, 0, 360);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);
        
        String s = this.key+"";
        FontMetrics fm = g.getFontMetrics();
        int xString = width/2 - fm.stringWidth(s)/2 - 5;
        int yString = height/2 + 3;
        
        graphics.drawString(s, xString, yString);
        
        if(this.parentPoint != null){
            int sX = parentPoint.x + width/2;
            int sY = parentPoint.y + height;
            
            int dX = this.getLocation().x+ width/2;
            int dY = this.getLocation().y;
            Graphics gr = this.getParent().getGraphics();
            Container c  = this.getParent();
            String a = c.getName();
            gr.setColor(Color.BLUE);
            gr.drawLine(sX, sY, dX, dY);
        }

       
        
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += size.height;
        return size;
    }
}
