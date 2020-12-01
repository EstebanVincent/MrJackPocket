package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {
    private JButton button1;
    private JPanel panel1;

    public Board() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"fck java");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
