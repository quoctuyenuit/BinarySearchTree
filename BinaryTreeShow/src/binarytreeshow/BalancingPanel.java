/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author QuocTuyen
 */
public class BalancingPanel extends JPanel{
    private JButton btn_Start;
    public BalancingPanel(){
        TreeManager.Instance().refresh();
        
        this.setLayout(new BorderLayout());
        this.btn_Start = new JButton("Bắt đầu cân bằng");
        btn_Start.setBackground(new Color(175, 203, 187));
        this.btn_Start.setFont(btn_Start.getFont().deriveFont(btn_Start.getFont().getSize() * 1.4F));
        
        this.add(btn_Start, BorderLayout.CENTER);
        
        this.btn_Start.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_StartActionPerformed(e);
            }
        });
    }
    
    private void btn_StartActionPerformed(ActionEvent e){
        
        if(TreeManager.Instance().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo cây kìa, không có cây sao cân bằng.");
            return;
        }
        
        TreeManager.Instance().balanceTheTree();
    }
}
