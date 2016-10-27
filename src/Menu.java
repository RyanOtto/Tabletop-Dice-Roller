import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Menu extends JFrame {

	int[] rulesInputToArray(String userInputString) //Will take rules window user input strings and return them as int arrays
	{
		String[] userInputElements = userInputString.split("\\s+"); //Store separate numbers in user input as elements in String array
		int[] userInputValues = new int[userInputElements.length]; //Create int array with as many elements as in the String array
		
		if(userInputElements[0].equals(">")) //If user specifies > to look for greater values 
		{
			userInputValues[0] = -3; //Set first element to -3, meaning "greater than"				
		}
		else if(userInputElements[0].equals("<")) //If user specifies < to look for lesser values
		{
			userInputValues[0] = -2; //Set first element to -2, meaning "less than"
		}
		else if(userInputElements[0].equals("-1")) //If user enters nothing
		{
			userInputValues[0] = -1; //Set first element to -1, meaning "no input"
		}
		

		for(int i = 0; i < userInputElements.length; i++){  //Store elements in String array in an int array as ints

			if(!userInputElements[i].equals(">") && !userInputElements[i].equals("<") && !userInputElements.equals("")) //If user has entered a number here
			{
				userInputValues[i] = Integer.parseInt(userInputElements[i]); //Add that number to int array
			}
		}
		return userInputValues; //Return integer array representing rule criteria
	}
	
	int[][] diceInputToArray(String userInputString) //Will take user input for dice number and sides and place store as 2d integer array
	{
		String[] userInputElements = userInputString.split("d|\\s+"); //Retrieve numbers before and after d as separate elements in String array
		int[][] numSidesAndDice = new int[2][userInputElements.length]; //Two-dimensional array to store number of dice, and number of sides in each 'row'
		
		int j = 0; //Used for storing numDice
		int k = 0; //Used for storing numSides
		
		//Place elements in two-dimensional int array.  Each odd number is numDice, each even is numSides
		for(int i = 0; i < userInputElements.length; i++){ //For length of String array
			if(i%2 == 0) //If dealing with a number of dice
			{
				numSidesAndDice[0][j] = Integer.parseInt(userInputElements[i]); //Put number in jth element of first column
				j++;
			}
			else //If dealing with a number of sides
			{
				numSidesAndDice[1][k] = Integer.parseInt(userInputElements[i]); //Put number in kth element of second column
				k++;
			}
		}

		return numSidesAndDice; //Return 2d array representing user dice choices
	}
	
	String diceRoller(int[] successInput, int[] failInput, int[] critSuccessInput, int[] critFailInput, int[][] userDiceInput) //Takes in rule and dice input, then 'rolls'
	{
		int numSuccess = 0; //Number of successes from dice rolls
		int numCritSuccess = 0; //Number of crit success from dice rolls
		int numFail = 0; //Number of failures from dice rolls
		int numCritFail = 0; //Number of crit failures from dice rolls
		Random diceNumberGen = new Random(); //Used for dice roll		
		String rollString = ""; //Will be used to show numbers rolled		
		
		for(int i = 0; i < userDiceInput.length; i++){ //For the amount of dice/side combinations given by the user
			for(int j = 0; j < userDiceInput[0][i]; j++){ //For the amount of dice specified in this element
				int rollNumber = diceNumberGen.nextInt(userDiceInput[1][i]) + 1; //Generate number from 1 to number of sides for current die
				rollString += rollNumber + " "; //Add number rolled to rollString, so user can see what number they got		
				
				//Then check rules and add numbers to the appropriate variables
				if(successInput[0] != -1) //If user has entered input for the rule (not left it blank)
				{
					if(successInput[0] != -2 && successInput[0] != -3){ //If user has not specified a greater than or less than rule
						for(int k = 0; k < successInput.length; k++){ //For each element of rule array
							if(rollNumber == successInput[k]){ //If roll number is equal to number specified
								numSuccess++; //Add 1 to success count
						 	}}}
		
					else if(successInput[0] == -2){ //If user entered input for less than a set of numbers 
						for(int k = 1; k < successInput.length; k++){ //For each element of rule array
							if(rollNumber < successInput[k]){ //If roll number is less than number specified
								numSuccess++; //Add 1 to success count
							}}}
					
					else if(successInput[0] == -3){ //If user entered input for greater than a set of numbers
						for(int k = 1; k < successInput.length; k++){ //For each element of rule array
							if(rollNumber > successInput[k]){ //If roll number is greater than number specified
								numSuccess++; //Add 1 to success count
							}}}}
			
			
	
				if(failInput[0] != -1) //If user has entered input for the rule (not left it blank)
				{
					if(failInput[0] != -2 && failInput[0] != -3){ //If user has not specified a greater than or less than rule
						for(int k = 0; k < failInput.length; k++){ //For each element of rule array
							if(rollNumber == failInput[k]){ //If roll number is equal to number specified
								numFail++; //Add 1 to fail count
						 	}}}
					
					else if(failInput[0] == -2){ //If user entered input for less than a set of numbers
						for(int k = 1; k < failInput.length; k++){ //For each element of rule array
							if(rollNumber < failInput[k]){ //If roll number is less than number specified
								numFail++; //Add 1 to fail count
							}}}
								
					else if(failInput[0] == -3){ //If user entered input for greater than a set of numbers
						for(int k = 1; k < failInput.length; k++){ //For each element of rule array
							if(rollNumber > failInput[k]){ //If roll number is greater than number specified
							 numFail++; //Add 1 to fail count
							}}}}
			
				
				if(critSuccessInput[0] != -1) //If user has entered input for the rule (not left it blank)
				{
					if(critSuccessInput[0] != -2 && critSuccessInput[0] != -3){ //If user has not specified a greater than or less than rule
						for(int k = 0; k < critSuccessInput.length; k++){ //For each element of rule array
							if(rollNumber == critSuccessInput[k]){ //If roll number is equal to number specified
								numCritSuccess++; //Add 1 to crit success count
							}}}
				
					else if(critSuccessInput[0] == -2){ //If user entered input for less than a set of numbers
						for(int k = 1; k < critSuccessInput.length; k++){ //For each element of rule array
							if(rollNumber < critSuccessInput[k]){ //If roll number is less than number specified
								numCritSuccess++; //Add 1 to crit success count
							}}}
					
					else if(critSuccessInput[0] == -3){ //If user entered input for greater than a set of numbers
						for(int k = 1; k < critSuccessInput.length; k++){ //For each element of rule array
							if(rollNumber > critSuccessInput[k]){ //If roll number is greater than number specified
								numCritSuccess++; //Add 1 to crit success count
							}}}}
			
				
				if(critFailInput[0] != -1) //If user has entered input for the rule (not left it blank)
				{
					if(critFailInput[0] != -2 && critFailInput[0] != -3){ //If user has not specified a greater than or less than rule
						for(int k = 0; k < critFailInput.length; k++){ //For each element of rule array
							if(rollNumber == critFailInput[k]){ //If roll number is equal to success number specified
								numCritFail++; //Add 1 to crit fail count
						 	}}}
							
					else if(critFailInput[0] == -2){ //If user entered input for less than a set of numbers
						for(int k = 1; k < critFailInput.length; k++){ //For each element of rule array
							if(rollNumber < critFailInput[k]){ //If roll number is less than success number specified
								numCritFail++; //Add 1 to crit fail count
							}}}
								
					else if(critFailInput[0] == -3){ //If user entered input for greater than a set of numbers
						for(int k = 1; k < critFailInput.length; k++){ //For each element of rule array
							if(rollNumber > critFailInput[k]){ //If roll number is greater than success number specified
								numCritFail++; //Add 1 to crit fail count
							}}}}
			}
		}
		
		String resultString = "You Rolled The Following Numbers: " + rollString + "\nSuccesses: " + numSuccess + " Critical Successes: " + numCritSuccess + " Failures: " + numFail + " Critical Failures: " + numCritFail; 
		return resultString; //Return string that shows successes, failures, etc.
	}
	
	//The main menu
	public Menu(){
		
	//Main menu frame
	JFrame menuFrame = new JFrame();
	menuFrame.setSize(500, 200);
	menuFrame.setTitle("Main Menu");
	menuFrame.setBackground(Color.white);
	menuFrame.setLayout(new GridLayout());
	
	//Help frame, used to show instructions to user
	JFrame helpFrame = new JFrame();
	helpFrame.setSize(1000, 200);
	helpFrame.setTitle("Main Menu");
	helpFrame.setBackground(Color.white);
	helpFrame.setLayout(new GridLayout());
	
	//Choose Dice Frame, used for setting what dice to roll 
	JFrame diceChooseFrame = new JFrame();
	diceChooseFrame.setSize(615, 180);
	diceChooseFrame.setTitle("Choosing Your Dice");
	diceChooseFrame.setBackground(Color.white);
	diceChooseFrame.setLayout(new GroupLayout(diceChooseFrame.getContentPane()));
	
	//Rules frame, used to set what dice rolls are successes/failures, etc.
	JFrame rulesFrame = new JFrame();
	rulesFrame.setSize(510, 350);
	rulesFrame.setTitle("Setting Your Dice Rules");
	rulesFrame.setBackground(Color.white);
	rulesFrame.setLayout(new GroupLayout(rulesFrame.getContentPane()));

	//Rules frame's GroupLayout panel, used for user input with dice rules
	JPanel rulesPanel = new JPanel();
	rulesPanel.setBackground(Color.white);
	rulesPanel.setSize(500, 310);
	GroupLayout rulesLayout = new GroupLayout(rulesPanel);
	rulesLayout.setAutoCreateGaps(true);
	rulesLayout.setAutoCreateContainerGaps(true);
	
	//Choose Dice frame's GroupLayout panel, used to determine number of sides and quantity of dice
	JPanel chooseDicePanel = new JPanel();
	chooseDicePanel.setBackground(Color.white);
	chooseDicePanel.setSize(600, 140);
	GroupLayout chooseDiceLayout = new GroupLayout(chooseDicePanel);
	chooseDiceLayout.setAutoCreateGaps(true);
	chooseDiceLayout.setAutoCreateContainerGaps(true);
	
	
	//Labels for rules panel
	JLabel instructionsLabel = new JLabel();
	String instructionsString = "To set a rule, type a number and press space before adding another number.  If you do not want to specify numbers for a rule, leave it blank."; 
	instructionsLabel.setText("<html>"+ instructionsString +"</html>"); //Used to make text wrap when exceeding window width
	
	//Labels for choose dice panel
	JLabel diceChooseInstructions = new JLabel();
	String diceChooseInstructionsString = "Type the number of dice followed by 'd' and the number of sides. Follow each entry by a space, IE roll two 3-sided dice and three 4-sided dice by typing 2d3 3d4.";
	diceChooseInstructions.setText("<html>"+ diceChooseInstructionsString +"</html>"); //Used to make text wrap when exceeding window width

	//Labels for rules frame
	JLabel successLabel = new JLabel("Success: ");
	JLabel critSuccessLabel = new JLabel("Critical Success: ");
	JLabel failLabel = new JLabel("Failure: ");
	JLabel critFailLabel = new JLabel("Critical Failure: ");
	
	//Label for dice choosing frame
	JLabel diceChooseLabel = new JLabel("Dice: ");
	
	//Text field for dice choosing frame
	JTextField diceChooseEntry = new JTextField();
	
	//Text fields to be used for user input
	JTextField successEntry = new JTextField();
	JTextField critSuccessEntry = new JTextField();
	JTextField failEntry = new JTextField();
	JTextField critFailEntry = new JTextField();
	
	//OK and Cancel buttons for rules frame.  OK will keep changes to rules set by user and close window, cancel will disregard them and close window
	JButton rulesOkButton = new JButton("OK"); //OK button in rules frame, to confirm rule choices
	JButton rulesCancelButton = new JButton("Cancel"); //Cancel button, to prevent rule changes
	
	//OK and Cancel buttons for dice choosing frame.  OK will confirm dice choices set by user and close window, cancel will disregard them and close the window
	JButton chooseDiceOKButton = new JButton("OK");
	JButton chooseDiceCancelButton = new JButton("Cancel");
	
	//Choose Dice frame's panel layout
		chooseDiceLayout.setHorizontalGroup(chooseDiceLayout.createParallelGroup() 
				.addComponent(diceChooseInstructions)
				.addComponent(diceChooseLabel)
				.addComponent(diceChooseEntry)
	
				.addGroup(chooseDiceLayout.createSequentialGroup() //Place OK and Cancel below labels

				.addComponent(chooseDiceOKButton)
				.addComponent(chooseDiceCancelButton)
				)
			);
		
		chooseDiceLayout.setVerticalGroup(chooseDiceLayout.createSequentialGroup() //Vertical group for frame components
				.addComponent(diceChooseInstructions)
				.addComponent(diceChooseLabel)
				.addComponent(diceChooseEntry)
				
				.addGroup(chooseDiceLayout.createParallelGroup() //Place OK and Cancel below labels

				.addComponent(chooseDiceOKButton)
				.addComponent(chooseDiceCancelButton)
				)
			);
		
		chooseDicePanel.setLayout(chooseDiceLayout);
		diceChooseFrame.add(chooseDicePanel);
		
		
	//Rules frame's panel layout
	rulesLayout.setHorizontalGroup(rulesLayout.createParallelGroup() //Horizontal group for frame copmonents 
			//Labels before text fields
			.addComponent(instructionsLabel)
			.addComponent(successLabel)
			.addComponent(critSuccessLabel)
			.addComponent(failLabel)
			.addComponent(critFailLabel)
			
			//Text Fields
			.addComponent(successEntry)
			.addComponent(critSuccessEntry)
			.addComponent(failEntry)
			.addComponent(critFailEntry)
			
			//OK and Cancel buttons
			.addGroup(rulesLayout.createSequentialGroup() //Place OK and Cancel below label
			.addComponent(rulesOkButton)
			.addComponent(rulesCancelButton)
			)
		);
	
	rulesLayout.setVerticalGroup(rulesLayout.createSequentialGroup() //Vertical group for frame components
			.addComponent(instructionsLabel)

			.addComponent(successLabel)
			.addComponent(successEntry)

			.addComponent(critSuccessLabel)
			.addComponent(critSuccessEntry)
			
			.addComponent(failLabel)
			.addComponent(failEntry)
			
			.addComponent(critFailLabel)
			.addComponent(critFailEntry)
						
			.addGroup(rulesLayout.createParallelGroup()
			.addComponent(rulesOkButton)
			.addComponent(rulesCancelButton)
			)
		);
	
	rulesPanel.setLayout(rulesLayout);
	rulesFrame.add(rulesPanel);
	
	//Main Menu Buttons
	JButton diceChooseButton = new JButton("Choose Dice"); //Will let user determine what kind of dice to roll, and how many
	diceChooseButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
	diceChooseButton.setBackground(Color.white);
	menuFrame.getContentPane().add(diceChooseButton);
	
	JButton diceRollButton = new JButton("Roll Dice"); //Will "roll the dice" and alert user of results
	diceRollButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
	diceRollButton.setBackground(Color.white);
	menuFrame.getContentPane().add(diceRollButton);
	
	JButton rulesButton = new JButton("Rules"); //Will allow users to set rules for dice roll successes, fails, etc.
	rulesButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
	rulesButton.setBackground(Color.white);
	menuFrame.getContentPane().add(rulesButton);
	
	JButton helpButton = new JButton("Help"); //Will show user instructions on how to use this program
	helpButton.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
	helpButton.setBackground(Color.white);
	menuFrame.getContentPane().add(helpButton);
	
	
	
	//Startup
	menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //If the main window frame is closed, exit the program
	menuFrame.setVisible(true); //Make the main window frame visible
	

	
	
	//Choose dice button
	diceChooseButton.addActionListener(new ActionListener(){ //Show window to choose amount and sides of dice to be rolled
		public void actionPerformed(ActionEvent e)
		{
			diceChooseFrame.setVisible(true);
		}});
	
	//Choose Dice OK button
	chooseDiceOKButton.addActionListener(new ActionListener(){ //Close dice choosing window without discarding input
		public void actionPerformed(ActionEvent e)
		{
			diceChooseFrame.dispose();
		}});
	
	//Choose Dice Cancel button
	chooseDiceCancelButton.addActionListener(new ActionListener(){ //Close dice choosing window and discard user input
		public void actionPerformed(ActionEvent e)
		{
			diceChooseEntry.setText("");
			diceChooseFrame.dispose();  
		}});
	
	//Rules button
	rulesButton.addActionListener(new ActionListener(){ //Show window to choose rules for dice rolls
		public void actionPerformed(ActionEvent e)
		{
			rulesFrame.setVisible(true);
		}});
	
	//Rules OK button
	rulesOkButton.addActionListener(new ActionListener(){ //Close rules window without discarding user input
		public void actionPerformed(ActionEvent e)
		{
			rulesFrame.dispose();
		}});
	
	//Rules Cancel button
	rulesCancelButton.addActionListener(new ActionListener(){ //Close rules window and discard user input
		public void actionPerformed(ActionEvent e)
		{
			successEntry.setText("");
			critSuccessEntry.setText("");
			failEntry.setText("");
			critFailEntry.setText("");

			rulesFrame.dispose(); 
		}});
	
	//Roll dice button
	diceRollButton.addActionListener(new ActionListener(){ //Take user input into int arrays, roll dice, display results
		public void actionPerformed(ActionEvent e)
		{
			//Assign rules frame user input to int array
			String successRule = successEntry.getText(); //Store user input for successful roll numbers
			String critSuccessRule = critSuccessEntry.getText(); //Store user input for critically successful roll numbers
			String failRule = failEntry.getText(); //Store user input for fail roll numbers
			String critFailRule = critFailEntry.getText(); //Store user input for critical failure roll numbers
			String diceChosen = diceChooseEntry.getText(); //Store user input for dice to roll
			
			if(successRule.isEmpty()){ //If no rules for successes
				successRule = "-1"; //Change string to -1 represent "no rules"
			}
			
			if(critSuccessRule.isEmpty()){ //If no rules for crit success
				critSuccessRule = "-1"; //Change string to -1 represent "no rules"
			}
			
			if(failRule.isEmpty()){ //If no rules for failure 
				failRule = "-1"; //Change string to -1 represent "no rules"
			}
			
			if(critFailRule.isEmpty()){ //If no rules for crit failure 
				critFailRule = "-1"; //Change string to -1 to represent "no rules"
			}
			
			//Store user specified rules in int arrays 
			int[] userSuccessInput = rulesInputToArray(successRule); 
			int[] userCritSuccessInput = rulesInputToArray(critSuccessRule);
			int[] userFailInput = rulesInputToArray(failRule);
			int[] userCritFailInput = rulesInputToArray(critFailRule);
			
			//Store dice chosen by user input in int array
			if(diceChosen.isEmpty()){ //If no dice chosen
				JOptionPane.showMessageDialog(menuFrame, "Please enter an amount of dice to roll using the 'Choose Dice' button.", "ERROR: No Dice Chosen", JOptionPane.ERROR_MESSAGE); //Tell user to choose dice
			}
			
			int[][] userDiceInput = diceInputToArray(diceChooseEntry.getText()); //Put user input dice num and sides to int[][] array
			
			if(!diceChosen.isEmpty()){ //If user entered dice to roll
				JOptionPane.showMessageDialog(menuFrame, diceRoller(userSuccessInput, userFailInput, userCritSuccessInput, userCritFailInput, userDiceInput)); //Roll dice according to user-input rules, show results to user
			}}});
		
	//Help button
	helpButton.addActionListener(new ActionListener(){ //Show instructions
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(helpFrame, 
			"This program will sort through which dice would be considered 'successes' and 'failures' based on your rule choices.  "
			+ "\n* Choose the amount of sides a die will have and how many of that die you want to roll by pressing the 'Choose Dice' button.  "
			+ "\n* Configure what counts as successes, failures, etc. by pressing the 'Rules' button.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		}});
	}
}