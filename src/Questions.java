
class Questions
{
    String[] topicList = {
        "math",
        "physics"
    };
    String[] questions = new String[4];
    String[][] options = new String[4][3];
    String[] answers = new String[4];
    int questionNumber = 0;
    void setTopic(String topic)
    {
        this.questionNumber = 0;
        switch (topic) {
            case "math":
            {
                questions[0]  =  "Value of pi?";
                questions[1]  =  "Value of e?";
                questions[2]  =  "Which is greater?";
                questions[3]  =  "Which is irrational?";

                options[0] = new String[]{ "3.41", "3.12", "3.25" };
                options[1] = new String[]{ "2.732", "2.891", "2.781"};
                options[2] = new String[]{ "3^1/3", "2*0.5", "0"};
                options[3] = new String[]{ "e", "i", "d"};

                answers[0] = "3.14";
                answers[1] = "2.718";
                answers[2] = "2^1/2";
                answers[3] = "pi";

                System.out.println("Math was selected");
            }
                break;
        
            case "physics":
            {
                questions = new String[4];
                questions[0]  =  "Value of planck's constant?";
                questions[1]  =  "Value of acceleration due to gravity?";
                questions[2]  =  "Whose gravitaional pull is greater?";
                questions[3]  =  "Which is a derivative of velocity?";

                options = new String[4][];
                options[0] = new String[]{ 
                    "6.626×10−34 J⋅Hz",
                    "6.626×10−34 J-1⋅Hz−1",
                    "6.626×10−34 J-1⋅Hz" };
                options[1] = new String[]{ "9.8m/s", "9.8ms-1", "10m/s2"};
                options[2] = new String[]{ "A pillow", "5Kg of iron", "10Kg of Hydrogen"};
                options[3] = new String[]{ "v", "S", "t"};

                answers[0] = "6.626×10−34 J⋅Hz−1";
                answers[1] = "9.8m/s2";
                answers[2] = "10Kg of iron";
                answers[3] = "a";

                System.out.println("physics was selected");
            }
                break;
        }
    }

    String getQuestion()
    {
        return questions[questionNumber];
    }

    String[] getOptions()
    {
        String[] temp = new String[4];
        for(int i = 0; i<options[questionNumber].length;i++)
            temp[i] = options[questionNumber][i];
        temp[3] = answers[questionNumber];
        return temp;
    }

    String getAnswer()
    {
        return answers[questionNumber];
    }

    boolean  next()
    {
        if (this.questionNumber < questions.length -1)
        {
            this.questionNumber++;
            return true;
        }
        else
        {
            this.questionNumber = 0;
            return false;
        }
    }
}