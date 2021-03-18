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
import java.util.ArrayList;

public class AntExample extends JFrame {

    private JPanel mainPanel;
    private JLabel timerLabel;
    private JButton Stop;
    private JButton Start;
    private JPanel SecondPanel;
    private JPanel ControlPanel;
    private JCheckBox IsVisiable;
    private JRadioButton timeVisible;
    private JRadioButton timeHidden;
    private ArrayList<Ant> list;
    boolean isRunning = false;
    Habitat habitat = new Habitat(this.SecondPanel);
    int seconds = 1;

    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timerLabel.setText("Таймер: " + seconds);
            seconds++;
            }
    };
    Timer timer = new Timer(1000,taskPerformer);

    public AntExample(String title){
        super(title);

        ControlPanel.setFocusable(true);
        this.setContentPane(mainPanel);
        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        timerLabel.setText("Таймер: " + seconds);
        Color clr = Color.getHSBColor(204, (float)0.07, (float)0.25);
        ControlPanel.setBackground(clr);
        this.Start.addActionListener(this::actionStart);
        this.Stop.addActionListener(this::actionStop);
        this.timeVisible.addActionListener(this::timerVisible);
        this.timeHidden.addActionListener(this::timerHidden);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    private void timerHidden(ActionEvent e){
        if(timeHidden.isSelected()) {
            timerLabel.setVisible(false);
        }
    }

    private void timerVisible(ActionEvent e) {
        if(timeVisible.isSelected()){
            timerLabel.setVisible(true);
        }
    }

    public void actionStart(ActionEvent e) {
        start();
    }
    public void start(){
        if(!isRunning) {
            timerLabel.setVisible(true);
            habitat.start();
            timer.start();
            isRunning = true;
            Start.setEnabled(false);
            Stop.setEnabled(true);
        }
    }

    public void actionStop(ActionEvent e) {
        stop();
    }
    public void stop(){
        int quantityWorkers = WorkerAnt.quantity_ant;
        int quantityWarriors = WarriorAnt.quantity_ant;


        if(isRunning) {
            timer.stop();
            habitat.stop();
            isRunning = false;
            Stop.setEnabled(false);
            Start.setEnabled(true);

        }
        if(IsVisiable.isSelected()) {
            paintResult(quantityWarriors, quantityWorkers);
        }
        repaint();
    }

    public void paintResult(int quantityWarriors ,int quantityWorkers){
        JOptionPane.showMessageDialog(this, "Warrior ants quantity: " + quantityWarriors  +
                "\nWorker ants quantity: " + quantityWorkers +
                "\nTime passed: " + seconds);
    }

    KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(final KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_B:{
                    start();
                    break;
                }
                case KeyEvent.VK_E:{
                    stop();
                    break;
                }
                case KeyEvent.VK_T:{
                    if(timeVisible.isSelected()){
                        timeHidden.setSelected(true);
                        timerLabel.setVisible(false);

                    } else {
                        timeVisible.setSelected(true);
                        timerLabel.setVisible(true);
                    }
                    break;
                }
                case KeyEvent.VK_I:{
                    paintResult(WarriorAnt.quantity_ant,WorkerAnt.quantity_ant);
                    break;
                }
            }
            return false;
        }
    };



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        habitat.respawn();
    }
}
