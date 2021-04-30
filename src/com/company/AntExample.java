package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.MyConsole;

import javax.print.Doc;
import javax.sound.sampled.BooleanControl;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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
    public static int TimeSimulation = 1; // чтобы можно было обратиться из других классов

    private MyPanel canvas;
    private JButton Stop;
    private JButton Start;
    private JPanel SecondPanel; //= new MyPanel();
    private JPanel ControlPanel;
    private JCheckBox IsVisible;
    private JRadioButton timeVisible;
    private JRadioButton timeHidden;
    private JComboBox WarriorChance;
    private JComboBox WorkerChance;
    private JTextField WarriorTimeSpawn;
    private JTextField WorkerTimeSpawn;
    private JTextField TimeLiveWorker;
    private JTextField TimeLiveWarrior;
    private CustomMenu MyMenu;
    private JButton currentObjects;
    private JButton WarriorIntellect;
    private JButton WorkerIntellect;
    private JComboBox priorityThreadWorker;
    private JComboBox priorityThreadWarrior;
    private JButton dialogConsole;
    private Config cfg;

    private JButton Download;
    private JButton Save;

    static public Vector<Ant> list = new Vector<>();
    static public HashSet<Integer> idList = new HashSet();
    static public TreeMap<Integer,Integer> BornList = new TreeMap();

    boolean isRunning = false;
    Habitat habitat;
    Serializate serializate;
    private int N1;
    private int N2;
    private double P1=1.0;
    private double P2=1.0;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    ActionListener taskPerformer = new ActionListener() {// Таймер
        public void actionPerformed(ActionEvent evt) {
            TimeSimulation++;
            if(timeVisible.isSelected())
                timerLabel.setText("Таймер: " + TimeSimulation);

            CheckTimeRespawn(TimeLivingWarrior, TypeAnt.Warrior);// проверка окончания времени жизни Война

            CheckTimeRespawn(TimeLivingWorker, TypeAnt.Worker);// проверка окончания времени жизни Рабого

            }
    };
    Timer timer = new Timer(1000,taskPerformer);

    public AntExample(String title) {
        super(title);

        MyMenu = new CustomMenu();
        configureMenu();

        canvas = new MyPanel();
        canvas.setPreferredSize(new Dimension(500, -1));
        canvas.setBackground(new Color(140, 0, 190));
        mainPanel.add(canvas);

        habitat = new Habitat(canvas);
        serializate = new Serializate(this);

        ControlPanel.setFocusable(true);

        this.setContentPane(mainPanel);

        timerLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        timerLabel.setText("\u200E");

        Color clr = Color.getHSBColor(204, (float) 0.07, (float) 0.25);
        ControlPanel.setBackground(clr);

        int i = 10;//Заполнение шансов муравьев
        while (i <= 100) {
            this.WarriorChance.addItem(i);
            this.WorkerChance.addItem(i);
            this.priorityThreadWorker.addItem(i / 10);
            this.priorityThreadWarrior.addItem(i / 10);
            i += 10;
        }

        this.priorityThreadWorker.setSelectedItem(1);//установки списка на первый элемент
        this.priorityThreadWarrior.setSelectedItem(1);

        this.Start.addActionListener(this::actionStart);
        this.Stop.addActionListener(this::actionStop);
        this.timeVisible.addActionListener(this::timerVisible);
        this.timeHidden.addActionListener(this::timerHidden);
        this.IsVisible.addActionListener(this::toggleInfoShown);
        this.TimeLiveWarrior.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                TimeLiveWarrior();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                TimeLiveWarrior();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                TimeLiveWarrior();
            }
        });

        this.TimeLiveWorker.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                TimeLiveWorker();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                TimeLiveWorker();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                TimeLiveWorker();

            }
        });

        this.WorkerIntellect.addActionListener(this::ControlWorkerIntellect);
        this.WarriorIntellect.addActionListener(this::ControlWarriorIntellect);
        this.dialogConsole.addActionListener(this::StartConsole);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        this.WarriorChance.addItemListener(this::ChanceWarrior);
        this.WorkerChance.addItemListener(this::ChanceWorker);
        this.WarriorTimeSpawn.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CheckTimeSpawnWarrior();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CheckTimeSpawnWarrior();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CheckTimeSpawnWarrior();
            }
        });
        this.WorkerTimeSpawn.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CheckTimeSpawnWorker();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CheckTimeSpawnWorker();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CheckTimeSpawnWorker();
            }
        });

        cfg = new Config(this);
        cfg.readConfig();

        this.currentObjects.addActionListener(this::startCurrentInfoDialog);
        this.priorityThreadWorker.addItemListener(this::PriorityWorker);
        this.priorityThreadWarrior.addItemListener(this::PriorityWarrior);
        this.Save.addActionListener(this::saveObjects);
        this.Download.addActionListener(this::downloadObjects);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                cfg.writeConfig();
            }
        });
    }
    private void saveObjects(ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);
        File file = fc.getSelectedFile();

        serializate.save(file);
    }

    private void downloadObjects(ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        File file = fc.getSelectedFile();
        stop(true);
        serializate.download(file);
    }

    private void StartConsole(ActionEvent actionEvent) {
        MyConsole t = new MyConsole(this);
        t.setBounds(500,500, 250,250);
        t.setVisible(true);
    }

    private void PriorityWarrior(ItemEvent actionEvent) {
        Habitat.runningWarriorAntThread.setPriority(Integer.parseInt(this.priorityThreadWarrior.getSelectedItem().toString()));
    }

    private void PriorityWorker(ItemEvent actionEvent) {
        Habitat.runningWorkerAntThread.setPriority(Integer.parseInt(this.priorityThreadWorker.getSelectedItem().toString()));

    }

    private void ControlWarriorIntellect(ActionEvent actionEvent) {
        if (WarriorIntellect.getText()=="Остановка воина"){
            WarriorIntellect.setText("Начать движение воина");
            //тут код с остановкой потока
            Habitat.runningWarriorAntThread.stopThread();

        } else {
            WarriorIntellect.setText("Остановка воина");
            //тут код с возобновлением потока
            if(!Habitat.runningWarriorAntThread.isAlive()) {
                Habitat.runningWarriorAntThread = new RunningAntThread(this.canvas, WarriorAnt.getStaticName(), WarriorAnt.speed);
                Habitat.runningWarriorAntThread.start();
            }

        }
    }

    private void ControlWorkerIntellect(ActionEvent actionEvent) {
        if (WorkerIntellect.getText()=="Остановка рабочего"){
            WorkerIntellect.setText("Начать движение рабочего");
            //тут код с остановкой потока
            Habitat.runningWorkerAntThread.stopThread();
        } else {
            WorkerIntellect.setText("Остановка рабочего");
            //тут код с возобновлением потока
            if(!Habitat.runningWorkerAntThread.isAlive()) {
                Habitat.runningWorkerAntThread = new RunningAntThread(this.canvas, WorkerAnt.getStaticName(), WorkerAnt.speed);
                Habitat.runningWorkerAntThread.start();
            }
        }
    }

    private void startCurrentInfoDialog(ActionEvent actionEvent) {
        currentObjects co = new currentObjects(this);
        co.setVisible(true);
    }

    private void TimeLiveWorker(){
        try{
            this.TimeLivingWorker = Integer.parseInt(this.TimeLiveWorker.getText().toString());
        }catch (Exception exception){
            this.TimeLivingWorker = 5;

        }
        System.out.println(this.TimeLivingWorker+"");
    }

    private void TimeLiveWarrior(){
        try{
            this.TimeLivingWarrior = Integer.parseInt(this.TimeLiveWarrior.getText().toString());
        }catch (Exception exception){
            this.TimeLivingWarrior = 5;

        }
        System.out.println(this.TimeLivingWarrior+"");
    }

    private void ChanceWarrior(ItemEvent e){
        this.P2 = Double.parseDouble(this.WarriorChance.getSelectedItem().toString())/100;
        System.out.println(this.P2+"");
    }

    private void CheckTimeSpawnWarrior(){
        try{
            this.N2 = Integer.parseInt(this.WarriorTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N2 = 1000;
        }

        System.out.println(this.N2+"");

    }


    private void ChanceWorker(ItemEvent e){
        this.P1 = Double.parseDouble(this.WorkerChance.getSelectedItem().toString())/100;
        System.out.println(this.P1+"");
    }

    private void CheckTimeSpawnWorker(){
        try{
            this.N1 = Integer.parseInt(this.WorkerTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N1 = 1000;
        }

        System.out.println(this.N1+"");

    }

    public void toggleTimer(boolean isShown){
        if(isShown){
            timerLabel.setText("Таймер: " + TimeSimulation);
            MyMenu.timerOptionShow.setSelected(true);
            timeVisible.setSelected(true);
        }
        else{
            timerLabel.setText("\u200E");
            MyMenu.timerOptionHide.setSelected(true);
            timeHidden.setSelected(true);
        }
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

    public void stop(boolean a){
        int quantityWorkers = WorkerAnt.quantity_ant;
        int quantityWarriors = WarriorAnt.quantity_ant;

        StopOperations();
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

    private void CheckTimeRespawn(int TimeLiving, TypeAnt typeAnt){

        if(AntExample.list.size()!=0) {
            boolean OBJFound = false;
            int i = 0;
            if (typeAnt == TypeAnt.Warrior && WarriorAnt.quantity_ant != 0) {//удалает первый элемент переданного типа и отрисовывает все остальные объекты и всех вспомагательных данных
                while (i < AntExample.list.size()) {
                    if(AntExample.list.get(i).getName() != WarriorAnt.getStaticName()){
                        OBJFound = true;
                        break;
                    }
                    i++;
                }

            }
            if(typeAnt == TypeAnt.Worker && WorkerAnt.quantity_ant != 0){
                while (i < AntExample.list.size()) {
                    if(AntExample.list.get(i).getName() != WorkerAnt.getStaticName()){
                        OBJFound = true;
                        break;
                    }
                    i++;
                }

            }
            if(OBJFound == true) {
                if(AntExample.list.get(i).getTimeBorn() + AntExample.list.get(i).getTimeLive() == AntExample.TimeSimulation) //каждый следующий элемент будет удален спустя время спавна
                    habitat.respawn(i, typeAnt);

            }
        }

    }

    public CustomMenu getMyMenu(){
        return MyMenu;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
