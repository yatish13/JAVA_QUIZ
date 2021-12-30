package com.java.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

class McqQuiz extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton btnNext, btnReview;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];

    // create jFrame with radioButton and JButton
    McqQuiz(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }
        btnNext = new JButton("Next");
        btnReview = new JButton("Review");
        btnNext.addActionListener(this);
        btnReview.addActionListener(this);
        add(btnNext);
        add(btnReview);
        set();
        label.setBounds(30, 40, 450, 20);
        // radioButton[0].setBounds(50, 80, 200, 20);
        radioButton[0].setBounds(50, 80, 450, 20);
        radioButton[1].setBounds(50, 110, 200, 20);
        radioButton[2].setBounds(50, 140, 200, 20);
        radioButton[3].setBounds(50, 170, 200, 20);
        btnNext.setBounds(100, 240, 100, 30);
        btnReview.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    // handle all actions based on event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 4) {
                btnNext.setEnabled(false);
                btnReview.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Review")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 4)
                btnReview.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }

        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "correct answers= " + count);
            System.exit(0);
        }
    }

    // SET Questions with options
    void set() {
        radioButton[4].setSelected(true);
        if (current == 0) {
            label.setText("Que1: 121 Divided by 11 is ");
            radioButton[0].setText("11");
            radioButton[1].setText("10");
            radioButton[2].setText("19");
            radioButton[3].setText("18");
        }
        if (current == 1) {
            label.setText("Que2: 60 Times of 8 Equals to");
            radioButton[0].setText("480");
            radioButton[1].setText("300");
            radioButton[2].setText("250");
            radioButton[3].setText("400");
        }
        if (current == 2) {
            label.setText("Que3: Find the Missing Term in Multiples of 6 : 6, 12, 18, 24, _, 36, 42, _ 54, 60.");
            radioButton[0].setText("32, 45");
            radioButton[1].setText("30, 48");
            radioButton[2].setText("24, 40");
            radioButton[3].setText("25, 49");
        }
        if (current == 3) {
            label.setText("Que4: What is the Next Prime Number after 7 ?");
            radioButton[0].setText("13");
            radioButton[1].setText("12");
            radioButton[2].setText("14");
            radioButton[3].setText("11");
        }
        if (current == 4) {
            label.setText("Que5: The Product of 131 x 0 x 300 x 4");
            radioButton[0].setText("11");
            radioButton[1].setText("0");
            radioButton[2].setText("46");
            radioButton[3].setText("45");
        }
        label.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            radioButton[j].setBounds(50, 80 + i, 200, 20);
    }

    // declare right answers.
    boolean check() {
        if (current == 0)
            return (radioButton[0].isSelected());
        if (current == 1)
            return (radioButton[0].isSelected());
        if (current == 2)
            return (radioButton[1].isSelected());
        if (current == 3)
            return (radioButton[3].isSelected());
        if (current == 4)
            return (radioButton[1].isSelected());
        return false;
    }

    public static void main(String[] args) {
        new McqQuiz("MCQ Quiz App");
    }
}