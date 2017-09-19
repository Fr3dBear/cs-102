import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* EventHandlers class:  collection of event handlers         *
**************************************************************/
public interface EventHandler
{
	/*************************************************************
	* Class: MainWindowHandler	                      	         *
	* Purpose: handler for mainWindow					         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public class MainWindowHandler implements ActionListener
	{
		// check which button was pressed
		public void actionPerformed( ActionEvent action )
		{
			// hide the ui so more commands cant be run while this one is running
			UserInterface.getMainWindow().setVisible(false);
			
			// if searh for num is pressed
			if(((JButton)action.getSource()).getText().equals("Search for course number"))
			{
				try
				{
					// ask user for course number
					String inputValue = JOptionPane.showInputDialog("Enter the Course Number:");
					Prog5.searchCourseNumInt(inputValue); // search for num
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if search for title is pressed
			else if(((JButton)action.getSource()).getText().equals("Search course titles"))
			{	
				try
				{
					// ask user for course title
					String inputValue = JOptionPane.showInputDialog("Enter the Course Title:");
					Prog5.searchCourseTitleInt(inputValue); // search for title
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if print database is pressed
			else if(((JButton)action.getSource()).getText().equals("Print all records"))
			{
				Prog5.printDatabaseInt(); // print the database out
			}
			// if compute gpa is pressed
			else if(((JButton)action.getSource()).getText().equals("Compute GPA"))
			{
				Prog5.calculateGPAInt(); // print the calculated gpa
			}
			// if add course is pressed
			else if(((JButton)action.getSource()).getText().equals("Add Course"))
			{
				try
				{
					Prog5.userAddCourse(Prog5.courseData); // start the add course prompts
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if remove course is pressed
			else if(((JButton)action.getSource()).getText().equals("Remove Course"))
			{
				try 
				{
					Prog5.userRemoveCourse(Prog5.courseData); // start the remove course prompts
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if edit course is pressed
			else if(((JButton)action.getSource()).getText().equals("Edit Course"))
			{
				try
				{
					Prog5.userEditCourse(Prog5.courseData); // start the edit course prompts
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if store database is pressed
			else if(((JButton)action.getSource()).getText().equals("Store Database"))
			{
				try
				{
					Prog5.userStore(Prog5.courseData); // start the store prompts
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if reload database is pressed
			else if(((JButton)action.getSource()).getText().equals("Reload Database"))
			{
				try
				{
					Prog5.userReload(Prog5.courseData); // start the reload prompts
				}
				catch( NullPointerException exc ) { /* do nothing user just hit cancel */ }
			}
			// if exit database is pressed
			else if(((JButton)action.getSource()).getText().equals("Exit"))
			{
				System.exit(0);
			}
			// default catch for button presses
			else 
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendWarning("There was an even but wasnt within "+
						"the expected events","Error");
				UserInterface.getMainWindow().setVisible(true);
			}
			// unhide the ui so the user can pick a new task
			UserInterface.getMainWindow().setVisible(true);
		}
	}
}
