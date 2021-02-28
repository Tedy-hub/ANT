package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;
import com.company.ant.ant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GenerateThread extends Thread {
    private JFrame window;
    Habitat habitat;
    private ArrayList<ant> antlist;
    private int heidght;
    private int width;
    ant ant;
    private double P;
    private int N;

    public GenerateThread(JFrame window,double P,int N, ant ant,ArrayList<ant> antlist) {
        this.window = window;
        this.P = P;
        this.N = N;
        this.antlist = antlist;
        this.ant = ant;
    }

    public void run() {
        //рисуем муравьев
        while (!this.isInterrupted()){
            try {
                this.sleep(N);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
            System.out.println("есть");
            if(Math.random()<P) {
                System.out.println(Math.random());
                if (ant.getName() == "Warrior Ant") {
                    ant = new WarriorAnt();
                } else {
                    ant = new WorkerAnt();
                }
                ant.draw(window);
                antlist.add(ant);
            }
        }
        }
    }






