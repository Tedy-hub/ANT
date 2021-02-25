package com.company;

import com.company.ant.WarriorAnt;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AntExample extends JFrame implements KeyListener {
    private JPanel mainPanel;
    private JLabel timerLabel;
    Habitat habitat = new Habitat(this,1000,1000);
    int cekonds = 0;
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timerLabel.setText(Integer.toString(cekonds));
            cekonds++;
        }
    };
    Timer timer = new Timer(1000,taskPerformer);


    public AntExample(String title){
        super(title);
        this.addKeyListener(this);
        this.setContentPane(mainPanel);
        timerLabel.setVisible(false);
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
            if(timerLabel.isVisible()){
                timerLabel.setVisible(false);
            }else {
                timerLabel.setVisible(true);
            }
        }
        //B      ttTTEEBBBt
        if(e.getKeyCode()==KeyEvent.VK_B){
            habitat.start();
            timer.start();
        }
        //E
        if(e.getKeyCode()==KeyEvent.VK_E){
            System.out.println("нажали E");
            habitat.stop();
            timer.stop();
        }
    }
    //клавиша отпущена
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
