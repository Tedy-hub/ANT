package com.company.ant;

import java.awt.*;

public interface IBehaviour {
    int getSize();

    String getName();

    int getSpawnTime();

    int getSpawnChance();

    int getPosX();

    int getPosY();

    void setName(String name);

    void setSize(int size);

    void setSpawnTime(int time);

    void setSpawnChance(int chance);

    void setPosX(int x);

    void setPosY(int y);

    void draw(Window window);

    void RespawnAnt(Window window);

}
