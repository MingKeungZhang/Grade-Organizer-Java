import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * Create the GUI class. It uses JFrame to create the GUI
 */
public class GUI extends JFrame
{
	//To hold the student name and grade
	private String firstName, lastName;
	private int grade;
	private int student;

	//Create an array for student object with comparable
	private Comparable<StudentGrade>[] report;
	
	//the array size
	private final int ARRAY_SIZE = 25;

	//create the panel
	private JPanel studentPanel;
	private JPanel buttonPanel;

	//Create the student panel
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField gradeTF;
	private JLabel firstNameLabel, lastNameLabel, gradeLabel;
	private JButton addButton;

	//Create the button panel
	private JButton averageButton;
	private JButton sortGradeButton;
	private JButton exitButton;

	//create the textfile
	String fileName;

	/**
	 * The constructor. Create the GUI layout
	 */
	public GUI() 
	{
		setTitle("Statistic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		buildStudentPanel();
		buildButtonPanel();
		add(studentPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);

		//initialized variables
		report = new StudentGrade[ARRAY_SIZE];
		student = 0;
	}

	/**
	 * Build the student panel
	 */
	private void buildStudentPanel() 
	{
		//create the panel object
		studentPanel = new JPanel();
		firstNameTF = new JTextField(12);
		lastNameTF = new JTextField(12);
		gradeTF = new JTextField(4);
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("  Last Name");
		gradeLabel = new JLabel("  Grade");

		//create the button
		addButton = new JButton("Add");
		addButton.addActionListener(new AddButtonListener());
		
		//the border
		studentPanel.setBorder(BorderFactory.createTitledBorder("Student Info"));
		
		//Add the objects to the panel
		studentPanel.add(firstNameLabel);
		studentPanel.add(firstNameTF);
		studentPanel.add(lastNameLabel);
		studentPanel.add(lastNameTF);
		studentPanel.add(gradeLabel);
		studentPanel.add(gradeTF);
		studentPanel.add(addButton);
	}

	/**
	 * Build the button panel
	 */
	private void buildButtonPanel() 
	{
		//create the buttons
		buttonPanel = new JPanel();
		averageButton = new JButton("Average Grade");
		sortGradeButton = new JButton("Sort Grades");
		exitButton = new JButton("Exit");
		
		//add the listener for the buttons
		averageButton.addActionListener(new AverageButtonListener());
		sortGradeButton.addActionListener(new SortGradeButtonListener());
		exitButton.addActionListener(new ExitButtonListener());
		
		//add the button to the panel
		buttonPanel.add(averageButton);
		buttonPanel.add(sortGradeButton);
		buttonPanel.add(exitButton);		
	}

	/**
	 * Private inner class that handles the event when
	 * the user clicks the add student button.
	 */
	private class AddButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{	
			firstName = firstNameTF.getText();
			lastName = lastNameTF.getText();
			try
			{
			grade = Integer.parseInt(gradeTF.getText());
			}
			catch(NumberFormatException f)
			{
			}
			
			if(valid() == true)
			{
				report[student] = new StudentGrade(firstName, lastName, grade);

				System.out.print(((StudentGrade) report[student]).firstName);
				System.out.print(((StudentGrade) report[student]).lastName);
				System.out.println(((StudentGrade) report[student]).grade);

				student++;

				print();
				
				firstNameTF.setText("");
				lastNameTF.setText("");
				gradeTF.setText("");
				
				if(student == ARRAY_SIZE)
				{
					addButton.setEnabled(false);
				}

			}

		}
	}

	/**
	 * Private inner class that handles the event when
	 * the user clicks the sort name button.
	 */
	private class AverageButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			double total = 0;
			for (int i = 0; i < student; i++)
			{
				total += ((StudentGrade) report[i]).grade;
			}
			double average = total / (student);
			JOptionPane.showMessageDialog(null, "Average Grade: " + average);

		}
	}

	/**
	 * Private inner class that handles the event when
	 * the user clicks the sort grade button.
	 */
	private class SortGradeButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{	
			for (int i = 1; i < student; i++)
			{
				Comparable<StudentGrade> temp = report[i];
				int j = i;
				while(j > 0 && temp.compareTo((StudentGrade) report[j - 1]) < 0)
				{
					report[j] = report[j-1];
					j--;
				}
				report[j] = temp;
			}
			JOptionPane.showMessageDialog(null, "Grade Sorted");
			print();
		}
	}

	/**
	 * Private inner class that handles the event when
	 * the user clicks the exit button.
	 */
	private class ExitButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{	
			System.exit(0);
		}
	}
	
	/**
	 * print the students to the textfile
	 */
	public void print()
	{
		fileName = "report.txt";
		try
		{
			PrintWriter print = new PrintWriter(fileName);
			for(int i = 0; i < student; i++)
			{
				print.print(report[i].toString());
			}
			print.close();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * check if input are valid
	 * @return valid or invalid
	 */
	public boolean valid()
	{
		if(firstName.equals("") || lastName.equals(""))
		{
			firstNameTF.requestFocus();
			lastNameTF.requestFocus();
			JOptionPane.showMessageDialog(null, "Invalid");
			return false;

		}
		else
		{
			try
			{
				grade = Integer.parseInt(gradeTF.getText());
			}
			catch (NumberFormatException f)
			{
				JOptionPane.showMessageDialog(null, "Invalid");
				return false;
			}
		}
		return true;

	}
}
