import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;

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
			if(((JButton)action.getSource()).getText().equals("Search for course number"))
			{
				// hide the ui so more commands cant be run while this one is running
				UserInterface.getMainWindow().setVisible(false);
				// create a new search window
				UserInterface.addSearchWindow(true,false,false);
				JFrame tempFrame = UserInterface.getSearchWindow();
				tempFrame.setVisible(true); // make the window visable
				String userEntry = System.out.println(tempFrame.getLayout().toString());
				// unhide the ui so the user can pick a new task
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Search course titles"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Titles","titles");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Print all records"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("print","Print");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Compute GPA"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Compute","Compute");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Add Course"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Add","Add");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Remove Course"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Remove","Remove");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Edit Course"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Edit","Edit");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Store Database"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Store","Store");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Reload Database"))
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendMessage("Reload", "Reload");
				UserInterface.getMainWindow().setVisible(true);
			}
			else if(((JButton)action.getSource()).getText().equals("Exit"))
			{
				System.exit(0);
			}
			else 
			{
				UserInterface.getMainWindow().setVisible(false);
				UserInterface.sendWarning("There was an even but wasnt within "+
						"the expected events","Error");
				UserInterface.getMainWindow().setVisible(true);
			}
		}
	}
	
	/*************************************************************
	* Class: SearchWindowHandler	                      	     *
	* Purpose: handler for searchWindow					         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public class SearchWindowHandler implements ActionListener
	{
		// check which button was pressed
		public void actionPerformed( ActionEvent action )
		{
			if(((JButton)action.getSource()).getText().equals("Search"))
			{
				JFrame tempFrame = UserInterface.getSearchWindow();
			}
			else if(((JButton)action.getSource()).getText().equals("Close"))
			{
				UserInterface.getSearchWindow().dispose();
			}
		}
	}
}
