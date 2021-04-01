package com.company;


import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyPanel extends JPanel{

    private Image dbimg;
    private Graphics dbg;
    private Habitat habitat;
    private TypeAnt typeAnt;

    MyPanel(){
        super();
        setVisible(true);
        setDoubleBuffered(true);
    }

    public void setHabitat(Habitat _habitat){
        habitat = _habitat;
    }

    public void setTypeAnt(TypeAnt _ant){
        typeAnt = _ant;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
       // dbimg = createImage(getWidth(), getHeight());
        //dbg = dbimg.getGraphics();
        //paintComponent(dbg);
        //getGraphics().drawImage(dbimg, 0, 0, this);
        //dbg.dispose();
  }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i =0;
        while(i < AntExample.list.size()){
            if(AntExample.list.get(i).getName() == WarriorAnt.getStaticName()){
                g.drawImage(WarriorAnt.warrior_ant, AntExample.list.get(i).getPosX(),AntExample.list.get(i).getPosY(),
                        AntExample.list.get(i).getSize()*100, AntExample.list.get(i).getSize()*100, this);
            }
            else{
                g.drawImage(WorkerAnt.worker_ant, AntExample.list.get(i).getPosX(),AntExample.list.get(i).getPosY(),
                        AntExample.list.get(i).getSize()*65, AntExample.list.get(i).getSize()*100, this);
            }
            i++;
        }


//        if(ant == null) return;
//        System.out.println(ant.getName());
//        if(ant.getName() == WarriorAnt.getStaticName()){
//            g.drawImage(WarriorAnt.warrior_ant, ant.getPosX(), ant.getPosY(), ant.getSize()*100, ant.getSize()*100, this);
//        }
//        if(ant.getName() == WorkerAnt.getStaticName()){
//            g.drawImage(WorkerAnt.worker_ant, ant.getPosX(), ant.getPosY(), ant.getSize()*65, ant.getSize()*100, this);
//
//        }

        //habitat.respawn(typeAnt, g);
        //repaint();
    }

}