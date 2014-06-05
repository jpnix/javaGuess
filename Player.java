/**
* Java GUI guessing game
* Matt Permenter
* Last Changed: 4/4/2012
*/


package numberGuess;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;


public class Player extends JFrame implements ActionListener
{
       private CardLayout playerCard;
       private JPanel cardPanel;
       public String player1;
       public String player2;
       public String number;
       public String numGuess;
       public JTextField oneName;
       public JTextField twoName;
       public JTextField guessNumber;
       public JTextField guess;
       public JTextField playerScore;
       public JLabel tf;
       public int score = 0;
       public int secretNumber;
       public int numberGuessed;

	
	  // Constructor sets up all the cards in the card layout:
	  public Player()
	  {
		
		setTitle("Guess the number!");
		setSize(300,200);
		setLocation(500,400);
		//found out if you dont add default close you can use up 4gb of RAM in about 30min of testing
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		cardPanel = new JPanel();
		playerCard = new CardLayout();
		cardPanel.setLayout(playerCard);
		
		//this is the starting card where you get the user names
		JPanel startCard = new JPanel();
		startCard.setLayout(new FlowLayout());
		JLabel p1Name = new JLabel("Player 1 Name:");
		oneName = new JTextField();
		oneName.setColumns(20);
		
		JLabel p2Name = new JLabel("Player 2 Name:");
		twoName = new JTextField();
		//columns make the textfield longer 
		twoName.setColumns(20);

		JButton start = new JButton("Start Game");
		//add action listener to the button
		start.addActionListener(this);
		
		
		//add first components
		startCard.add(p1Name);
		startCard.add(oneName);
		startCard.add(p2Name);
		startCard.add(twoName);
		startCard.add(start);
		

		//add panel to the content 
		cardPanel.add("Start", startCard);
		contentPane.add(cardPanel);
		playerCard.first(cardPanel);
		
		//this is the panel for 2nd card it gets the secret number
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new FlowLayout());
		JButton play = new JButton("Play");
		play.addActionListener(this);
        JLabel numLabel = new JLabel("Choose the secret number for player to guess");
		guessNumber = new JTextField();
		guessNumber.setColumns(5);
		numberPanel.add(numLabel);
		numberPanel.add(guessNumber);
		numberPanel.add(play);
		cardPanel.add("number", numberPanel);
		//end 2nd panel
		
		//this is the panel for 3nd card the actual play part of the game
		JPanel playPanel = new JPanel();
		playPanel.setLayout(new FlowLayout());
		JButton newBtn = new JButton("New Number");
		JButton great = new JButton(">");
		great.addActionListener(this);
		JButton less = new JButton("<");
		less.addActionListener(this);
		JButton equal = new JButton("=");
		equal.addActionListener(this);
		newBtn.addActionListener(this);
        JLabel playRule = new JLabel("Use the buttons to help you guess the number");
        JLabel numHere = new JLabel("Input Guess Here");
        JLabel dispScore = new JLabel("player" + " Score");
        tf = new JLabel("Click to see true or false");
		guess = new JTextField();
		guess.setColumns(20);
		playerScore = new JTextField();
		playerScore.setColumns(5);
		playPanel.add(playRule);
		playPanel.add(tf);
		playPanel.add(great);
		playPanel.add(equal);
		playPanel.add(less);
		playPanel.add(numHere);
		playPanel.add(guess);
		playPanel.add(dispScore);
		playPanel.add(playerScore);
		playPanel.add(newBtn);
		cardPanel.add("game", playPanel);
		//end 3nd panel
	  }

	//this is where all the action events are
	//this gets the names from the players and displays second card
	public void actionPerformed(ActionEvent e) 
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Start Game"))
		{
			player1 = oneName.getText();
			player2 = twoName.getText();
			playerCard.show(cardPanel, "number");
		}
		
		//this shows the third card and lets you play the game
		if(actionCommand.equals("Play"))
		{
			playerCard.show(cardPanel, "game");
			//set up the variables entered by the user and convert to int for comparison
			number = guessNumber.getText();
			secretNumber = Integer.parseInt(number);
		}
		
		//when the new number button is pressed it resets all fields and goes back to first card
		if(actionCommand.equals("New Number"))
		{
			oneName.setText("");
		     twoName.setText("");
		     guessNumber.setText("");
		     tf.setText("Click to see true or false");
		     guess.setText("");
		     playerScore.setText("");
		     score = 0;
            playerCard.show(cardPanel, "Start");
		}
		
		
		//this is where the comparison button logic is performed
		//if the guess is greater
		if(actionCommand.equals(">"))
		{
			//get the users guess and convert to actual number
			numGuess = guess.getText();
			numberGuessed = Integer.parseInt(numGuess);
			
			//do comparisons and set true false label accordingly and add to score
			if(secretNumber < numberGuessed)
			{
				tf.setText("True");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
			else 
			{
				tf.setText("False");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
		}
		
		
		//if the user guess is equal
		if(actionCommand.equals("="))
		{
			
          
			numGuess = guess.getText();
			numberGuessed = Integer.parseInt(numGuess);
			
			if(secretNumber == numberGuessed)
			{
				tf.setText(player2 + " Guessed the Number!");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
			else
			{
				tf.setText("False");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
		}
		
		//if user guess is less than the secret number
		if(actionCommand.equals("<"))
		{
			numGuess = guess.getText();
			numberGuessed = Integer.parseInt(numGuess);
			
			if(secretNumber > numberGuessed)
			{
				tf.setText("True");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
			else
			{
				tf.setText("False");
				score+=1;
				playerScore.setText(Integer.toString(score));
			}
		}
	}
} 
	
	
	
	

