import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.BorderLayout;

class Main
{
    static JFrame mainFrame = new JFrame("Main Menu");
    JPanel mainPanel = new JPanel();
    
    //Question Frame
    static JFrame questionFrame = new JFrame("Questions");
    JPanel questionPanel = new JPanel();
    
    //Heading label on a Heading Panel
    JPanel headPanel = new JPanel();
    JLabel headLabel = new JLabel("Choose a quiz topic");
    
    //topic area label amd panel
    JPanel topicPanel = new JPanel();
    JLabel topicLabel = new JLabel("Topic area");
    JButton physics = new JButton("physics");
    JButton math = new JButton("math");

    //Questions class variable
    Questions qClass = new Questions();
    
    //scorePanel
    static JFrame scoreFrame = new JFrame("Scores");
    JPanel scorePanel = new JPanel();
    int score = 0;
    JButton exitJButton = new JButton("Exit");
    JButton retryJButton = new JButton("Retry");

    JLabel scoreFrameLabel = new JLabel();
    JLabel scoreLabel = new JLabel("Score : " + String.valueOf(score));

    String question, answer;
    String[] op;
    JButton[] buttonList = new JButton[4];
    JLabel questionLabel = new JLabel();
    JLabel answerLabel = new JLabel();
    JButton back = new JButton("Back");
    public void menu()
    {
        qClass.setTopic(qClass.topicList[0]);
        op = qClass.getOptions();
        //headPanel settings
        headPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //headPanel.setLayout();
        headPanel.setBackground(Color.WHITE);
        headPanel.add(headLabel);
        
        //start quiz button
        physics.addActionListener(startQuiz);
        physics.setActionCommand(physics.getText());
        math.addActionListener(startQuiz);
        math.setActionCommand(math.getText());
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                questionFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });

        //topic Panel
        topicPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        topicPanel.setLayout(new BoxLayout(topicPanel, BoxLayout.Y_AXIS));
        topicPanel.setBackground(Color.WHITE);
        topicPanel.add(topicLabel);
        topicPanel.add(math);
        topicPanel.add(physics);
        
        //scorePanel to show scores and exit or retry
        scorePanel.add(scoreFrameLabel);
        scorePanel.add(exitJButton);
        scorePanel.add(retryJButton);
        scorePanel.setBorder(BorderFactory.createEmptyBorder(70, 20, 70, 20));
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setVisible(true);

        //Question Panel
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.add(scoreLabel);
        questionPanel.add(questionLabel);
        for (int i = 0;i < 4;i++)
        {
            buttonList[i] = new JButton();
            buttonList[i].addActionListener(optionButton);
            questionPanel.add(buttonList[i]);
        }
        questionPanel.add(answerLabel);
        questionPanel.add(back);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(50, 70, 70, 100));
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setSize(500, 100);
        this.question(qClass.getQuestion(), op, qClass.getAnswer());
        questionPanel.setVisible(true);

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

        //scoreFrame settings
        scoreFrame.add(scorePanel, BorderLayout.CENTER);
        scoreFrame.setSize(200, 100);
        scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreFrame.pack();
        scoreFrame.setLocationRelativeTo(null);
        scoreFrame.setFocusable(true);
        scoreFrame.setVisible(false);

        //QuestionFrame settings
        questionFrame.add(questionPanel, BorderLayout.CENTER);
        questionFrame.setSize(500, 400);
        questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionFrame.pack();
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setFocusable(true);
        questionFrame.setVisible(false);

        //Frame settings
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(500, 100);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.setVisible(true);
    }

    // sets the questions
    public void question(String question, String[] op, String answer)
    {
        this.question = question;
        this.op = op;
        this.answer = answer;
        questionLabel.setText("Q " + (qClass.questionNumber + 1) + " : " + question);
        List<String> options = Arrays.asList(op);
        Collections.shuffle(options);
        for (int i = 0;i < op.length;i++)
        {
            buttonList[i].setText(options.get(i));
            buttonList[i].setForeground(Color.black);
        }
    }
    public static void main(String[] args)
    {
        Main m = new Main();
        m.menu();
    }

    //REgisters the answer
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
                        scoreLabel.setText("Score : " + String.valueOf(++score));
                    }
                    else
                    {
                        buttonList[i].setForeground(Color.red);
                        answerLabel.setText("");
                    }
                }
                else
                {
                    buttonList[i].setForeground(Color.black);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception timeException) {
                //TODO: handle exception
            }
            if(qClass.next())
            {
                op = qClass.getOptions();
                question(qClass.getQuestion(), op, qClass.getAnswer());
            }
            else
            {
                String display = String.format("Your score is %d", score);
                scoreFrameLabel.setText(display);

                exitJButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        scoreFrame.setVisible(false);
                        mainFrame.setVisible(true);
                    }
                });

                retryJButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        score = 0;
                        qClass.questionNumber = 0;
                        op = qClass.getOptions();
                        scoreLabel.setText("Score : " + String.valueOf(score));
                        answerLabel.setText("");
                        question(qClass.getQuestion(), qClass.getOptions(), qClass.getAnswer());
                        questionFrame.setVisible(true);
                        scoreFrame.setVisible(false);
                    }
                });

                questionFrame.setVisible(false);
                scoreFrame.setVisible(true);
            }
        }
    };

    ActionListener startQuiz = new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            qClass.setTopic(e.getActionCommand());
            score = 0;
            qClass.questionNumber = 0;
            op = qClass.getOptions();
            scoreLabel.setText("Score : " + String.valueOf(score));
            question(qClass.getQuestion(), qClass.getOptions(), qClass.getAnswer());
            mainFrame.setVisible(false);
            questionFrame.setVisible(true);
        }
    };
}
