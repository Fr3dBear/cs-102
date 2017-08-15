import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 3                                   *
* Assignment class:  main entry point for assignment 3       *
**************************************************************/
public class Prog3 
{
	public static void main (String[] args)
	{
		ConsolePrint localPrinter = new ConsolePrint(); 	// local printer for general printing
		CourseSearch courseSearcher = new CourseSearch(); 	// Database searcher for course's
		GpaCalc gpaCalculator = new GpaCalc();				// Calcualtor for gpa via database
		Scanner console = new Scanner(System.in); // Scanner for parsing the console
		int userInput = 0; 						  // User input for selecting option
		Database courseData = new Database();     // Create a database to import to
		// String for printing the greeting message
		String greetingString = "Welcome to the CS-102 Transcript Program" +
								"\nCurrent available commands:" 		   +
								"\n    1 --> Search for a course number"   +
								"\n    2 --> Search course titles"         +
								"\n    3 --> Print all records" 		   +
								"\n    4 --> Computer GPAs" 			   +
								"\n    5 --> Add Course"				   +
								"\n    6 --> Remove Course"				   +
								"\n    9 --> Exit"						   ;
		
		final int choice1 = 1; // Course by number
		final int choice2 = 2; // Course by title
		final int choice3 = 3; // Print all records
		final int choice4 = 4; // Computer GPAs
		final int choice5 = 5; // Add a course
		final int choice6 = 6; // Remove a course
		final int choice7 = 7; // Edit a course
		final int choice9 = 9; // Exit
		try
		{
			// attempt to load the database with file location
			//   given at runtime
			courseData.loadDatabase(args);
		}
		catch(ArrayIndexOutOfBoundsException exc)
		{
			System.out.println("The database is full, " + exc.getMessage() + " course(s) were loaded!");
		}
		catch(IllegalArgumentException exc)
		{
			System.out.println(exc.getMessage());
		}
		
		System.out.println("Database has been loaded!\n");
		
		while(true)
		{
			System.out.println(greetingString); // Print welcome message
			try
			{
				userInput = console.nextInt();
				console.nextLine(); // Clear the scanner's buffer by going to the return char
									//    still viable if a newline char is pasted into terminal
			}
			catch(InputMismatchException exc)
			{
				// Just change the user input to use the default catch
				userInput = 0;
			}
			
			switch (userInput)
			{
				case choice1: System.out.println("What is the Number of the course?: ");
						try
						{
							courseSearcher.findByNumber(console.next(), courseData);
						}
						catch(NoSuchFieldException exc)
						{
							System.out.println("Course was not found!\n");
						}
						break;
				
				case choice2: System.out.println("What is the title of the course?: ");
						try
						{
							courseSearcher.findByTitle(console.next(), courseData);
						}
						catch(NoSuchFieldException exc)
						{
							System.out.println("Course was not found!\n");
						}
						break;
				
				case choice3: System.out.println("Printing all records\n");
						try
						{
							localPrinter.printDatabase(courseData);
						}
						catch(IllegalArgumentException exc)
						{
							System.out.println(exc.getMessage());
						}
						break;
				
				case choice4: System.out.print("Students GPA: ");
						try
						{
							System.out.format("%.2f\n\n",gpaCalculator.calcGpa(courseData));
						}
						catch(IllegalArgumentException exc)
						{
							System.out.println(exc.getMessage());
						}
						break;
				
				case choice5: userAddCourse(courseData, console);
							  break;
				
				case choice6: userRemoveCourse(courseData, console, courseSearcher);
							  break;
							  
				//case choice7: userEditCourse(courseData, console, courseSearcher);
				//			  break;
						
				case choice9: System.out.println("Exiting");
							  System.exit(0); // exit successfully
				
				// Catch all (commands that are not 1-4,9)
				default: System.out.println("The command you entered is not recognized.\n");
						 break;
			}
		}
	}
	
