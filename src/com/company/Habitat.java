package com.company;

import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Habitat {

    static MyPanel window;
    static private int N1 = 1000;
    static private int N2 = 1000;
    static private double P1 = 1;
    static private double P2 = 0.1;
    static GenerateThread thread1;
    static GenerateThread thread2;
    static RunningAntThread runningWorkerAntThread;
    static RunningAntThread runningWarriorAntThread;
    private JPanel BottomPanel;

    Habitat(MyPanel window) {
        this.window = window;

    }

    public static void start() {
        try {
            WorkerAnt.worker_ant = ImageIO.read(new File("src/com/company/Picture/SpawnWorcker/B (16).png"));
        } catch (IOException e) {
        }
        try {
            WarriorAnt.warrior_ant = ImageIO.read(new File("src/com/company/Picture/SpawnWarrior/W (16).png"));
        } catch (IOException e) {
        }
        thread1 = new GenerateThread(window, P1, N1, WorkerAnt.getStaticName());
        thread2 = new GenerateThread(window, P2, N2, WarriorAnt.getStaticName());
        runningWorkerAntThread = new RunningAntThread(window, WorkerAnt.getStaticName(), WorkerAnt.speed);
        runningWarriorAntThread = new RunningAntThread(window, WarriorAnt.getStaticName(), WarriorAnt.speed);
        thread1.start();
        thread2.start();
        runningWorkerAntThread.start();
        runningWarriorAntThread.start();
    }

    public static void stop() {
        if (thread1 != null) thread1.stopThread();
        if (thread2 != null) thread2.stopThread();
        if (runningWarriorAntThread != null) runningWarriorAntThread.stopThread();
        if (runningWorkerAntThread != null) runningWorkerAntThread.stopThread();
        for (int i = 0; i < AntExample.list.size(); i++) {
            if (AntExample.list.get(i).getName() == "Warrior Ant")
                WarriorAnt.quantity_ant--;
            else {
                WorkerAnt.quantity_ant--;
            }
        }
        AntExample.list.clear();
    }



    public void respawn(int indexDead, TypeAnt typeAnt){

        if (typeAnt == TypeAnt.Warrior) WarriorAnt.quantity_ant--;

        else WorkerAnt.quantity_ant--;

        AntExample.idList.remove(AntExample.list.get(indexDead).getId());
        AntExample.BornList.remove(AntExample.list.get(indexDead).getId());
        AntExample.list.remove(indexDead);
        window.repaint();

    }

    public void ChangeProperties(double P1, int N1, double P2, int N2) {
        if (N1 == 0) N1 = 1000;
        if (N2 == 0) N2 = 1000;

        Habitat.N1 = N1;
        Habitat.P1 = P1;
        Habitat.N2 = N2;
        Habitat.P2 = P2;
    }
}
