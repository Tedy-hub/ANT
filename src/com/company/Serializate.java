package com.company;

import com.company.ant.Ant;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Serializate implements Serializable {
    AntExample antExample;

    public Serializate(AntExample antExample) {
        this.antExample = antExample;
    }

    public void download(File file) {
        Vector<Ant> antlist;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            AntExample.list = (Vector<Ant>) ois.readObject();
            System.out.println("прочитали");
            antExample.start();
            Toast toast = new Toast(antExample,"считали из файла",2000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void save(File file) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(AntExample.list);
            System.out.println("записали");
            Toast toast = new Toast(antExample,"все сохранили",2000);
            //toast.showToast(antExample,"все сохранили",p,1000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}