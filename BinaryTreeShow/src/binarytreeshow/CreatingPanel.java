/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author QuocTuyen
 */
public class CreatingPanel extends JPanel {
    private JTextField txt_N;
    private JTextField txt_From;
    private JTextField txt_To;
    private JTextField txt_Array;
    private JButton btn_Random;
    private JButton btn_Start;
    
    
    private int n = 0;//Số lượng phần tử của mảng
    
    public CreatingPanel(){
        this.setLayout(new GridLayout(0, 1, 10, 10));
        this.setBackground(new Color(209, 225, 216));
        
        JLabel label1 = new JLabel("Tạo cây từ mảng bất kỳ");
        JPanel b = new JPanel(new GridLayout(1, 1, 26, 25));
        JLabel l = new JLabel("Số lượng phần tử: ");
        l.setFont(l.getFont().deriveFont(l.getFont().getSize()*1.4F));
        b.add(l);
        this.txt_N = new JTextField("10");
        this.txt_N.setPreferredSize(new Dimension(50, 25));
        this.txt_N.setFont(txt_N.getFont().deriveFont(txt_N.getFont().getSize() * 1.4F));
        b.add(txt_N);
        this.add(b);
        //----------------------------------------------------------------------
        JPanel randomPanel = new JPanel(new GridLayout(1, 1, 15, 15));
        JLabel label2 = new JLabel("Bắt đầu");
        this.txt_From = new JTextField("0");
        this.txt_From.setPreferredSize(new Dimension(70, 30));
        JLabel label3 = new JLabel("Đến");
        this.txt_To = new JTextField("20");
        this.btn_Random = new JButton("Ngẫu nhiên");
        
        
        
        randomPanel.add(label2);
        randomPanel.add(this.txt_From);
        randomPanel.add(label3);
        randomPanel.add(this.txt_To);
        randomPanel.add(this.btn_Random);
        for (Component component : randomPanel.getComponents()) {
            Font currentFont = component.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F);
            component.setFont(newFont);
            if(component instanceof JButton)
                component.setBackground(new Color(175, 203, 187));
        }
        //----------------------------------------------------------------------
        JLabel label4 = new JLabel("Tạo cây từ mảng được nhập vào");
        this.txt_Array = new JTextField();
        this.btn_Start = new JButton("Bắt đầu");
        
        this.add(label1);
        this.add(randomPanel);
        this.add(label4);
        this.add(this.txt_Array);
        this.add(this.btn_Start);
        
        for (Component component : this.getComponents()) {
            if(component instanceof JTextField)
                continue;
            Font currentFont = component.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            component.setFont(newFont);
            component.setBackground(new Color(209, 225, 216));
            
            if(component instanceof JButton)
                component.setBackground(new Color(175, 203, 187));
        }
        //----------------------------------------------------------------------
        //Set Events
        //----------------------------------------------------------------------
        this.txt_N.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e){
                txt_keyTypeJustNumber(e);
            }
        });
        
        this.txt_From.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                txt_keyTypeJustNumber(e);
            }
        });
        
        this.txt_To.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e){
                txt_keyTypeJustNumber(e);
            }
        });
        
        this.txt_Array.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == KeyEvent.VK_SPACE))) {
                    e.consume();
                }  
            }
        });
        
        this.btn_Random.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txt_From.getText().equals("") || txt_To.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Chưa nhập giá trị xác định vùng kìa\n Nhập lại đi pa!");
                        return;
                    }

                    if(txt_N.getText().equals("") || txt_N.getText().equals("0")){
                        JOptionPane.showMessageDialog(null, "Không có phần tử nào thì tạo cây kiểu gì?\nNhập lại đi [><]");
                        return;
                    }

                    int min = Integer.parseInt(txt_From.getText());
                    int max = Integer.parseInt(txt_To.getText());
                    n = Integer.parseInt(txt_N.getText());
                    if(min >= max){
                        JOptionPane.showMessageDialog(null, "Giá trị bắt đầu phải nhỏ hơn tía trị đến\nNhập lại đi pa");
                        return;
                    }
                    
                    if( n >= max - min){
                        JOptionPane.showMessageDialog(null, "Nhập lại giá trị bắt đầu và kết thúc lại đi, nhập thoả mãn khoảng cách max và min phải lớn hơn số lượng phần tử");
                        return;
                    }

                    String array = new String();
                    Random rand = new Random();
                    int i = n;
                    while(i > 0){
                        int temp = rand.nextInt(max - min + 1) + min;
                        if(!array.contains(temp + ""))
                        {
                            array += temp + " ";
                            i--;
                        }
                    }
                    txt_Array.setText(array);
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(null, "Đã có lỗi xay ra trong lúc nhập rồi, kiểm tra lại đi!");
                }
                
            }
        });
        
        this.btn_Start.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(txt_N.getText().equals("") || txt_N.getText().equals("0")){
                       JOptionPane.showMessageDialog(null, "Không có phần tử nào thì tạo cây kiểu gì?\nNhập lại đi [><]");
                       return;
                   }
                    else if( n ==0 ){
                        n = Integer.parseInt(txt_N.getText());
                    }

                   if(n == 0){
                       JOptionPane.showMessageDialog(null, "Không có phần tử nào thì tạo cây kiểu gì?\nNhập lại đi [><]");
                       return;
                   }

                   String[] array = txt_Array.getText().split(" ");
                   ArrayList<Integer> list = new ArrayList<>();
                   for (String element : array) {
                       if(!element.equals(" ") && !element.equals(""))
                           list.add(Integer.parseInt(element));
                   }

                   TreeManager.Instance().destroy();
                   TreeManager.Instance().buildTreeFromList(list);
                   TreeManager.Instance().repaint();
                   }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Đã có lỗi xay ra trong lúc nhập rồi, kiểm tra lại đi!");
                }
            }
        });
        
    }
    
    private void txt_keyTypeJustNumber(java.awt.event.KeyEvent e){
        char c = e.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
            e.consume();
        }
    }
    
}
