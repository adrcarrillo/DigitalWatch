package com.arn.digitalwatch;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

public class DigitalWatch implements Runnable {

    //JFrame f;
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";
    JButton b;

    DigitalWatch(){

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF");
        }

        Thread t = new Thread(this);
        JFrame f = new JFrame();
        t.start();
        b = new JButton();
        b.setBounds(45,45,100,50);

        f.add(b);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200,200);
        f.setLayout(null);
        f.setVisible(true);
    }

    @Override
    public void run() {
        try {
            while(true) {
                Calendar cal = Calendar.getInstance();
                hours = cal.get(Calendar.HOUR_OF_DAY);
                if (hours > 12) hours -= 12;
                minutes = cal.get(Calendar.MINUTE);
                seconds = cal.get(Calendar.SECOND);

                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                Date date = cal.getTime();
                timeString = formatter.format(date);
                printTime();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
        
    }

    public void printTime() {
        b.setText(timeString);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new DigitalWatch()); //Error runtime
        new DigitalWatch();
    }
    

}