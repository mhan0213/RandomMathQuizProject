package javaProgramming;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class RandomMathQuiz implements ActionListener{
    //questions 1-10
    String[] questions = {
        "What is 10 + 10?",
        "What is 6 x 5?",
        "What is 10,000 / 10?",
        "What is 987 - 97?",
        "What is the correct simplification of (3x+1)(x-1)?",
        "What is 5^2?",
        "What is the correct factor of 3x + 15?",
        "What is 2^-3?",
        "What is the cubed root of -64?",
        "What are the first 5 digits of pi?"
    };

    //multiple choice answers
    String[][] options = {
        {"10", "20", "1", "100"},
        {"65", "56", "35", "30"},
        {"10,000", "100", "1,000", "100"},
        {"890", "980", "860", "880"},
        {"3x^2 - 2x - 1", "3x^2 + 2x + 1", "3x^2 + 2x - 1", "3x^2 - 2x + 1"},
        {"10", "5", "25", "52"},
        {"3(x + 15)", "3(x + 5)", "3x(1 + 5)", "x(3 + 15)"},
        {"6", "1/6", "-8", "1/8"},
        {"-4", "8", "4", "does not exist"},
        {"3.1411", "3.1415", "3.1514", "3.1511"}
    };

    //right answers
    char[] answers = {
        'B',
        'D',
        'C',
        'A',
        'A',
        'C',
        'B',
        'D',
        'A',
        'B'
    };

    //guess, answer, index, correct guess, total q, score
    char guess;
    char answer;
    int index;
    int correctGuess = 0;
    int totalQuestions = questions.length;
    int score;
    
    //jframe, textfield, text area
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    //jbutton for buttons ABCD
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    //jlabel for answers ABCD
    JLabel labelA = new JLabel();
    JLabel labelB = new JLabel();
    JLabel labelC = new JLabel();
    JLabel labelD = new JLabel();
    //number of questions right of quiz
    JTextField numbRight = new JTextField();

    //constructor
    public RandomMathQuiz(){
        //frame
        frame.setTitle("Random Math Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setBackground(new Color(0,0,0));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        //textfield
        textfield.setBounds(0,0,600,70);
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(225,255,225));
        textfield.setFont(new Font("Open Sans", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        //textarea
        textarea.setBounds(0,70,600,70);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(225,255,225));
        textarea.setFont(new Font("Open Sans", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);
        
        //button
        buttonA.setBounds(0,140,105,105);
        buttonA.setFont(new Font("Open Sans", Font.BOLD, 25));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");
        buttonA.setBackground(new Color(225,225,225));

        buttonB.setBounds(0,245,105,105);
        buttonB.setFont(new Font("Open Sans", Font.BOLD, 25));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        buttonB.setBackground(new Color(225,225,225));

        buttonC.setBounds(0,350,105,105);
        buttonC.setFont(new Font("Open Sans", Font.BOLD, 25));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");
        buttonC.setBackground(new Color(225,225,225));

        buttonD.setBounds(0,455,105,105);
        buttonD.setFont(new Font("Open Sans", Font.BOLD, 25));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");
        buttonD.setBackground(new Color(225,225,225));

        //labels for answers
        labelA.setBounds(125,140,500,105);
        labelA.setBackground(new Color(50,50,50));
        labelA.setForeground(new Color(225,255,225));
        labelA.setFont(new Font("Open Sans", Font.PLAIN, 35));

        labelB.setBounds(125,245,500,105);
        labelB.setBackground(new Color(50,50,50));
        labelB.setForeground(new Color(225,255,225));
        labelB.setFont(new Font("Open Sans", Font.PLAIN, 35));

        labelC.setBounds(125,350,500,105);
        labelC.setBackground(new Color(50,50,50));
        labelC.setForeground(new Color(225,255,225));
        labelC.setFont(new Font("Open Sans", Font.PLAIN, 35));

        labelD.setBounds(125,455,500,105);
        labelD.setBackground(new Color(50,50,50));
        labelD.setForeground(new Color(225,255,225));
        labelD.setFont(new Font("Open Sans", Font.PLAIN, 35));

        //# of answers user got correct
        numbRight.setBounds(0,70,600,70);
        numbRight.setBackground(new Color(25,25,25));
        numbRight.setForeground(new Color(225,255,225));
        numbRight.setFont(new Font("Times new roman", Font.BOLD, 30));
        numbRight.setBorder(BorderFactory.createBevelBorder(1));
        numbRight.setHorizontalAlignment(JTextField.CENTER);
        numbRight.setEditable(false);

        //adding to frame
        frame.add(labelA);
        frame.add(labelB);
        frame.add(labelC);
        frame.add(labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        //call nextQuestion method
        nextQuestion();
    }
    //nextQuestion method
    public void nextQuestion(){
        if(index >= totalQuestions){
            results();
        }
        else{
            //printing questions and answer options
            textfield.setText("Question #" + (index + 1));
            textarea.setText(questions[index]);
            labelA.setText(options[index][0]);
            labelB.setText(options[index][1]);
            labelC.setText(options[index][2]);
            labelD.setText(options[index][3]);
        }
    }
    //action performed method
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //buttons disabled
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //buttons ABCD with corresponding answer
        if(e.getSource() ==  buttonA){
            answer = 'A';
            //if answer is correct
            if(answer == answers[index]){
                correctGuess++;
            }
        }
        if(e.getSource() ==  buttonB){
            answer = 'B';
            //if answer is correct
            if(answer == answers[index]){
                correctGuess++;
            }
        }
        if(e.getSource() ==  buttonC){
            answer = 'C';
            //if answer is correct
            if(answer == answers[index]){
                correctGuess++;
            }
        }
        if(e.getSource() ==  buttonD){
            answer = 'D';
            //if answer is correct
            if(answer == answers[index]){
                correctGuess++;
            }
        }
        //call displayAnswer method
        displayAnswer();
    }
    //display answer method
    public void displayAnswer(){
        //buttons disabled
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //if selected answer is not correct change answers to red
        if(answers[index] != 'A')
            labelA.setForeground(new Color(255, 0,0));
        if(answers[index] != 'B')
            labelB.setForeground(new Color(255, 0,0));
        if(answers[index] != 'C')
            labelC.setForeground(new Color(255, 0,0));
        if(answers[index] != 'D')
            labelD.setForeground(new Color(255, 0,0));
        //if selected answer is correct changes to green
        if(answers[index] == 'A')
            labelA.setForeground(new Color(0,225,0));
        if(answers[index] == 'B')
            labelB.setForeground(new Color(0,225,0));
        if(answers[index] == 'C')
            labelC.setForeground(new Color(0,225,0));
        if(answers[index] == 'D')
            labelD.setForeground(new Color(0,225,0));

        //1.5 second delay
        Timer pause = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //label back to original color
                labelA.setForeground(new Color(225,255,225));
                labelB.setForeground(new Color(225,255,225));
                labelC.setForeground(new Color(225,255,225));
                labelD.setForeground(new Color(225,255,225));

                answer = ' ';
                //set buttons enable
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                //next index 
                index++;
                //call next question method
                nextQuestion();
            }
        });
        //pause executes once
        pause.setRepeats(false);
        pause.start();
    }
    //result method
    public void results(){
        //buttons disabled
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        score = ((int)(correctGuess/(double)totalQuestions) * 100);

        textfield.setText("SCORE!");
        textarea.setText("");
        labelA.setText("");
        labelB.setText("");
        labelC.setText("");
        labelD.setText("");

        numbRight.setText(correctGuess + "/" + totalQuestions);

        frame.add(numbRight);
    }
}