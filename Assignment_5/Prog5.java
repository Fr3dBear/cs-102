import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField; 

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* Assignment class:  main entry point for assignment 3       *
**************************************************************/
public class Prog5 
{
	static Database courseData = new Database(); // Create a database to import to
	
	/*************************************************************
	* Method: main()			                                 *
	* Purpose: main entry for the program	 			         *
	*          							                         *
	* Parameters: String[]:			 string array of prog args	 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	public static void main (String[] args)
	{
		UserInterface ui = new UserInterface(); // init the ui
		ui.addMainWindow();		 // config for main window config
		
		try
		{
			// attempt to load the database with file location
			//   given at runtime
			courseData.loadDatabase(args);
		}
		catch(ArrayIndexOutOfBoundsException exc)
		{
			UserInterface.sendWarning("The database is full, " + exc.getMessage() +
					" course(s) were loaded!", "ERROR");
		}
		catch(IllegalArgumentException exc)
		{
			// there was an error show it to user
			UserInterface.sendWarning(exc.getMessage(),"ERROR");
		}
	}
	
	/*************************************************************
	* Method: userAddClass()	                                 *
	* Purpose: add a user class to database 			         *
	*          							                         *
	* Parameters: Database: targetbase							 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	static void userAddCourse(Database targetDatabase)
	{
		String userInput = JOptionPane.showInputDialog(
							"Enter your class in the following format:\n"  +
						   "201003/CE-320/4/Microcomputers I/B+/N\n"      +
						   "yyyytt/corsnum/credit/title/grade/excluded\n" +
						   "yyyy is year tt is term (01,02,03,04)     ",
						   "New Course");
		Course tempCourse = new Course(); // course to be added
		String dateString = ""; // Temp string for separating the year and semester
		if(userInput == "") 
		{
			UserInterface.sendWarning("You didn't enter anything", "Really?");
			return;
		}
		Scanner pieces = new Scanner(userInput); // Scanner for spliting string
		
		
		// setTermTaken, and setExcludeFlag will throw a parse error
		//     if data sent is not in the correct format
		try
		{
			pieces.useDelimiter("/");
			dateString = pieces.next();
			if(dateString.length() != 6) 
			{
				throw new ParseException("Year/Term is wrong length",0);
			}
			tempCourse.setYearTaken(dateString.substring(0, 4)); // Set year to string char's 0-4 (year)
			tempCourse.setTermTaken(dateString.substring(4, 6)); // Set term taken to the 2 digit semester code
			tempCourse.setCourseNumber(pieces.next());			 // Set the course number
			tempCourse.setCreditCount(pieces.nextInt());		 // Set the number of credits the class is worth
			tempCourse.setCourseTitle(pieces.next());			 // Set the course title
			tempCourse.setCourseGrade(pieces.next().toUpperCase()); // Set the course grade
			tempCourse.setExcludeFlag(pieces.next());			 // Set the exclude flag
		}
		catch(ParseException exc)
		{
			UserInterface.sendWarning(exc.getMessage() + " Your input is ignored!\n", "Error");
			return;
		}
		catch(InputMismatchException exc) 
		{
			UserInterface.sendWarning(exc.getMessage()+"your input is ignored\n", "Error");
			return;
		}
		targetDatabase.addCourse(tempCourse);
	}
	
	/*************************************************************
	* Method: userStore()	                                 	 *
	* Purpose: store database to file					         *
	*          							                         *
	* Parameters: Database: 		 targetbase 				 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	static void userStore(Database targetDatabase)
	{
		String userInput = JOptionPane.showInputDialog("Enter a file name to save to: ");
		try
		{
			targetDatabase.storeDatabase(userInput);
		}
		catch(IllegalArgumentException exc)
		{
			UserInterface.sendMessage(
					"\nAre you trying to break me? There is nothing to store!\n",
					"ERROR");
		}
	}
	
	/*************************************************************
	* Method: userReload()		                                 *
	* Purpose: reload a database from file	 			         *
	*          							                         *
	* Parameters: Database: 		 targetbase					 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	static void userReload(Database targetDatabase)
	{
		String userInput = JOptionPane.showInputDialog(
				"Enter a file to load, within the local directory: ");
		// Cast to string array to pass to load database
		String userInputArray[] = {userInput};
		targetDatabase.removeAll(); // destroy old data
		try
		{
			// attempt to load the database with file location
			//   given at runtime
			targetDatabase.loadDatabase(userInputArray);
		}
		catch(ArrayIndexOutOfBoundsException exc)
		{
			UserInterface.sendWarning("The database is full, " + exc.getMessage() +
					" course(s) were loaded!", "ERROR");
		}
		catch(IllegalArgumentException exc)
		{
			UserInterface.sendWarning(exc.getMessage(), "ERROR");
		}
		UserInterface.sendMessage("Database reloaded!", "INFO");
	}
	
	/*************************************************************
	* Method: printCourse()	 		                             *
	* Purpose: compile a single course into a buffer	         *
	* 															 *
	* NOTE: this was making all my methods too long so...        *
	*          							                         *
	* Parameters: Database: LinkedList: Index: 					 *
	* 				targetDatabase, returnResults, index 		 *
	* Returns: String:           	the info in the index        *
	**************************************************************/
	private static String printCourse(LinkedList<Course> returnResults, int index)
	{
		String buffer =
			returnResults.get(index).getCourseNumber() 	+ ": " +
			returnResults.get(index).getCourseTitle()  	+ " (" +
			returnResults.get(index).getCreditCount()  	+ "). "+
			returnResults.get(index).getTermTaken()		+ " "  +
			returnResults.get(index).getYearTaken()	 	+ " "  +
			returnResults.get(index).getCourseGrade()  	+ "\n" ;
		return buffer;
	}
	
