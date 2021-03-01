package com.company.ant;

import java.awt.*;

public abstract class ant implements IBehaviour {
    private String name;
    private int Size;
    private int posX;
    private int posY;


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
    public void draw(Window window) {

    }
}
