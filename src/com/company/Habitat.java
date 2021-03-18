package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Habitat {

    static private ArrayList<Ant> list = new ArrayList<>();
    static JPanel window;
    static private int N1 = 1000;
    static private int N2 = 1000;
    static private double P1 = 1;
    static private double P2 = 0.1;
    static GenerateThread thread1;
    static GenerateThread thread2;
    private JPanel BottomPanel;

    Habitat(JPanel window){
        this.window = window;



   }

    public static ArrayList<Ant> getList() {
        return list;
    }

    public static void setList(ArrayList<Ant> list) {
        Habitat.list = list;
    }

    public static void start() {
        try {//тип обязательная проверка на правильность пути
            WorkerAnt.worker_ant = ImageIO.read(new File("src/com/company/Picture/SpawnWorcker/B (16).png"));

            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        try {//тип обязательная проверка на правильность пути
            WarriorAnt.warrior_ant =ImageIO.read(new File("src/com/company/Picture/SpawnWarrior/W (16).png"));

            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("нет");
        }
        thread1 = new GenerateThread(window, P1, N1, WorkerAnt.getStaticName(), list);
        thread2= new GenerateThread(window, P2, N2, WarriorAnt.getStaticName(), list);
        thread1.start();
        thread2.start();
    }

    public static void stop(){
        if(thread1 != null) thread1.interrupt();
        if(thread2 != null) thread2.interrupt();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName() == "Warrior Ant")
                WarriorAnt.quantity_ant--;
            else{
                WorkerAnt.quantity_ant--;
            }

        }
        list.clear();
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
            i++;
        }
    }

    public void ChangeProperties(double P1, int N1, double P2, int N2){
        if(N1 == 0)N1 = 1000;
        if(N2 == 0)N2 = 1000;

        Habitat.N1 = N1;
        Habitat.P1 = P1;
        Habitat.N2 = N2;
        Habitat.P2 = P2;
    }
}
