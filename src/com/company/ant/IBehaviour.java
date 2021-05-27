package com.company.ant;

import com.company.AntExample;
import com.company.MyPanel;

import javax.swing.*;
import java.awt.*;

public interface IBehaviour {

    int getId();

    int getSize();

    int getTimeLive();

    int getTimeBorn();

    void setTimeLive(int TimeLive);

    void setTimeBorn(int TimeBorn);

    String getName();

    int getPosX();

    int getPosY();

    void setId(int id);

    void setName(String name);

    void setSize(int size);

    void setPosX(int x);

    void setPosY(int y);

    void draw(JPanel window);

    void run(int speed);
   // public void RespawnAnt(MyPanel window);

}
