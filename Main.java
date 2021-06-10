//package MADTask1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.BorderLayout;

//String question = "Which is a fire type Pokemon?";
//String answer = "Pansear";
class Main
{
    JFrame mainFrame = new JFrame("Main Menu");
    static JPanel mainPanel = new JPanel();
    //Heading label on a Heading Panel
    JPanel headPanel = new JPanel();
    JLabel headLabel = new JLabel("Choose a quiz topic");
    //topic area label amd panel
    JPanel topicPanel = new JPanel();
    JLabel topicLabel = new JLabel("Topic area");
    JButton startQuiz = new JButton("Start Quiz");

    String question, answer;
    String[] op;
    JButton[] buttonList = new JButton[4];
    JFrame questionFrame = new JFrame();    
    JLabel questionLabel = new JLabel();
    JLabel answerLabel = new JLabel();
    static JPanel questionPanel = new JPanel();
    JButton back = new JButton("Back");
    boolean correctAnswer = false;
    public void menu()
    {
        String[] op = {"Pansear", "Chespin", "Panpour", "Pansage"};
        //headPanel settings
        headPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        //headPanel.setLayout();
        headPanel.setBackground(Color.gray);
        headPanel.add(headLabel);

        //topic Panel
        topicPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        topicPanel.setLayout(new BoxLayout(topicPanel, BoxLayout.Y_AXIS));
        topicPanel.setBackground(Color.green);
        topicPanel.add(topicLabel);
        topicPanel.add(startQuiz);
        boolean ans = false;
        startQuiz.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                question("Fire type?", op, "Pansear");
                mainPanel.setVisible(false);
                questionPanel.setVisible(true);
            }
        });
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                questionPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });

        //Question Panel
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(questionLabel);
        for (int i = 0;i < 4;i++)
        {
            buttonList[i] = new JButton();
            buttonList[i].addActionListener(optionButton);
            questionPanel.add(buttonList[i]);
        }
        questionPanel.add(answerLabel);
        questionPanel.add(back);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        questionPanel.setBackground(Color.WHITE);
        this.question("Fire type?", op, "Pansear");
        questionPanel.setVisible(false);

        //Top Layout
        GridBagConstraints top = new GridBagConstraints();
        top.fill = GridBagConstraints.HORIZONTAL;
        top.gridx = 1;
        top.gridy = 0;
        //Center layout
        GridBagConstraints topic = new GridBagConstraints();
        topic.fill = GridBagConstraints.HORIZONTAL;
        topic.gridx = 1;
        topic.gridy = 1;

        //mainPanel settings
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(headPanel, top);
        mainPanel.add(topicPanel, topic);

        //Frame settings
        mainFrame.add(questionPanel, BorderLayout.PAGE_START);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.setVisible(true);
    }

    public void question(String question, String[] op, String answer)
    {
        this.question = question;
        this.op = op;
        this.answer = answer;
        questionLabel.setText(question);
        List<String> options = Arrays.asList(op);
        Collections.shuffle(options);
        for (int i = 0;i < op.length;i++)
        {
            buttonList[i].setText(options.get(i));
        }
    }
    public static void main(String[] args)
    {
        Main m = new Main();
        m.menu();
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
                        answerLabel.setText("Correct!");
                        correctAnswer = true;
                        break;
                    }
                    else
                    {
                        buttonList[i].setForeground(Color.red);
                        answerLabel.setText("");
                        correctAnswer = false;
                    }
                }
                else
                {
                    buttonList[i].setForeground(Color.black);
                }
            }
        }
    };
}