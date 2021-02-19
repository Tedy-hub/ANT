package com.company;

import java.awt.Dimension;

import javax.swing.*;

public class ControlFrame {

    private JButton button1;
    private JPanel rootPanel;

    /*public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test label");
        frame.getContentPane().add(label);

        frame.setPreferredSize(new Dimension(200, 100));

        frame.pack();
        frame.setVisible(true);
    }*/

    public void CreateGui(){
       JFrame Window = new JFrame("ANT-HOME");
       Window.setVisible(true);
       Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Window.setSize(1280,1024);

    }


}
