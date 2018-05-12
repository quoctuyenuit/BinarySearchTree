/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author QuocTuyen
 */
public class OutputArrayPanel extends JPanel{
    private JButton btn_NLR;
    private JButton btn_LNR;
    private JButton btn_LRN;
    private JLabel txtStatus;
    
    public OutputArrayPanel(){
        TreeManager.Instance().refresh();
        
        this.setBackground(new Color(209, 225, 216));
        
        this.setLayout(new GridLayout(0, 1, 5, 5));
        
        this.btn_LNR = new JButton("Xuất theo LNR");
        this.btn_LRN = new JButton("Xuất theo LRN");
        this.btn_NLR = new JButton("Xuất theo NLR");

        this.setSizeComponent(btn_NLR);
        this.setSizeComponent(btn_LRN);
        this.setSizeComponent(btn_LNR);
        
        this.txtStatus = new JLabel();
        txtStatus.setFont(txtStatus.getFont().deriveFont(txtStatus.getFont().getSize() * 1.4F));
        
        this.add(btn_NLR);
        this.add(btn_LNR);
        this.add(btn_LRN);
        
        JPanel statusPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JScrollPane scroll = new JScrollPane(statusPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBounds(0, 0, 200, 500);
        statusPanel.add(txtStatus);
        statusPanel.add(new JLabel("  "));
        
        statusPanel.setBackground(new Color(209, 225, 216));
        
        this.add(scroll);
        
        
        this.btn_NLR.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNLRActionPerformed(e);
            }
        });
        
        this.btn_LNR.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLNRActionPerformed(e);
            }
        });
        
        this.btn_LRN.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLRNActionPerformed(e);
            }
        });
        
    }
    
    public void btnNLRActionPerformed(ActionEvent e){
        if (TreeManager.Instance().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa có cây kìa pa!");
            return;
        }
        
        this.txtStatus.setText("Xuất theo NLR là: " + TreeManager.Instance().getNLR().toString());
    }
    
    public void btnLRNActionPerformed(ActionEvent e){
        if (TreeManager.Instance().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa có cây kìa pa!");
            return;
        }
        
        this.txtStatus.setText("Xuất theo NLR là: " + TreeManager.Instance().getLRN().toString());
    }
    
    public void btnLNRActionPerformed(ActionEvent e){
        if (TreeManager.Instance().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa có cây kìa pa!");
            return;
        }
        
        this.txtStatus.setText("Xuất theo NLR là: " + TreeManager.Instance().getLNR().toString());
    }
    
    private void setSizeComponent(Component component){
        component.setFont(component.getFont().deriveFont(component.getFont().getSize() * 1.4F));
        component.setBackground(new Color(175, 203, 187));
    }
    
}
