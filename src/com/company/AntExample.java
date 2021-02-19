package com.company;

import javax.swing.*;

public class AntExample extends JFrame {
    private JPanel mainPanel;
    private JTextField ANTTextField;

    public AntExample(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,1000);
        this.setContentPane(mainPanel);
        this.pack();
    }
}
