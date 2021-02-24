package com.company.ant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WarriorAnt extends ant{

   static private int quantity_ant = 0;//для отслеживания кол-ва объектов
   //static public Image warrior_ant;
    static public ArrayList<Image> warrior_ant = new ArrayList<>();
    public int NumberPicture = 1;

   public WarriorAnt(){
       setName("Warrior Ant");

       // случайный размер от 2 до 4 ед.
       Random rand = new Random();
       int size = rand.nextInt(2);
       setSize(size + 2);

       setSpawnTime(1000);
       setSpawnChance(100); // 70
       quantity_ant++;
   }

    public int GetQuantityAntThisGroup(){
        return quantity_ant;
    }

    @Override
    public void draw(Window window) {

        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 100);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 100);
        this.setPosX(x);
        this.setPosY(y);

        JPanel panel = new JPanel();


        Frame frame = new JFrame();
        frame.setSize(8, 4);

        JLabel jLabel = new JLabel();
        jLabel.setSize(40,40);
        jLabel.setBounds(x,y,30,30);
        Image image = Toolkit.getDefaultToolkit().createImage("src/com/company/Picture/cat.gif");

        ImageIcon imageIcon = new ImageIcon(image);

        imageIcon.setImageObserver(jLabel);
        jLabel.setIcon(imageIcon);

        window.add(jLabel);
        window.setVisible(true);


        //Timer timer = new Timer(58, new ActionListener() {
         //   @Override
         //   public void actionPerformed(ActionEvent e) {
          //      if(NumberPicture<16)
          //          window.getGraphics().drawImage(warrior_ant.get(NumberPicture), x, y, getSize() * 65, getSize() * 100, null);
          //      else return;

           //     NumberPicture++;

            //}
        //});
        //timer.start();
        //System.out.println("Warrior X: " + x + " Y: " + y + " Quantity: " + quantity_ant);
        //window.getGraphics().drawImage(, x, y, getSize() * 65, getSize() * 100, null);
    }
    public void Respawn(Window window){

        window.getGraphics().drawImage(warrior_ant.get(15), this.getPosX(), this.getPosY(), getSize() * 65, getSize() * 100, null);

    }
}
