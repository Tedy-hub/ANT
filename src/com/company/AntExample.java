package com.company;

import com.company.ant.Ant;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class AntExample extends JFrame implements KeyListener {
    private JPanel mainPanel;
    private JLabel timerLabel;
    private JLabel info;
    private JLabel quantityAnt;
    private ArrayList<Ant> list;
    Habitat habitat = new Habitat(this);


    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timerLabel.setText("Секундомер: " + habitat.update());;
        }
    };
    Timer timer = new Timer(1000,taskPerformer);


    public AntExample(String title){
        super(title);
        this.addKeyListener(this);
        this.setContentPane(mainPanel);
        quantityAnt.setVisible(false);
        info.setVisible(false);
        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        habitat.respawn();
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
        //B
        if(e.getKeyCode()==KeyEvent.VK_B){
            habitat.start();
            timer.start();
        }
        //E
        if(e.getKeyCode()==KeyEvent.VK_E){
            System.out.println("нажали E");
            habitat.stop();
            timer.stop();
            getinfo();
        }

        if(e.getKeyCode()==KeyEvent.VK_I){
            System.out.println("нажали I");
        }

    }

    //клавиша отпущена
    @Override
    public void keyReleased(KeyEvent e) {
    }


    public void getinfo(){
        list = habitat.getList();
        int quantityWorkers=0;
        int quantityWarriors=0;
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getName()=="Warrior Ant"){
                quantityWarriors++;
            } else {
                quantityWorkers++;
            }
        }

        info.setVisible(true);
        info.setForeground( new java.awt.Color(220, 20, 60));
        info.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        info.setText("количество воинов: " + Integer.toString(quantityWarriors) + "количество рабочих: " + Integer.toString(quantityWorkers) );

    }

}
