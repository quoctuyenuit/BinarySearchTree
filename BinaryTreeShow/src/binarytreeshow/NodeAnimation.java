/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreeshow;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Calendar;

/**
 *
 * @author QuocTuyen
 */
public class NodeAnimation {
    int key;
    private Color nodeColor;
    private final int width = 60;
    private final int height = 60;
    private Point nodeLocation;
    private Point destinationLocation;
    
    private static NodeAnimation _instance;
    private boolean isStart;
    private Container container;
    
    public static NodeAnimation Instance(){
        if(_instance == null)
            _instance = new NodeAnimation();
        return _instance;
    }
    
    public NodeAnimation(){
        this.velocity = 300;
        nodeColor = Color.YELLOW;
    }
    
    public void setContainer(Container container){
        this.container = container;
    }
    
    public void setStart(Node node){
        if(isStart)
            return;
        
        this.key = node.key;
        this.nodeLocation = new Point(node.getLocation());
    }
    
    private float angle;
    private float velocity;
    private float time;
    
    void update(float deltaTime){
        this.time += deltaTime;
        //Chiếu vận tốc lên các trục toạ độ
        float vx = (float) (this.velocity*Math.cos(angle));
        float vy = (float) (this.velocity*Math.sin(angle));
       
        //Tính lại toạ độ của vật
        
        nodeLocation.x += vx*deltaTime;
        nodeLocation.y += vy*deltaTime;
    }
    
    private Thread thread;
    
    public void start(Point desLocation){
        if(isStart)
            return;
        
        this.isStart = true;
        this.destinationLocation = desLocation;
        
        float a = destinationLocation.x - nodeLocation.x;
        float b =destinationLocation.y  - nodeLocation.y;
        
        float cosA = (float) (a/Math.sqrt(a*a + b*b));
        angle = (float) Math.acos(cosA);
        
        
        if(b<0) //Chuyển dấu góc nếu vector hướng lên trên
            angle*=-1;
        
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
                float fps = 80;
                long deltaTime = 0;

                float tickPerFrame = 1000/fps;

                long timeStart = Calendar.getInstance().getTimeInMillis();
                while(isStop() == false)
                {
                    deltaTime = Calendar.getInstance().getTimeInMillis() - timeStart;
                    if(deltaTime >= tickPerFrame)
                    {
                        update((float)deltaTime/1000);
                        container.repaint();
                        try {
//                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        timeStart +=deltaTime;
                    }
                }
//                isStart = false;
//                thread.stop();
//            }
//        });
//        
//        thread.start();
    }
    
    public boolean isStart(){
        return isStart;
    }
    
    private boolean isStop(){
        return (nodeLocation.x >= (destinationLocation.x - 10) &&
                nodeLocation.x <= (destinationLocation.x + 10) &&
                nodeLocation.y >= (destinationLocation.y - 10) &&
                nodeLocation.y <= (destinationLocation.y + 10));
    }
    
    public void setDestinationLocation(Point location){
        this.destinationLocation = new Point(location);
    }
    
    public void paint(Graphics g){
        if(!isStart)
            return;
        
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int x = nodeLocation.x;
        int y = nodeLocation.y;
        
        
        graphics.setColor(nodeColor);
        graphics.fillArc(x, y, 50, 50, 0, 360);
        
        graphics.setColor(Color.BLACK);
        graphics.drawArc(x, y, 50, 50, 0, 360);
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F);
        g.setFont(newFont);
        
        String s = this.key+"";
        FontMetrics fm = g.getFontMetrics();
        int xString = width/2 - fm.stringWidth(s)/2 - 5 +x ;
        int yString = height/2 + 3 + y;
        
        graphics.setColor(Color.BLACK);
        graphics.drawString(s, xString, yString);
    }
}
