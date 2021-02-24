package com.company;

import com.company.ant.WarriorAnt;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AntExample extends JFrame implements KeyListener {
    private JPanel mainPanel;
    //private Thread thread;
    Habitat habitat = new Habitat(this,1000,1000);


    public AntExample(String title){
        super(title);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(5000,5000);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public void run(){
    }
    //клавиша нажата и отпущена
    @Override
    public void keyTyped(KeyEvent e) {
    }
    //клавиша нажата, но не отпущена
    @Override
    public void keyPressed(KeyEvent e) {
        //T
        if(e.getKeyCode()==KeyEvent.VK_T){
            System.out.println("нажали T");
        }
        //B      ttTTEEBBBt
        if(e.getKeyCode()==KeyEvent.VK_B){
            System.out.println("нажали B");
            habitat.start();
        }
        //E
        if(e.getKeyCode()==KeyEvent.VK_E){
            System.out.println("нажали E");
            habitat.stop();
        }
    }
    //клавиша отпущена
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
