package com.company;

import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Habitat extends JFrame{
    private JFrame window;
    static private ArrayList<WarriorAnt> warriorAnts = new ArrayList<>();
    static private ArrayList<WorkerAnt> workerAnts = new ArrayList<>();


    public Habitat(JFrame _window, int width, int height){
        _window.setSize(width, height);
        window = _window;
        int i = 1;
        try {//тип обязательная проверка на правильность пути
            while(i < 17) {
                Image img = ImageIO.read(new File("src/com/company/Picture/SpawnWorcker/B (" + i + ").png"));
                WorkerAnt.worker_ant.add(img);
                i++;
            }
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}
        i=1;
        try {//тип обязательная проверка на правильность пути
            while(i < 17) {
                Image img = ImageIO.read(new File("src/com/company/Picture/SpawnWarrior/W (" + i + ").png"));
                WarriorAnt.warrior_ant.add(img);
                i++;
            }
            System.out.println("Изображение считалось");
        }catch (IOException e) { System.out.println("нет");}

    }


    private static int seconds = 0;
    public void spawnAnt(){
            // логика полсе нажатия на кнопку
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //показывает картинку
                    seconds++;
                    WarriorAnt warriorAnt = new WarriorAnt();
                    if(seconds % (warriorAnt.getSpawnTime() / 1000) == 0){
                        int shouldSpawn = ThreadLocalRandom.current().nextInt(1, 101);
                        if (shouldSpawn < warriorAnt.getSpawnChance()) {
                            warriorAnts.add(warriorAnt);
                            warriorAnt.draw(window);
                        }
                        seconds = 0;
                    }

                    int shouldSpawn = ThreadLocalRandom.current().nextInt(1, 101);
                    WorkerAnt workerAnt = new WorkerAnt();
                    if (shouldSpawn < workerAnt.getSpawnChance()) {
                        workerAnts.add(workerAnt);
                        workerAnt.draw(window);
                        //System.out.println("Действие проходит");
                    }
                }
            });
            timer.start();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }
    // перерисовка всех кадров займет больше времени
    public void RespownAllAnts(){
        window.getContentPane().repaint();
        int i = 0;
        while(i < warriorAnts.size() || i < workerAnts.size()){
            warriorAnts.get(i).Respawn(window);
            workerAnts.get(i).Respawn(window);
            i++;


        }


    }





}
