package com.company.ant;

import com.company.AntExample;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public abstract class Ant implements IBehaviour, Serializable {
    private int id;
    private String name;
    private int Size;
    private int posX;
    private int posY;
    private int TimeLive;
    static int t;
    private int TimeBorn;
    private int positionBornX;
    private int positionBornY;
    //состояние муравья

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
    public void setTimeLive(int TimeLive) { this.TimeLive = TimeLive; }

    @Override
    public void setTimeBorn(int TimeBorn) { this.TimeBorn = TimeBorn; }

    @Override
    public void draw(JPanel window) { }

    public abstract Image getImg();

    public int getPositionBornX() {
        return positionBornX;
    }

    public int getPositionBornY() {
        return positionBornY;
    }

    public void setPositionBornY(int positionBornY) {
        this.positionBornY = positionBornY;
    }

    public void setPositionBornX(int positionBornX) {
        this.positionBornX = positionBornX;
    }
}
