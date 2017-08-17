import java.util.LinkedList;

/**************************************************************
* Dalton Nofs                                                 *
* Login ID: nofs5491                                          *
* CS-102, Summer 2017                                         *
* Programming Assignment 3                                    *
* CourseSearch class:  used to search a database for a course *
**************************************************************/
public class CourseSearch
{
	/*************************************************************
	* Method: findByTitle()	                     	             *
	* Purpose: Searches database for matching course number      *
	*          							                         *
	* Parameters: 												 *
	* 		String: courseTitle: Course title to search for 	 *
	* 		Database: targetDatabase: database to be searched	 *
	* Returns: 													 *
	* 		Void: nothing to be returned						 *
	**************************************************************/
	public void findByTitle(String courseTitle, Database targetDatabase) throws NoSuchFieldException
	{	
		String buffer = ""; // Out buffer
		
		// Search given database for a matching string
		for(int index=0; index<targetDatabase.getArraySize(); index++)
		{
			int numCourses = targetDatabase.get(index).size();
			// Loop courses
			for(int index2=0;index2<numCourses;index2++)
			{
				if(targetDatabase.getArrayPosition(index,index2).getCourseTitle().
						toLowerCase().contains(courseTitle.toLowerCase()))
				{
					// Add the class attributes to the buffer
					buffer += targetDatabase.getArrayPosition(index,index2).
								getCourseNumber() + ": " +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCourseTitle()  + " (" +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCreditCount()  + "). "+
							  targetDatabase.getArrayPosition(index,index2).
							  	getTermTaken()	 + " "  +
							  targetDatabase.getArrayPosition(index,index2).
							  	getYearTaken()	 + " "  +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCourseGrade()  + "\n"	;
				}
			}
		}
		if(!buffer.equals(""))
		{
			System.out.println("Results:");
			System.out.println(buffer);
			buffer = ""; // clear buffer
		}
		// Throw exception as no match was found
		else{throw new NoSuchFieldException();}
	}
	
	/*************************************************************
	* Method: findByNumber()	                                 *
	* Purpose: Searches database for matching course number      *
	*          							                         *
	* Parameters: 												 *
	* 		String: courseNumber: Course number to search for 	 *
	* 		Database: targetDatabase: database to be searched	 *
	* Returns: 													 *
	* 		LinkedList<Integer>: list of course indexes 		 *
	**************************************************************/
	public LinkedList<Integer> findByNumber(String courseNumber, Database targetDatabase) throws NoSuchFieldException
	{
		String buffer = ""; // Out buffer
		LinkedList<Integer> returnList = new LinkedList<Integer>();
		
		// Search given database for a matching string
		for(int index=0; index<targetDatabase.getArraySize(); index++)
		{
			int numCourses = targetDatabase.get(index).size();
			// Loop courses
			for(int index2=0;index2<numCourses;index2++)
			{
				if(targetDatabase.getArrayPosition(index,index2).getCourseNumber().
						toLowerCase().equals(courseNumber.toLowerCase()))
				{
					// Add the class attributes to the buffer
					buffer += targetDatabase.getArrayPosition(index,index2).
								getCourseNumber() + ": " +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCourseTitle()  + " (" +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCreditCount()  + "). "+
							  targetDatabase.getArrayPosition(index,index2).
							  	getTermTaken()	 + " "  +
							  targetDatabase.getArrayPosition(index,index2).
							  	getYearTaken()	 + " "  +
							  targetDatabase.getArrayPosition(index,index2).
							  	getCourseGrade()  + "\n"	;
					returnList.addLast(index);  // first index
					returnList.addLast(index2); // second index
				}
			}
		}
		if(!buffer.equals(""))
		{
			System.out.println("Results:");
			System.out.println(buffer);
			buffer = ""; // clear buffer
			return returnList;
		}
		// Throw exception as no match was found
		else{throw new NoSuchFieldException();}
	}
}
