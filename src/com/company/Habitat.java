package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.ant.ant;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Habitat {

    //static private ArrayList<WarriorAnt> warriorAnts = new ArrayList<>();
    static private ArrayList<ant> list = new ArrayList<>();
    static JFrame window;
    static private int N1 = 1000;
    static private int N2 = 1000;
    static private double P1 = 1;
    static private double P2 = 0.1;
    static WarriorAnt warriorAnt = new WarriorAnt();
    static WorkerAnt workerAnt = new WorkerAnt();
    static GenerateThread thread1;
    static GenerateThread thread2;


    Habitat(AntExample window, int width, int height){
        this.window = window;
   }

    public static ArrayList<ant> getList() {
        return list;
    }

    public static void setList(ArrayList<ant> list) {
        Habitat.list = list;
    }



    public static void start() {
        int i = 1;
        try {//тип обязательная проверка на правильность пути
            WorkerAnt.worker_ant = ImageIO.read(new File("src/com/company/Picture/SpawnWorcker/B (16).png"));

            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        i = 1;
        try {//тип обязательная проверка на правильность пути
            WarriorAnt.warrior_ant =ImageIO.read(new File("src/com/company/Picture/SpawnWarrior/W (16).png"));

            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        thread1 = new GenerateThread(window, P1, N1, workerAnt, list);
        thread2= new GenerateThread(window, P2, N2,warriorAnt, list);
        thread1.start();
        thread2.start();
    }

    public static void stop(){
        thread1.interrupt();
        thread2.interrupt();
    }
    public void respawn(){
        try {
            thread1.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i<list.size()){
            list.get(i).RespawnAnt(window);
        }
    }
}
