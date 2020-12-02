package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        //MrJackPocket a = new MrJackPocket();
        //a.play();

        //Action ac = new Action();
        //System.out.println(ac.positionDetectiveList[1][0]);
        Main m = new Main();
        m.setUpSwing();




    }

    public void setUpSwing(){
        JFrame frame = new JFrame("Start");
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);




        JButton play = new JButton("Start");
        play.setSize(100, 100);
        play.isDefaultButton();
        play.setLayout(null);
        play.setVisible(true);


        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setSize(400, 400);
        p.add(play,BorderLayout.CENTER);
        frame.setContentPane(p);

        frame.pack();

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MrJackPocket a = new MrJackPocket();
                a.play();
            }
        });
    }

}
