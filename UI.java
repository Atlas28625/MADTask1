package MADTask1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Forget about icons for now
    ImageIcon tick = new ImageIcon("tick.svg");
    System.out.println(tick);
    label2.setIcon(new ImageIcon(getClass().getResource("tick.svg")));
*/
//Changes

public class UI
{
    String question, answer;
    String[] op;
    JButton[] buttonList;
    JFrame questionFrame = new JFrame();    
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JPanel panel = new JPanel();
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton back = new JButton("Back");
    boolean correctAnswer = false;
    public void question(String question, String[] op, String answer)
    {
        this.question = question;
        this.op = op;
        this.answer = answer;
        this.buttonList = new JButton[op.length];
        label1.setText(question);
        panel.add(label1);
        List<String> options = Arrays.asList(op);
        Collections.shuffle(options);
        for (int i = 0;i < op.length;i++)
        {
            buttonList[i] = new JButton(options.get(i));
            buttonList[i].addActionListener(optionButton);
            panel.add(buttonList[i]);
        }
        
        panel.add(label2);
        panel.add(back);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        questionFrame.add(panel, BorderLayout.CENTER);
        questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionFrame.pack();
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setFocusable(true);
        //questionFrame.setVisible(true);
    }


        ActionListener optionButton = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Object source = e.getSource();
                for (int i = 0;i < op.length;i++)
                {
                    if(source == buttonList[i])
                    {
                        if(answer.equals(buttonList[i].getText()))
                        {
                            buttonList[i].setForeground(Color.green);
                            label2.setText("Correct!");
                            correctAnswer = true;
                            break;
                        }
                        else
                        {
                            buttonList[i].setForeground(Color.red);
                            label2.setText("");
                        }
                    }
                    else
                    {
                        buttonList[i].setForeground(Color.black);
                    }
                }
            }
        };

/*    public static void main(String[] args)
    {
        String question = "Which is a fire type Pokemon?";
        String[] op = {"Pansear", "Chespin", "Panpour", "Pansage"};
        String answer = "Pansear";
        Template t = new Template(question, op, answer);
    } */
}
