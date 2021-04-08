package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class currentObjects extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea;

    public currentObjects(Frame parent) {
        super(parent, "fefe");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for (int i = 0; i < AntExample.list.size(); i++) {
            textArea.append(i + 1 + ". " + AntExample.list.get(i).getName() + " - ");

            textArea.append(AntExample.BornList.get(AntExample.list.get(i).getId()) + "\n");
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        currentObjects dialog = new currentObjects(new JFrame());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}