	/*************************************************************
	* Method: userAddClass()	                                 *
	* Purpose: add a user class to database 			         *
	*          							                         *
	* Parameters: Database: Scanner: targetbase, console scanner *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void userAddCourse(Database targetDatabase, Scanner console)
	{
		System.out.println("Enter your class in the following format:\n"  +
						   "201003/CE-320/4/Microcomputers I/B+/N\n"      +
						   "yyyytt/corsnum/credit/title/grade/excluded\n" +
						   "yyyy is year tt is term (01,02,03,04)     "  );
		String userInput = ""; // User input string to be feed to addCourse
		Course tempCourse = new Course(); // course to be added
		String dateString = ""; // Temp string for separating the year and semester
		userInput = console.nextLine(); // get users input
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
			tempCourse.setCourseGrade(pieces.next());			 // Set the course grade
			tempCourse.setExcludeFlag(pieces.next());			 // Set the exclude flag
		}
		catch(ParseException exc)
		{
			System.out.println(exc.getMessage() + " Your input is ignored!\n");
			return;
		}
		catch(InputMismatchException exc) 
		{
			System.out.println(exc.getMessage()+"your input is ignored\n");
			return;
		}
		targetDatabase.addCourse(tempCourse);
		System.out.println("\nDone adding courses!\n");
	}
	
	/*************************************************************
	* Method: userRemoveCourse()	                             *
	* Purpose: remove a user class to database 			         *
	*          							                         *
	* Parameters: Database: Scanner: targetbase, console scanner *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private static void userRemoveCourse(Database targetDatabase, Scanner console,
			CourseSearch courseSearcher)
	{
		System.out.println("Please enter the course number you would like to remove:");
		String userInput = console.next(); // users course input
		console.nextLine(); // Clear the scanner's buffer by going to the return char
							//    still viable if a newline char is pasted into terminal
		System.out.println("Please enter the term you would like to remove it from " +
				           "(yyyytt): ");
		String termInput = console.next(); // capture the term to delete from
		console.nextLine(); // Clear the scanner's buffer by going to the return char
							//     still viable if a newline char is pasted into terminal
		LinkedList<Integer> returnResults;
		int deletedCounter = 0; // counter for number of courses deleted
		try
		{
			returnResults = courseSearcher.findByNumber(userInput, targetDatabase);
		} 
		catch (NoSuchFieldException exc)
		{ 
			System.out.println("No results were found!");
			return;
		}
		// return results are stored resut[1] = index 1, reult[2] index2
		for(int index=0;index<returnResults.size();)
		{
			// Print the course as long as it matches the term
			if(termInput.equalsIgnoreCase(targetDatabase.getArrayPosition(
					((int) returnResults.get(index)),
					((int)returnResults.get(index+1))).getTermTakenRaw()))
			{
				System.out.print(
					targetDatabase.getArrayPosition(((int) returnResults.get(index)),
							((int)returnResults.get(index+1))).getCourseNumber() + ": " +
					targetDatabase.getArrayPosition(((int) returnResults.get(index)),
							((int)returnResults.get(index+1))).getCourseTitle()  + " (" +
					targetDatabase.getArrayPosition(((int) returnResults.get(index)),
							((int)returnResults.get(index+1))).getCreditCount()  + "). "+
				  	targetDatabase.getArrayPosition(((int) returnResults.get(index)),
				  			((int)returnResults.get(index+1))).getTermTaken()	 + " "  +
				  	targetDatabase.getArrayPosition(((int) returnResults.get(index)),
				  			((int)returnResults.get(index+1))).getYearTaken()	 + " "  +
				  	targetDatabase.getArrayPosition(((int) returnResults.get(index)),
				  			((int)returnResults.get(index+1))).getCourseGrade()  + "\n"	);
				System.out.println("Would you like to delete(y/n):");
				userInput = console.next();
				if(userInput.equalsIgnoreCase("y"))
				{
					// remove course from lower list
					targetDatabase.remove(((int) returnResults.get(index)),
							((int)returnResults.get(index+1)));
					deletedCounter++;
				}
				else{/* do nothing */}
				index += 2; // because of storage in results add 2 instead of 1
			}
		}
		System.out.println("You deleted " + deletedCounter + " course(s)!\n");
	}
	
	/*************************************************************
	* Method: userRemoveCourse()	                             *
	* Purpose: remove a user class to database 			         *
	*          							                         *
	* Parameters: Database: Scanner: targetbase, console,  		 *
	* Returns: void:           		 N/A				         *
	**************************************************************/
	private void userEditCourse(Database targetBase, Scanner console, CourseSearch courseSearcher)
	{
		System.out.println("Please enter the course number you would like to edit:");
		String userInput = console.next(); // users course input
		console.nextLine(); // Clear the scanner's buffer by going to the return char
							//    still viable if a newline char is pasted into terminal
		LinkedList<Integer> returnResults;
		try
		{
			returnResults = courseSearcher.findByNumber(userInput, targetBase);
		} 
		catch (NoSuchFieldException e)
		{ 
			System.out.println("No results were found!");
			return;
		}
	}
}
