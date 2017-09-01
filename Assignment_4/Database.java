import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.Scanner;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 4                                   *
* Database class:  storage location of all classes imported  *
**************************************************************/
public class Database
{
	LinkedList<Term> courseList = new LinkedList<Term>(); // Storage location for data read from file
	
	/*************************************************************
	* Method: loadDatabase()	                                 *
	* Purpose: Read file and store data into courseArray         *
	*          							                         *
	* Parameters:                                                *
	*    		String args:	 Pram's passed at program start  *
	*    						   which notes file location     *
	* Returns: Void:             nothing will be returned        *
	**************************************************************/
	public void loadDatabase(String[] args) throws ArrayIndexOutOfBoundsException, IllegalArgumentException
	{
		Scanner file = null; // File scanner
		
		try
		{
			file = new Scanner(new File(args[0]));
		}
		catch(ArrayIndexOutOfBoundsException exc)
		{
			System.out.println("No arguments given! Terminating program!");
			System.exit(1);
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("File could not be opened. Terminating program!");
			System.exit(2);
		}
		
		while(file.hasNext())
		{
			String fileLine = file.nextLine();  	// Line read from file
			Scanner pieces = new Scanner(fileLine); // Split the line
			String dateString = ""; 				// Temp string for separating the year and semester
			Course tempCourse = new Course(); 		// Temp course obj for storing imported data until obj
													//    is added to the array
			
			// setTermTaken, and setExcludeFlag will throw a parse error
			//     if data sent is not in the correct format
			try
			{
				pieces.useDelimiter("/");
				dateString = pieces.next();
				if(dateString.length() != 6) 
				{
					throw new ParseException("Year/Term is wrong length", courseList.size()+1);
				}
				tempCourse.setYearTaken(dateString.substring(0, 4)); // Set year to string char's 0-4 (year)
				tempCourse.setTermTaken(dateString.substring(4, 6)); // Set term taken to the 2 digit semester code
				tempCourse.setCourseNumber(pieces.next());			 // Set the course number
				tempCourse.setCreditCount(pieces.nextInt());		 // Set the number of credits the class is worth
				tempCourse.setCourseTitle(pieces.next());			 // Set the course title
				tempCourse.setCourseGrade(pieces.next());			 // Set the course grade
				tempCourse.setExcludeFlag(pieces.next());			 // Set the exclude flag
				// Add the new course to the database
				this.addCourse(tempCourse);
			}
			catch(ParseException exc)
			{
				System.out.println(exc.getMessage() + "\nError occured on line: " + fileLine +
								   " : Line is being ignored and not added to array.");
			}
		}
		if(courseList.isEmpty())
		{
			throw new IllegalArgumentException("\nThe database is empty!\n");
		}
		else {/* do nothing */}
	}
	
