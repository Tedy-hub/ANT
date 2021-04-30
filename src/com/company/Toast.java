package com.company;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Toast {

    private final JFrame component;
    private Point   location;
    private final String  message;
    private long duration; //in millisecond

    public Toast(JFrame comp, String msg, long forDuration) {
        this.component = comp;
        this.message = msg;
        this.duration = forDuration;

        if(this.component != null)
        {

            if(this.location == null)
            {
                this.location = component.getLocationOnScreen();
            }

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    Popup view = null;
                    try
                    {
                        Label tip = new Label(message);
                        tip.setForeground(Color.BLACK);
                        tip.setBackground(Color.WHITE);
                        view = PopupFactory.getSharedInstance().getPopup(component, tip , location.x + component.getWidth()/2, location.y + component.getHeight() - 100);
                        view.show();
                        Thread.sleep(duration);
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(Toast.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        view.hide();
                    }
                }
            }).start();
        }
    }



    /*public static void showToast(JFrame component, String message)
    {
        new Toast(component, null, message, 2000/*Default 2 Sec);
    }*/

    /*public static void showToast(JFrame component, String message, Point location, long forDuration)
    {
        new Toast(component, location, message, forDuration);
    }*/
}
