package com.company;

import javax.swing.*;

public class CustomMenu {
    JMenuBar menuBar;
    JMenu menu;

    JMenu menuInner1;
    JMenu menuInner2;
    JMenu menuInner3;

    JMenuItem simOptionStart;
    JMenuItem simOptionStop;

    JCheckBoxMenuItem infoOptionShow;

    JRadioButtonMenuItem timerOptionShow;
    JRadioButtonMenuItem timerOptionHide;

    public CustomMenu(){

        menuBar = new JMenuBar();
        menu = new JMenu("Меню");

        menuInner1 = new JMenu("Симуляция");
        menuInner2 = new JMenu("Информация");
        menuInner3 = new JMenu("Таймер");

        simOptionStart = new JMenuItem("Запустить");
        simOptionStop = new JMenuItem("Остановить");

        infoOptionShow = new JCheckBoxMenuItem("Показывать информацию");

        timerOptionShow = new JRadioButtonMenuItem("Показать");
        timerOptionHide = new JRadioButtonMenuItem("Скрыть");
        ButtonGroup bg = new ButtonGroup();
        bg.add(timerOptionShow);
        bg.add(timerOptionHide);

        menuInner1.add(simOptionStart);
        menuInner1.add(simOptionStop);

        menuInner2.add(infoOptionShow);

        menuInner3.add(timerOptionShow);
        menuInner3.add(timerOptionHide);

        menu.add(menuInner1);
        menu.add(menuInner2);
        menu.add(menuInner3);

        menuBar.add(menu);
    }
}
