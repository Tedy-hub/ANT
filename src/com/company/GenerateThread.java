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
    private int P;
    private int N;

    public GenerateThread(JFrame window,int P,int N, ant ant,ArrayList<ant> antlist) {
        this.window = window;
        this.P = P;
        this.N = N;
        this.antlist = antlist;
        this.ant = ant;
        //window.setSize(h,w);
        //habitat = new Habitat(window,width,heidght);
    }


    private static int seconds = 0;

    public void run() {
        //рисуем муравьев

                //}
        }
    }


    // перерисовка всех кадров займет больше времени
    /*public void RespownAllAnts() {
        window.getContentPane().repaint();
        int i = 0;
        while (i < warriorAnts.size() || i < workerAnts.size()) {
            warriorAnts.get(i).Respawn(window);
            workerAnts.get(i).Respawn(window);
            i++;


        }


    }*/



