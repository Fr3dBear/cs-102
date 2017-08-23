import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;
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
			System.out.println("No arguments given!");
			System.exit(1);
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("File could not be opened.");
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
			}
			catch(ParseException exc)
			{
				System.out.println(exc.getMessage() + "\nError occured on line: " + fileLine +
								   " Line is being ignored and not added to array.");
			}
			// Add the new course to the database
			this.addCourse(tempCourse);
		}
		if(courseList.isEmpty())
		{
			throw new IllegalArgumentException("The database is empty!");
		}
		else {/* do nothing */}
	}
	
	/*************************************************************
	* Method: addCourse()                 		                 *
	* Purpose: manually add a course to the database	         *
	*          							                         *
	* Parameters:                                                *
	*	Course: newCourse: 	course to be added					 *
	* Returns: Void:  		nothing to be returned			     
	 * @throws Exception *
	**************************************************************/
	public void addCourse(Course newCourse)
	{
		int insertIndex; // index for course insertion
		if(courseList.isEmpty())
		{
			// Add new course to lower layer
			Term lowerList = new Term(newCourse.getYearTaken()+
					newCourse.getTermTakenRaw());
			lowerList.append(newCourse);
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
	* Method: getArrayPosition()                                 *
	* Purpose: Return data a array position requested	         *
	*          							                         *
	* Parameters:                                                *
	*	String position: 	index to return						 *
	* Returns: Course:  data stored in the position requested    *
	**************************************************************/
	public Course getArrayPosition(int position, int secondIndex) throws ArrayIndexOutOfBoundsException
	{
		if(position <= courseList.size())
		{
			Term returnCourse = courseList.get(position);
			return returnCourse.get(secondIndex);
		}
		// Throw an error that tells asker that they picked an invalid array position
		else{throw new ArrayIndexOutOfBoundsException();}
	}
	
	/*************************************************************
	* Method: getArraySize()	                                 *
	* Purpose: Read file and store data into courseArray         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: int:              the size of the array	         *
	**************************************************************/
	public int getArraySize()
	{
		return courseList.size();
	}
	
	/*************************************************************
	* Method: getArrayCount()	                                 *
	* Purpose: Get the size of course List				         *
	*          							                         *
	* Parameters: int:			index                            *
	* Returns: int:             number of elements in list       *
	**************************************************************/
	public int getArrayCount(int index)
	{
		return (courseList.get(index).size());
	}
	
	/*************************************************************
	* Method: checkIfTermExists()                                *
	* Purpose: add course to low level linkedlist		         *
	*          							                         *
	* Parameters: LinkedList, Course:		lowerList courseIn   *
	* Returns: void:            N/A							     *
	**************************************************************/
	private void addCourse(Term lowerList, Course courseIn)
	{
		for(int index=0;index<lowerList.size();index++)
		{
			String tempCourse = ((Course) lowerList.get(index)).getCourseNumber();
			if(tempCourse.equals(courseIn.getCourseNumber()))
			{ 
				System.out.print("Course Already Exsists!"); 
				return;
			}
			// check to see if current index is smaller
			else if(tempCourse.compareToIgnoreCase(courseIn.getCourseNumber()) > 0)
			{
				lowerList.add(index, courseIn);
				
				return;
			}
			else { /* do nothing */ }
		}
		// Its the lowest, add to bottom
		lowerList.append(courseIn);
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
			String tempCourse = courseIn.getYearTaken()+courseIn.getTermTakenRaw();
			if(tempCourse.equals(courseList.get(index).getTerm()))
			{
				/* term already exists so just return index */
				return index;
			}
			else if ((courseList.get(index).getTerm().compareToIgnoreCase(tempCourse) > 0))
			{
				courseList.add(index, new Term(courseIn.getYearTaken()+
						courseIn.getTermTakenRaw()));
				return index;
			}
		}
		// small then all add to end
		courseList.addLast(new Term(courseIn.getYearTaken()+
						   courseIn.getTermTakenRaw()));
		return (courseList.size()-1);
	}
	
	/*************************************************************
	* Method: get()				                                 *
	* Purpose: get list position		         				 *
	*          							                         *
	* Parameters: int:			index   						 *
	* Returns: Object:          N/A							     *
	**************************************************************/
	public Term get(int index)
	{
		return(courseList.get(index));
	}
	
	/*************************************************************
	* Method: remove()			                                 *
	* Purpose: remove a course from database       				 *
	*          							                         *
	* Parameters: int:			index,index 					 *
	* Returns: void:            N/A							     *
	**************************************************************/
	public void remove(int index, int index2)
	{
		try
		{
			Term lowerList = courseList.get(index);
			lowerList.remove(index2);
			if(lowerList.size()==0)
				courseList.remove(index);
		}
		catch(IndexOutOfBoundsException exc)
		{ System.out.println("Index is out of bounds!");}
	}
}
