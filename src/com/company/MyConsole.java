package com.company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class MyConsole extends JDialog {
    private AntExample jFrame;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;

    public MyConsole(AntExample parent) {
        super(parent, "console");
        jFrame = parent;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.textArea1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if((e.getKeyCode())==(KeyEvent.VK_ENTER)){
                    System.out.println("нажали");
                    checkText(textArea1.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
    }
    public void checkText(String str){
        String start = "старт";
        String stop = "стоп";

        String[] t = str.split("\n");

        if (start.equals(t[t.length-1].toLowerCase())){
            jFrame.start();
            textArea1.setText(str+"\n"+"началась симуляция");
        }
        if(stop.equals(t[t.length-1].toLowerCase())){
            jFrame.stop(true);
            textArea1.setText(str+"\n"+"симуляция закончилась");
        }
    }
}
