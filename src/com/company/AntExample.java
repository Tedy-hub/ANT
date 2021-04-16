package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;

enum TypeAnt{

    Worker,Warrior

}

public class AntExample extends JFrame {

    private JPanel mainPanel;
    private JLabel timerLabel;

    public static int TimeLivingWarrior = 100;
    public static int TimeLivingWorker = 100;
    public static int TimeSimulation = 10; // чтобы можно было обратиться из других классов

    private MyPanel canvas;
    private JButton Stop;
    private JButton Start;
    private JPanel SecondPanel; //= new MyPanel();
    private JPanel ControlPanel;
    private JCheckBox IsVisible;
    private JRadioButton timeVisible;
    private JRadioButton timeHidden;
    private JComboBox WarriorChance;
    private JTextField WarriorTimeSpawn;
    private JTextField WorkerTimeSpawn;
    private JComboBox WorkerChance;
    private JTextField TimeLiveWorker;
    private JTextField TimeLiveWarrior;
    private CustomMenu MyMenu;
    private JButton currentObjects;
    private JButton WarriorIntellect;
    private JButton WorkerIntellect;

    static public Vector<Ant> list = new Vector<>();
    static public HashSet<Integer> idList = new HashSet();
    static public TreeMap<Integer,Integer> BornList = new TreeMap();

    boolean isRunning = false;
    Habitat habitat;
    private int N1;
    private int N2;
    private double P1=0.1;
    private double P2=0.1;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    ActionListener taskPerformer = new ActionListener() {// Таймер
        public void actionPerformed(ActionEvent evt) {
            TimeSimulation++;
            if(timeVisible.isSelected())
                timerLabel.setText("Таймер: " + TimeSimulation);

            if(CheckTimeRespawn(TimeLivingWarrior)){// проверка окончания времени жизни Война
                RespawnAnt(TypeAnt.Warrior); }

            if(CheckTimeRespawn(TimeLivingWorker)){// проверка окончания времени жизни Рабого
                RespawnAnt(TypeAnt.Worker); }

            }
    };
    Timer timer = new Timer(1000,taskPerformer);

    public AntExample(String title){
        super(title);

        MyMenu = new CustomMenu();
        configureMenu();

        canvas = new MyPanel();
        canvas.setPreferredSize(new Dimension(500, -1));
        canvas.setBackground(new Color(140, 0,190));
        mainPanel.add(canvas);

        habitat = new Habitat(canvas);

        ControlPanel.setFocusable(true);

        this.setContentPane(mainPanel);

        timerLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        timerLabel.setText("\u200E");

        Color clr = Color.getHSBColor(204, (float)0.07, (float)0.25);
        ControlPanel.setBackground(clr);

        this.Start.addActionListener(this::actionStart);
        this.Stop.addActionListener(this::actionStop);
        this.timeVisible.addActionListener(this::timerVisible);
        this.timeHidden.addActionListener(this::timerHidden);
        this.IsVisible.addActionListener(this::toggleInfoShown);
        this.TimeLiveWorker.addActionListener(this::TimeLiveWorker);
        this.TimeLiveWarrior.addActionListener(this::TimeLiveWarrior);
        this.WorkerIntellect.addActionListener(this::ControlWorkerIntellect);
        this.WarriorIntellect.addActionListener(this::ControlWarriorIntellect);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        int i = 10;//Заполнение шансов муравьев
        while(i<=100) {
            this.WarriorChance.addItem(i);
            this.WorkerChance.addItem(i);
            i+=10;
        }
        this.WarriorChance.setSelectedItem(100);
        this.WorkerChance.setSelectedItem(100);
        this.WarriorChance.addActionListener(this::ChanceWarrior);
        this.WorkerChance.addActionListener(this::ChanceWorker);
        this.WarriorTimeSpawn.addActionListener(this::CheckTimeSpawnWarrior);
        this.WorkerTimeSpawn.addActionListener(this::CheckTimeSpawnWorker);
        this.currentObjects.addActionListener(this::startCurrentInfoDialog);
    }

    private void ControlWarriorIntellect(ActionEvent actionEvent) {
        if (WarriorIntellect.getText()=="Остановка воина"){
            WarriorIntellect.setText("Начать движение воина");
            //тут код с остановкой потока
        } else {
            WarriorIntellect.setText("Остановка воина");
            //тут код с возобновлением потока
        }
    }

    private void ControlWorkerIntellect(ActionEvent actionEvent) {
        if (WorkerIntellect.getText()=="Остановка рабочего"){
            WorkerIntellect.setText("Начать движение рабочего");
            //тут код с остановкой потока
        } else {
            WorkerIntellect.setText("Остановка рабочего");
            //тут код с возобновлением потока
        }
    }

    private void startCurrentInfoDialog(ActionEvent actionEvent) {
        currentObjects co = new currentObjects(this);
        //co.setSize(500,500);
        co.setBounds(500,500, 250,250);
        co.setVisible(true);
        //te.pack();
        //te.setVisible(true);



    }



