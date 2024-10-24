
package com.mycompany.xyz;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NumberPuzzle extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    int count = 0;
    JLabel counterLabel;

    public NumberPuzzle() {
        super(" Number Puzzle");

        for (int i = 0; i < 8; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
        }
        buttons[8] = new JButton(" ");

        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (JButton button : buttons) {
            panel.add(button);
            button.addActionListener(this);
        }
        add(panel, BorderLayout.CENTER);

        counterLabel = new JLabel("Moves: 0");
        add(counterLabel, BorderLayout.SOUTH);

        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int index = getButtonIndex(clickedButton);

        if (index % 3 != 0 && buttons[index - 1].getText().equals(" ")) {
            swapButtons(index, index - 1);
        }
        if (index % 3 != 2 && buttons[index + 1].getText().equals(" ")) {
            swapButtons(index, index + 1);
        }
        if (index / 3 != 0 && buttons[index - 3].getText().equals(" ")) {
            swapButtons(index, index - 3);
        }
        if (index / 3 != 2 && buttons[index + 3].getText().equals(" ")) {
            swapButtons(index, index + 3);
        }

        counterLabel.setText("Moves: " + ++count);

        if (isSolved()) {
            JOptionPane.showMessageDialog(this, "You won!");
        }
    }

    private int getButtonIndex(JButton button) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == button) {
                return i;
            }
        }
        return -1;
    }

    private void swapButtons(int index1, int index2) {
        String temp = buttons[index1].getText();
        buttons[index1].setText(buttons[index2].getText());
        buttons[index2].setText(temp);
    }

    private boolean isSolved() {
        for (int i = 0; i < 8; i++) {
            if (!buttons[i].getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new NumberPuzzle();
    }
}