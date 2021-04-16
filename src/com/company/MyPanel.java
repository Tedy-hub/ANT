package com.company;


import com.company.ant.Ant;
import com.company.ant.WarriorAnt;
import com.company.ant.WorkerAnt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    private Image dbimg;
    private Graphics dbg;
    private Habitat habitat;
    private TypeAnt typeAnt;

    MyPanel() {
        super();
        setVisible(true);
        setDoubleBuffered(true);
    }

    public void setHabitat(Habitat _habitat) {
        habitat = _habitat;
    }

    public void setTypeAnt(TypeAnt _ant) {
        typeAnt = _ant;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        Ellipse2D circle = new Ellipse2D.Float(10, 10, 20, 20);
        g2.fill(circle);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        while (i < AntExample.list.size()) {
            Ant ant = AntExample.list.get(i);
            if (AntExample.list.get(i).getName() == WarriorAnt.getStaticName()) {
                g.drawImage(WarriorAnt.warrior_ant, ant.getPosX(), ant.getPosY(),
                        ant.getSize() * 100, ant.getSize() * 100, this);
            } else {
                g.drawImage(WorkerAnt.worker_ant, AntExample.list.get(i).getPosX(), AntExample.list.get(i).getPosY(),
                        AntExample.list.get(i).getSize() * 65, AntExample.list.get(i).getSize() * 100, this);
            }
            i++;
        }
    }

}