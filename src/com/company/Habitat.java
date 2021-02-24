package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.ant.ant;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Habitat {

    static private ArrayList<WarriorAnt> warriorAnts = new ArrayList<>();
    static private ArrayList<ant> workerAnts = new ArrayList<>();
    static JFrame window;
    static private int N1 = 100;
    static private int N2 = 100;
    static private int P1 = 1;
    static private int P2 = 1;
    static WarriorAnt warriorAnt = new WarriorAnt();
    static WorkerAnt workerAnt = new WorkerAnt();
    static GenerateThread thread1;
    static GenerateThread thread2;


    Habitat(AntExample window, int width, int height){
        this.window = window;
        //window.setSize(width, height);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setVisible(true);
   }

    public static ArrayList<WarriorAnt> getWarriorAnts() {
        return warriorAnts;
    }

    public static void setWarriorAnts(ArrayList<WarriorAnt> warriorAnts) {
        Habitat.warriorAnts = warriorAnts;
    }

    /*public static ArrayList<WorkerAnt> getWorkerAnts() {
        return workerAnts;
    }*/

    /*public static void setWorkerAnts(ArrayList<WorkerAnt> workerAnts) {
        Habitat.workerAnts = workerAnts;
    }*/

    public static void start() {
        int i = 1;
        try {//тип обязательная проверка на правильность пути
            while (i < 17) {
                Image img = ImageIO.read(new File("src/com/company/Picture/SpawnWorcker/B (" + i + ").png"));
                WorkerAnt.worker_ant.add(img);
                i++;
            }
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        i = 1;
        try {//тип обязательная проверка на правильность пути
            while (i < 17) {
                Image img = ImageIO.read(new File("src/com/company/Picture/SpawnWarrior/W (" + i + ").png"));
                WarriorAnt.warrior_ant.add(img);
                i++;
            }
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        //thread1 = new GenerateThread(window, 1, 1000,workerAnt,workerAnts);
        //thread2= new GenerateThread(window, 1, 1000,warriorAnt,workerAnts);
        //thread1.start();
        //thread2.start();

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("есть");
                //if(Math.random()<P) {
                warriorAnt = new WarriorAnt();
                warriorAnt.draw(window);
                //если мы его рисуем
                //то добавляем
                warriorAnts.add(warriorAnt);
            }
        });
        timer.start();


    }

    public static void stop(){
        //thread1.interrupt();
        //thread2.interrupt();
    }

    /*@Override
    public void loadAnts() {

        //рисуем воина



        //рисуем работягу

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("есть");
            if(Math.random()<P1) {
                warriorAnt.draw(window);
                //если мы его рисуем
                //то добавляем
                warriorAnts.add(warriorAnt);
            if(Math.random()<P2){
                workerAnt.draw(window);
                //если мы его рисуем
                //то добавляем
                workerAnts.add(workerAnt);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/
}
