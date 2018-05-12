/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author QuocTuyen
 */
public class DeletingPanel extends JPanel{
    private JLabel txtStatus;
    
    public DeletingPanel(){
        TreeManager.Instance().refresh();
        
        this.setBackground(new Color(209, 225, 216));
        
        TreeManager.isDeleteNode = true;
        this.txtStatus = new JLabel("Click vào node mà mình muốn xoá để xoá nhé! <3");
        txtStatus.setFont(txtStatus.getFont().deriveFont(txtStatus.getFont().getSize() * 1.4F));
        
        this.add(txtStatus);
    }
}
