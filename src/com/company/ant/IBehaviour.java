package com.company.ant;

import com.company.AntExample;

import javax.swing.*;
import java.awt.*;

public interface IBehaviour {
    int getSize();

    String getName();

    int getPosX();

    int getPosY();

    void setName(String name);

    void setSize(int size);

    void setPosX(int x);

    void setPosY(int y);

    void draw(JPanel window);

    void RespawnAnt(JPanel window);

}