    private void TimeLiveWorker(ActionEvent e){
        try{
            this.TimeLivingWorker = Integer.parseInt(this.TimeLiveWorker.getText().toString());
        }catch (Exception exception){
            this.TimeLivingWorker = 5;
        }
    }

    private void TimeLiveWarrior(ActionEvent e){
        try{
            this.TimeLivingWarrior = Integer.parseInt(this.TimeLiveWarrior.getText().toString());
        }catch (Exception exception){
            this.TimeLivingWarrior = 5;
        }
    }

    private void ChanceWarrior(ActionEvent e){
        this.P2 = Double.parseDouble(this.WarriorChance.getSelectedItem().toString())/100;
        System.out.println(this.P2+"");
    }

    private void CheckTimeSpawnWarrior(ActionEvent e){
        try{
            this.N2 = Integer.parseInt(this.WarriorTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N2 = 1000;
        }

        System.out.println(this.N2+"");

    }


    private void ChanceWorker(ActionEvent e){
        this.P1 = Double.parseDouble(this.WorkerChance.getSelectedItem().toString())/100;
        System.out.println(this.P1+"");
    }

    private void CheckTimeSpawnWorker(ActionEvent e){
        try{
            this.N1 = Integer.parseInt(this.WorkerTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N1 = 1000;
        }

        System.out.println(this.N1+"");

    }

    public void timerHidden(ActionEvent e){
        timerLabel.setText("\u200E");
        MyMenu.timerOptionHide.setSelected(true);
        timeHidden.setSelected(true);
    }

    public void timerVisible(ActionEvent e) {
        timerLabel.setText("Таймер: " + TimeSimulation);
        MyMenu.timerOptionShow.setSelected(true);
        timeVisible.setSelected(true);
    }

    public void actionStart(ActionEvent e) {
        start();
    }

    public void start(){
        if(!isRunning) {
            timerLabel.setText("Таймер: " + TimeSimulation);
            timeVisible.setSelected(true);
            habitat.ChangeProperties(this.P1, this.N1, this.P2, this.N2);
            timer.start();
            habitat.start();
            isRunning = true;
            Start.setEnabled(false);
            Stop.setEnabled(true);
        }
    }

    public void actionStop(ActionEvent e) {
        stop();
    }

    public void toggleInfoShown(ActionEvent e){
        MyMenu.infoOptionShow.setSelected(IsVisible.isSelected());
        //IsVisible.setSelected(!IsVisible.isSelected());
    }

    public void stop(){
        int quantityWorkers = WorkerAnt.quantity_ant;
        int quantityWarriors = WarriorAnt.quantity_ant;

        if(IsVisible.isSelected()) {
            paintResult(quantityWarriors, quantityWorkers);
        } else {
            StopOperations();
        }
        repaint();
    }

    public void paintResult(int quantityWarriors ,int quantityWorkers){
        int response  = JOptionPane.showConfirmDialog(this,"Warrior ants quantity: " + quantityWarriors  +
                "\nWorker ants quantity: " + quantityWorkers +
                "\nTime passed: " + TimeSimulation,"Хотите продолжить?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        switch (response){

            case JOptionPane.NO_OPTION:{
                StopOperations();
                break;
            }
            case JOptionPane.CLOSED_OPTION:{
                StopOperations();
                break;
            }
        }
    }

    public void StopOperations(){
        if(isRunning) {
            timer.stop();
            habitat.stop();
            isRunning = false;
            Stop.setEnabled(false);
            Start.setEnabled(true);
        }
    }


    KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(final KeyEvent e) {
            if(e.getID() == KeyEvent.KEY_RELEASED){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_B: {
                    start();
                    break;
                }
                case KeyEvent.VK_E: {
                    stop();
                    break;
                }
                case KeyEvent.VK_T: {
                    System.out.println("Pressed T!\n");
                    if (timeVisible.isSelected()) {
                        timeHidden.doClick();
                    } else if (timeHidden.isSelected()) {
                        timeVisible.doClick();
                    }
                    break;
                }
                case KeyEvent.VK_I: {
                    paintResult(WarriorAnt.quantity_ant, WorkerAnt.quantity_ant);
                    break;
                }
            }
            }
            return false;
        }
    };

    public void RespawnAnt(TypeAnt typeAnt){

     //   this.paint(this.getGraphics());
        habitat.respawn(typeAnt);

    }


    public void configureMenu(){
        MyMenu.simOptionStart.addActionListener(this::actionStart);
        MyMenu.simOptionStop.addActionListener(this::actionStop);

        MyMenu.infoOptionShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IsVisible.setSelected(!IsVisible.isSelected());
            }
        });

        MyMenu.timerOptionShow.addActionListener(this::timerVisible);
        MyMenu.timerOptionHide.addActionListener(this::timerHidden);

        this.setJMenuBar(MyMenu.menuBar);
    }

    private boolean CheckTimeRespawn(int TimeLiving){
        if(AntExample.TimeSimulation % TimeLiving == 0) return true;
        else return false;

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
