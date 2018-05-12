/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import javafx.animation.Animation;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author QuocTuyen
 */
public class MainFrame {
    private JFrame frame;
    private JPanel showSpace;
    private JPanel menuSpace;
    private JPanel selectionMenuSpace;
    private JPanel detaiMenuSpace;
    private JPanel detaiContents;
    
    public MainFrame(){
        initComponents();
        initTree();
    }
    
    private void initComponents(){
        this.frame = new JFrame("Binary Search Tree");
        
        this.showSpace = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g); //To change body of generated methods, choose Tools | Templates.
                TreeManager.Instance().paintLink(g);
                NodeAnimation.Instance().paint(g);
            }
          
        };
        
        this.showSpace.setBackground(Color.LIGHT_GRAY);
        this.showSpace.setLayout(new GroupLayout(this.showSpace));
        this.showSpace.setAutoscrolls(true);
        
        JScrollPane scroll = new JScrollPane(showSpace);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        scroll.setBounds(0, 0, showSpace.getWidth(), showSpace.getHeight());
        
        this.frame.getContentPane().add(scroll);
        
        
        this.frame.add(this.showSpace, BorderLayout.CENTER);
        //======================================================================
        this.menuSpace = new JPanel(new GridLayout(1, 2, 5, 5));
        this.menuSpace.setPreferredSize(new Dimension(600, 270));
        this.menuSpace.setBorder(new EmptyBorder(5, 5, 5, 5));
        //======================================================================
        this.selectionMenuSpace = new JPanel(new GridLayout(0, 1, 3, 3));
        //this.selectionMenuSpace.setLayout(new BoxLayout(selectionMenuSpace, BoxLayout.PAGE_AXIS));
        this.selectionMenuSpace.setBackground(new Color(209, 225, 216));
        this.selectionMenuSpace.setBorder(new EmptyBorder(5, 25, 15, 5));
        //======================================================================
        this.detaiMenuSpace = new JPanel();
        this.detaiMenuSpace.setBackground(new Color(209, 225, 216));
        this.detaiMenuSpace.setBorder(new EmptyBorder(5, 15, 5, 15));
        this.detaiMenuSpace.setLayout(new BorderLayout());
//        this.detaiMenuSpace.setLayout(new SpringLayout());
        //======================================================================
        this.menuSpace.add(this.selectionMenuSpace);
        this.menuSpace.add(this.detaiMenuSpace);
        //======================================================================
        this.frame.add(this.menuSpace, BorderLayout.SOUTH);
        
        this.frame.setSize(1500, 900);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        //======================================================================
        
        JRadioButton createRad = new JRadioButton("Tạo cây nhị phân tìm kiếm");
        createRad.setSelected(true);
        this.setPropertyRadioButton(createRad, 1.4F);
        JRadioButton balanceRad = new JRadioButton("Cân bằng cây nhị phân tìm kiếm");
        this.setPropertyRadioButton(balanceRad, 1.4F);
        JRadioButton deleteRad = new JRadioButton("Xoá node trong cây");
        this.setPropertyRadioButton(deleteRad, 1.4F);
        JRadioButton findRad = new JRadioButton("Tìm kiếm trong cây");
        this.setPropertyRadioButton(findRad, 1.4F);
        JRadioButton outputRad = new JRadioButton("Xuất cây ra dạng mảng");
        this.setPropertyRadioButton(outputRad, 1.4F);
        
        
        ButtonGroup group = new ButtonGroup();
        group.add(createRad);
        group.add(balanceRad);
        group.add(deleteRad);
        group.add(findRad);
        group.add(outputRad);
        
        this.selectionMenuSpace.add(createRad);
        this.selectionMenuSpace.add(balanceRad);
        this.selectionMenuSpace.add(deleteRad);
        this.selectionMenuSpace.add(findRad);
        this.selectionMenuSpace.add(outputRad);
        
        createRad.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (detaiMenuSpace.getComponent(0) instanceof CreatingPanel)
                    return;
                TreeManager.isDeleteNode = false;
                detaiMenuSpace.setLayout(new BorderLayout());
                detaiMenuSpace.removeAll();
                detaiMenuSpace.add(new CreatingPanel());
                detaiMenuSpace.revalidate();
                detaiMenuSpace.repaint();
            }
        });
        
        balanceRad.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(detaiMenuSpace.getComponent(0) instanceof BalancingPanel)
                    return;
                TreeManager.isDeleteNode = false;
                detaiMenuSpace.setLayout(new BorderLayout());
                detaiMenuSpace.removeAll();
                detaiMenuSpace.add(new BalancingPanel(), BorderLayout.PAGE_END);
                detaiMenuSpace.revalidate();
                detaiMenuSpace.repaint();
            }
        });
        
        findRad.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(detaiMenuSpace.getComponent(0) instanceof FindingPanel)
                    return;
                TreeManager.isDeleteNode = false;
                detaiMenuSpace.removeAll();
                detaiMenuSpace.add(new FindingPanel(), BorderLayout.PAGE_START);
                detaiMenuSpace.revalidate();
                detaiMenuSpace.repaint();
            }
        });
        
        deleteRad.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(detaiMenuSpace.getComponent(0) instanceof DeletingPanel)
                    return;
                detaiMenuSpace.removeAll();
                detaiMenuSpace.add(new DeletingPanel(), BorderLayout.PAGE_START);
                detaiMenuSpace.revalidate();
                detaiMenuSpace.repaint();
            }
        });
        
        outputRad.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(detaiMenuSpace.getComponent(0) instanceof OutputArrayPanel)
                    return;
                TreeManager.isDeleteNode = false;
                
                detaiMenuSpace.removeAll();
                detaiMenuSpace.add(new OutputArrayPanel(), BorderLayout.PAGE_START);
                detaiMenuSpace.revalidate();
                detaiMenuSpace.repaint();
            }
        });
        
        //======================================================================


        
        detaiMenuSpace.add(new CreatingPanel());
    }
    
    
    
    private void setPropertyRadioButton(Component c, float size){
        Font currentFont = c.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * size);
        c.setFont(newFont);
        c.setBackground(new Color(209, 225, 216));
    }
    
    private void initTree(){
        TreeManager.Instance().setContainer(showSpace);
//        int[] test = {15, 8, 10, 7, 19, 25, 30, 35, 40};
//        TreeManager.Instance().buildTreeFromArray(test);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
