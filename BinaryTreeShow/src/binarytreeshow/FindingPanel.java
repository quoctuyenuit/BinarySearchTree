/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author QuocTuyen
 */
public class FindingPanel extends JPanel{
    private JTextField txt_key;
    private JButton btn_find;
    private JLabel txtStatus;
    
    public FindingPanel(){
        this.setBackground(new Color(209, 225, 216));
        
        this.setLayout(new GridLayout(0, 1, 10, 10));
        this.txtStatus = new JLabel();
        this.txt_key = new JTextField();
        this.txt_key.setFont(txt_key.getFont().deriveFont(txt_key.getFont().getSize() * 1.4F));
        
        this.btn_find = new JButton("Tìm đi");
        this.btn_find.setFont(btn_find.getFont().deriveFont(btn_find.getFont().getSize() * 1.4F));
        
        JLabel label = new JLabel("Giá trị cần tìm: ");
        label.setFont(label.getFont().deriveFont(label.getFont().getSize() * 1.4F));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(label);
        panel.add(txt_key);
     
        panel.setBackground(new Color(209, 225, 216));
        
        btn_find.setBackground(new Color(175, 203, 187));
        
        this.add(panel);
        this.add(btn_find);
        this.add(this.txtStatus);
        
        this.btn_find.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFindActionPerformed(e);
            }
        });
    }
    
    private void btnFindActionPerformed(ActionEvent e){
        TreeManager.Instance().refresh();
        
        if(TreeManager.Instance().isEmpty()){
            JOptionPane.showMessageDialog(null, "Chưa tạo cây kìa pa, không có cây lấy gì tìm!");
            return;
        }
        
        if(txt_key.getText().equals("")){
            return;
        }
        
        int key = Integer.parseInt(txt_key.getText());
        if(TreeManager.Instance().find(key))
            this.txtStatus.setText("Tìm thấy giá trị " + key + " rồi nhé!, xem lại đường dẫn trên cây đi nha! <3");
        else
            this.txtStatus.setText("Không có giá trị cần tìm trong cây, tìm hoài không thấy! ><");
    }
    
}
