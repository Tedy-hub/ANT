package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class AntExample extends JFrame implements KeyListener {

    private JPanel mainPanel;
    private JLabel timerLabel;
    private JButton Stop;
    private JButton Start;
    private JPanel SecondPanel;
    private ArrayList<Ant> list;

    Habitat habitat = new Habitat(this.SecondPanel);
    int seconds = 0;
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timerLabel.setText("Секундомер: " + seconds);
            seconds++;
            }
    };
    Timer timer = new Timer(1000,taskPerformer);


    public AntExample(String title){
        super(title);
        this.addKeyListener(this);
        this.setContentPane(mainPanel);
        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        timerLabel.setText("Секундомер: " + seconds);
        seconds++;
        this.Start.addActionListener(this::actionStart);
        this.Stop.addActionListener(this::actionStop);


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

    public void actionStart(ActionEvent e) {
        timer.start();
        habitat.start();
        isRunning = true;
    }

    public void actionStop(ActionEvent e) {
        System.out.println("нажали E");

        int quantityWorkers = WorkerAnt.quantity_ant;
        int quantityWarriors = WarriorAnt.quantity_ant;

        habitat.stop();
        timer.stop();
        isRunning = false;

        JOptionPane.showMessageDialog(this, "Warrior ants quantity: " + quantityWarriors  +
                "\nWorker ants quantity: " + quantityWorkers +
                "\nTime passed: " + seconds);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    //клавиша нажата, но не отпущена

    boolean isRunning = false;
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
            if(!isRunning) {
                timer.start();
                habitat.start();
                isRunning = true;
            }
        }
        //E
        if(e.getKeyCode()==KeyEvent.VK_E){
            System.out.println("нажали E");

            int quantityWorkers = WorkerAnt.quantity_ant;
            int quantityWarriors = WarriorAnt.quantity_ant;

            habitat.stop();
            timer.stop();
            isRunning = false;

            JOptionPane.showMessageDialog(this, "Warrior ants quantity: " + quantityWarriors  +
                    "\nWorker ants quantity: " + quantityWorkers +
                    "\nTime passed: " + seconds);
            repaint();
        }

        if(e.getKeyCode()==KeyEvent.VK_I){
            System.out.println("нажали I");
            int quantityWorkers = WorkerAnt.quantity_ant;
            int quantityWarriors = WarriorAnt.quantity_ant;
            JOptionPane.showMessageDialog(this, "Warrior ants quantity: " + quantityWarriors  +
                    "\nWorker ants quantity: " + quantityWorkers +
                    "\nTime passed: " + seconds);
        }

    }

    //клавиша отпущена
    @Override
    public void keyReleased(KeyEvent e) {
    }


}