	/*************************************************************
	* Method: storeDatabase()	                                 *
	* Purpose: Read file and store data into courseArray         *
	*          							                         *
	* Parameters:                                                *
	*  		String fileName:	 stores database to file		 *
	* Returns: Void:             N/A					         *
	**************************************************************/
	public void storeDatabase(String fileName)
	{
		String buffer = ""; // buffer for file write
		// local arraycount for cycling through the database
		int arrayCount = this.getDatabaseSize();
		
		// Check the status of the database
		if(arrayCount <= 0)
		{
			throw new IllegalArgumentException("Database is empty!\n");
		}
		
		// Loop semesters
		for(int index=0; index<arrayCount; index++)
		{
			buffer += gatherPrint(this.get(index).getRoot());
		}
		try
		{
			// configure the writer for writing to file
			Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream(fileName), "utf-8"));
			writer.write(buffer); // try writing to the file
			writer.close(); // Close the file
		}
		catch(IOException exc)
		{
			System.out.println("There was an error writing to the file!");
		}
		buffer = ""; // clear the buffer
		System.out.println("\nSave successfull!\n");
	}
	
	/*************************************************************
	* Method: addCourse()                 		                 *
	* Purpose: manually add a course to the database	         *
	*          							                         *
	* Parameters:                                                *
	*	Course: newCourse: 	course to be added					 *
	* Returns: Void:  		nothing to be returned			     
	**************************************************************/
	public void addCourse(Course newCourse)
	{
		int insertIndex; // index for course insertion
		if(courseList.isEmpty())
		{
			// Add new course to lower layer
			Term lowerList = new Term(newCourse.getTermTakenRaw());
			try 
			{
			lowerList.add(newCourse);
			}
			catch(IOException exc)
			{
				System.out.println("Course already exists!");
			}
			// Add new linkedList to upper layer
			courseList.add(0, lowerList);
		}
		else
		{
			//Check to see if term exists, if exists then index is returned
			insertIndex = addTerm(newCourse);
			// add new course to lower layer
			Term lowerList = courseList.get(insertIndex);
			// Add the course to list
			addCourse(lowerList, newCourse);
		}
	}
	
	/*************************************************************
	* Method: getDatabaseSize()	                                 *
	* Purpose: return the size of the database			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: int:              the size of the database        *
	**************************************************************/
	public int getDatabaseSize()
	{
		return courseList.size();
	}
	
	/*************************************************************
	* Method: checkIfTermExists()                                *
	* Purpose: add course to low level tree		         *
	*          							                         *
	* Parameters: LinkedList, Course:		lowerList courseIn   *
	* Returns: void:            N/A							     *
	**************************************************************/
	private void addCourse(Term lowerList, Course courseIn)
	{
		try
		{
			lowerList.add(courseIn);
		}
		catch(IOException exc)
		{
			System.out.println("Course already exists!");
		}
	}
	
	/*************************************************************
	* Method: checkIfTermExists()                                *
	* Purpose: add course to low level linkedlist		         *
	*          							                         *
	* Parameters: Course:	   courseIn   						 *
	* Returns: int:            index of added term			     *  
	**************************************************************/
	private int addTerm(Course courseIn)
	{
		for(int index=0;index<courseList.size();index++)
		{
			// Create the 201704 string
			String tempCourse = courseIn.getTermTakenRaw();
			if(tempCourse.equals(courseList.get(index).getTerm()))
			{
				/* term already exists so just return index */
				return index;
			}
			else if ((courseList.get(index).getTerm().compareToIgnoreCase(tempCourse) > 0))
			{
				courseList.add(index, new Term(courseIn.getTermTakenRaw()));
				return index;
			}
		}
		// small then all add to end
		courseList.addLast(new Term(courseIn.getTermTakenRaw()));
		return (courseList.size()-1);
	}
	
	/*************************************************************
	* Method: get()				                                 *
	* Purpose: get list position		         				 *
	*          							                         *
	* Parameters: int:			index   						 *
	* Returns: Object:          item stored at index		     *
	**************************************************************/
	public Term get(int index)
	{
		return(courseList.get(index));
	}
	
	/*************************************************************
	* Method: remove()			                                 *
	* Purpose: remove a course from database       				 *
	*          							                         *
	* Parameters: int: Course:	index, obj to be removed		 *
	* Returns: void:            N/A							     *
	**************************************************************/
	public void remove(int index, Course target)
	{
		try
		{
			Term lowerList = courseList.get(index);
			lowerList.remove(target);
			if(lowerList.isEmpty())
				courseList.remove(index);
		}
		catch(IndexOutOfBoundsException exc)
		{ System.out.println("Index is out of bounds!");}
	}
	
	/*************************************************************
	* Method: removeAll()			                                 *
	* Purpose: remove a course from database       				 *
	*          							                         *
	* Parameters: int: Course:	index, obj to be removed		 *
	* Returns: void:            N/A							     *
	**************************************************************/
	public void removeAll()
	{
		// de link the old list
		courseList.removeAll();
		courseList = new LinkedList<Term>();
	}
	
	/*************************************************************
	* Method: gatherPrint()	*private*                      	     *
	* Purpose: fills buffer with tree info				         *
	*          							                         *
	* Parameters: TreeNode:		current node	             	 *
	* Returns: String:         	compiled buffer from tree		 *
	**************************************************************/
	private String gatherPrint(TreeNode<Course> current)
	{
		String buffer = "";
		if(current == null) {return buffer;} // if fallen off list
		// follow format yyyytt/cs-num/cred/title/grade/include
		buffer += current.getDatum().getTermTakenRaw() + "/" +
				  current.getDatum().getCourseNumber() + "/" +
				  current.getDatum().getCreditCount()  + "/" +
				  current.getDatum().getCourseTitle()  + "/" +
				  current.getDatum().getCourseGrade()  + "/" +
				  current.getDatum().getExcludeFlag()  + "\r\n";
						// for return in file use \r\n
		// gather the rest of the left till null
	    buffer += gatherPrint(current.getRight());
	    // gather the rest of the right till null
		buffer += gatherPrint(current.getLeft());
		return buffer;
	}
}
