package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;

public class Config {
    private AntExample window;
    private Field[] fields;

    public Config(AntExample window){
        this.window = window;
        fields = window.getClass().getDeclaredFields();
    }


    public void writeConfig(){
        File file = new File("config.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write(((JTextField)getComponentByName("WarriorTimeSpawn")).getText() + ' ');
            writer.write(((JComboBox)getComponentByName("WarriorChance")).getSelectedItem().toString() + ' ');
            writer.write(((JTextField)getComponentByName("TimeLiveWarrior")).getText() + ' ');
            writer.write(((JTextField)getComponentByName("WorkerTimeSpawn")).getText() + ' ');
            writer.write(((JComboBox)getComponentByName("WorkerChance")).getSelectedItem().toString() + ' ');
            writer.write(((JTextField)getComponentByName("TimeLiveWorker")).getText() + ' ');

            int isTimerShown = ((JRadioButton)getComponentByName("timeVisible")).isSelected() ? 1 : 0;
            int isInfoShown = ((JCheckBox)getComponentByName("IsVisible")).isSelected() ? 1 : 0;
            writer.write(String.valueOf(isTimerShown) + ' ');
            writer.write(String.valueOf(isInfoShown) + ' ');

            String priorWarrior = String.valueOf(((JComboBox)getComponentByName("priorityThreadWarrior")).getSelectedIndex());
            String priorWorker = String.valueOf(((JComboBox)getComponentByName("priorityThreadWorker")).getSelectedIndex());
            writer.write(priorWarrior + ' ');
            writer.write(priorWorker + ' ');

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readConfig(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));

            String config = reader.readLine();
            String[] params = config.split(" ");

            ((JTextField)getComponentByName("WarriorTimeSpawn")).setText(params[0]);
            ((JComboBox)getComponentByName("WarriorChance")).setSelectedIndex(Integer.parseInt(params[1]) / 10 - 1);
            ((JTextField)getComponentByName("TimeLiveWarrior")).setText(params[2]);

            ((JTextField)getComponentByName("WorkerTimeSpawn")).setText(params[3]);
            ((JComboBox)getComponentByName("WorkerChance")).setSelectedIndex(Integer.parseInt(params[4]) / 10 - 1);
            ((JTextField)getComponentByName("TimeLiveWorker")).setText(params[5]);

            boolean b = params[6].equals("1");
            ((JRadioButton)getComponentByName("timeVisible")).setSelected(b);
            window.toggleTimer(b);

            b = params[7].equals("1");
            ((JCheckBox)getComponentByName("IsVisible")).setSelected(b);
            window.getMyMenu().infoOptionShow.setSelected(b);
            ((JComboBox)getComponentByName("priorityThreadWarrior")).setSelectedIndex(Integer.parseInt(params[8]));
            ((JComboBox)getComponentByName("priorityThreadWorker")).setSelectedIndex(Integer.parseInt(params[9]));

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object getComponentByName(String name){
        for(Field field : fields){
            field.setAccessible(true);
            String n = field.getName();
            if(name.equals(field.getName())){
                try {
                    return field.get(window);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
