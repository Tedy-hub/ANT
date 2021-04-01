package com.company.ant;

import com.company.AntExample;

import javax.swing.*;
import java.awt.*;

public abstract class Ant implements IBehaviour {
    private int id;
    private String name;
    private int Size;
    private int posX;
    private int posY;
    private int TimeLive;
    private int TimeBorn;

    @Override
    public int getId(){ return id; }

    @Override
    public int getTimeLive(){return TimeLive;}

    @Override
    public int getTimeBorn(){return TimeBorn;}

    @Override
    public int getSize() {
        return Size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setId(int id){this.id = id;}

    @Override
    public void setSize(int size) {
        Size = size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPosX(int x) {
        posX = x;
    }

    @Override
    public void setPosY(int y) {
        posY = y;
    }

    @Override
    public void SetTimeLive(int TimeLive) { this.TimeLive = TimeLive; }

    @Override
    public void SetTimeBorn(int TimeBorn) { this.TimeBorn = TimeBorn; }

    @Override
    public void draw(JPanel window) { }
}