	/*************************************************************
	* Method: searchCourseNumInt() 		                         *
	* Purpose: search for num interface for UI 			         *
	* Parameters: String			 Course num					 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	public static void searchCourseNumInt(String userInput)
	{
		try
		{
			// this prints internally so just junk the return we dont need it
			new CourseSearch().findByNumber(userInput, courseData);
		} 
		catch (NoSuchFieldException e)
		{
		    UserInterface.sendWarning("Course does not exist!", "ERROR");
		}
	}
	
	/*************************************************************
	* Method: searchCourseTitleInt() 		                     *
	* Purpose: search interface for title for UI		         *
	* Parameters: String			 Course title				 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	public static void searchCourseTitleInt(String userInput)
	{
		try
		{
			// this prints internally so just junk the return we dont need it
			new CourseSearch().findByTitle(userInput, courseData);
		} 
		catch (NoSuchFieldException e)
		{
		    UserInterface.sendWarning("Course does not exist!", "ERROR");
		}
	}
	
	/*************************************************************
	* Method: printDatabaseInt() 		                         *
	* Purpose: printDatabase interface for UI			         *
	* Parameters: void:				 N/A						 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	public static void printDatabaseInt()
	{
		try
		{
			// this does the actual printing
			new ConsolePrint().printDatabase(courseData);
		}
		catch(IllegalArgumentException exc)
		{
			// some exception happened show it to user
			UserInterface.sendWarning(exc.getMessage(),"ERROR");
		}
	}
	
	/*************************************************************
	* Method: calculateGPAInt() 		                         *
	* Purpose: gpa calc interface for UI			             *
	* Parameters: void:				 N/A						 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	public static void calculateGPAInt()
	{
		try
		{
			String GPA = String.format("%.2f\n\n",new GpaCalc().calcGpa(courseData));
			UserInterface.sendMessage("GPA is: " + GPA, "GPA Calculation");
		}
		catch(IllegalArgumentException exc)
		{
			// some exception happened show it to user
			UserInterface.sendWarning(exc.getMessage(),"ERROR");
		}
	}
	
	/*************************************************************
	* Method: userEditCourse()		                             *
	* Purpose: edit a user class to database , frontend public   *
	*          							                         *
	* Parameters: Database: Scanner: targetbase, console,  		 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	static void userEditCourse(Database targetDatabase)
	{
		createEditSearchWindow();
	}
	
	/*************************************************************
	* Method: userEditCourseWork()	                             *
	* Purpose: edit the course, the background work		         *
	*          							                         *
	* Parameters: Search: JFrame:    search, searchWindow 		 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void userEditCourseWork(String search)
	{
		String[] split = search.split("/"); // split the entry into 2
		String userInput; // get first part
		String termInput; // get second part
		try
		{
			userInput = split[0]; // get first part
			termInput = split[1]; // get second part
		}
		catch( ArrayIndexOutOfBoundsException exc)
		{
			UserInterface.sendWarning("You didn't enter enough information", "ERROR");
			UserInterface.getMainWindow().setVisible(true);
			return;
		}
		int promptCtr = 0; // Counter for seeing how many time the user was prompted
		LinkedList<Course> returnResults = null; // Results from the course search
		int editedCounter = 0; // For the number of edited courses
		Database targetDatabase = Prog5.courseData; // get the database
		try
		{
			returnResults = new CourseSearch().findByNumber(userInput, targetDatabase);
		} 
		catch (NoSuchFieldException exc)
		{ 
			UserInterface.sendMessage("No results were found!", "INFO");
			UserInterface.getMainWindow().setVisible(true);
			return;
		}
		for(int index=0;index<returnResults.size();)
		{
			// Print the course as long as it matches year and term
			if(termInput.equalsIgnoreCase(returnResults.get(index).getTermTakenRaw()))
			{
				if(JOptionPane.showConfirmDialog(null, "Would you like to Edit: " +
						printCourse(returnResults, index)) == 0)
				{
					for(int index2=0;index2<targetDatabase.getDatabaseSize();index2++)
					{
						if(targetDatabase.get(index2).getTerm().equals(
								returnResults.get(index).getTermTakenRaw()))
						{
							// remove course from lower list
							targetDatabase.remove(index2, returnResults.get(index));
							userAddCourse(targetDatabase);
							editedCounter++;
						}
					}
				}
				else{/* do nothing */}
				promptCtr++;
			}
			index += 2; // because of storage in results add 2 instead of 1
		}
		if(promptCtr>0)
			UserInterface.sendMessage("You edited " + editedCounter +
					" course(s)!\n","INFO");
		else
			UserInterface.sendWarning(
					"There were no courses with the specified term!\n", "ERROR");
		UserInterface.getMainWindow().setVisible(true);
	}
	
	/*************************************************************
	* Method: createEditSearchWindow()	                         *
	* Purpose: window for entry of course info		             *
	* Parameters: void:				 N/A						 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void createEditSearchWindow()
	{
		// New window for search info
		JFrame searchWindow = new JFrame("Edit SearchWindow");
		
		searchWindow.setSize(400,100); // set the size
		GridLayout aGrid = new GridLayout(3,2,0,0); // config the layout
		searchWindow.setLayout(aGrid); // set window layout
		
		// create buttons
		JButton crsSearch 	= new JButton("Search");
		JButton close = new JButton("Close");
		
		// create lables
		JLabel crsNumLbl = new JLabel("Course Number:");
		JLabel crsSemesterLbl = new JLabel("Course Semester (yyyytt):");
		
		// create 
		JTextField crsNum = new JTextField();
		JTextField crsSemester = new JTextField();
		
		// add objects to the window's
		searchWindow.add(crsNumLbl);
		searchWindow.add(crsNum);
		searchWindow.add(crsSemesterLbl);
		searchWindow.add(crsSemester);
		searchWindow.add(crsSearch);
		searchWindow.add(close);
		
		crsSearch.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  userEditCourseWork(crsNum.getText() + "/" + crsSemester.getText());
				  searchWindow.dispose();
			  }
		});
		
		close.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  searchWindow.dispose();
				  UserInterface.getMainWindow().setVisible(true);
			  }
		});
		
		searchWindow.setVisible(true);
	}
	
	/*************************************************************
	* Method: userRemoveCourse()	                             *
	* Purpose: remove a user class to database , frontend public *
	*          							                         *
	* Parameters: Database: 		 targetbase,		  		 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	static void userRemoveCourse(Database targetDatabase)
	{
		createRemoveSearchWindow();
	}
	
	/*************************************************************
	* Method: userRemoveCourseWork()	                         *
	* Purpose: background work for the remove course             *
	* Parameters: void:				 N/A						 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void userRemoveCourseWork(String search)
	{
		String[] split = search.split("/"); // split the entry into 2
		String userInput; // get first part
		String termInput; // get second part
		try
		{
			userInput = split[0]; // get first part
			termInput = split[1]; // get second part
		}
		catch( ArrayIndexOutOfBoundsException exc)
		{
			UserInterface.sendWarning("You didn't enter enough information", "ERROR");
			UserInterface.getMainWindow().setVisible(true);
			return;
		}
		int promptCtr = 0; // Counter for seeing how many time the user was prompted
		LinkedList<Course> returnResults = null; // Results from the course search
		int deletedCounter = 0; // For the number of edited courses
		Database targetDatabase = Prog5.courseData; // get the database
		try
		{
			returnResults = new CourseSearch().findByNumber(userInput, targetDatabase);
		} 
		catch (NoSuchFieldException exc)
		{ 
			UserInterface.sendWarning("No results were found!", "ERROR");
			UserInterface.getMainWindow().setVisible(true);
			return;
		}
		for(int index=0;index<returnResults.size();)
		{
			// Print the course as long as it matches year and term
			if(termInput.equalsIgnoreCase(returnResults.get(index).getTermTakenRaw()))
			{
				if(JOptionPane.showConfirmDialog(null, 
						 "Would you like to delete: " + 
						 printCourse(returnResults, index)) == 0)
				{
					for(int index2=0;index2<targetDatabase.getDatabaseSize();index2++)
					{
						if(targetDatabase.get(index2).getTerm().equals(
								returnResults.get(index).getTermTakenRaw()))
						{
							// remove course from lower list
							targetDatabase.remove(index2, returnResults.get(index));
							deletedCounter++;
							break; // exit for loop as term search is done
						}
					}
				}
				else{/* do nothing */}
				promptCtr++;
			}
			index += 2; // because of storage in results add 2 instead of 1
		}
		if(promptCtr>0)
			UserInterface.sendMessage("You deleted " + deletedCounter + 
					" course(s)!\n", "INFO");
		else
			UserInterface.sendMessage(
					"There were no courses with the specified term!\n", "INFO");
		UserInterface.getMainWindow().setVisible(true);
	}
	
	/*************************************************************
	* Method: createRemoveSearchWindow()                         *
	* Purpose: window for entry of course info		             *
	* Parameters: void:				 N/A						 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void createRemoveSearchWindow()
	{
		// New window for search info
		JFrame searchWindow = new JFrame("Remove SearchWindow");
		
		searchWindow.setSize(400,100); // set the size
		GridLayout aGrid = new GridLayout(3,2,0,0); // config the layout
		searchWindow.setLayout(aGrid); // set window layout
		
		// create buttons
		JButton crsSearch 	= new JButton("Search");
		JButton close = new JButton("Close");
		
		// create lables
		JLabel crsNumLbl = new JLabel("Course Number:");
		JLabel crsSemesterLbl = new JLabel("Course Semester (yyyytt):");
		
		// create 
		JTextField crsNum = new JTextField();
		JTextField crsSemester = new JTextField();
		
		// add objects to the window's
		searchWindow.add(crsNumLbl);
		searchWindow.add(crsNum);
		searchWindow.add(crsSemesterLbl);
		searchWindow.add(crsSemester);
		searchWindow.add(crsSearch);
		searchWindow.add(close);
		
		crsSearch.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  userRemoveCourseWork(crsNum.getText() + "/" + crsSemester.getText());
				  searchWindow.dispose();
			  }
		});
		
		close.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  searchWindow.dispose();
				  UserInterface.getMainWindow().setVisible(true);
			  }
		});
		
		searchWindow.setVisible(true);
	}
}
