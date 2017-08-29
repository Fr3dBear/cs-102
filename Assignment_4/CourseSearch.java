import java.util.LinkedList;

/**************************************************************
* Dalton Nofs                                                 *
* Login ID: nofs5491                                          *
* CS-102, Summer 2017                                         *
* Programming Assignment 4                                    *
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
			buffer += gather(courseTitle, targetDatabase.get(index).getRoot());
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
	* 		LinkedList<Course> search results					 *
	**************************************************************/
	public LinkedList<Course> findByNumber(String courseNumber, Database targetDatabase) throws NoSuchFieldException
	{
		String buffer = ""; // Out buffer
		Course tempCourse = new Course(); // Temp course for searching tree
		LinkedList<Course> returnBuff = new LinkedList<Course>(); // search results
		tempCourse.setCourseNumber(courseNumber);
		
		// Search given database for a matching string
		for(int index=0; index<targetDatabase.getArraySize(); index++)
		{
			if(targetDatabase.get(index).search(tempCourse))
			{
				// Add the class attributes to the buffer
				buffer += targetDatabase.get(index).getSearched().getCourseNumber()
							+ ": " +
						  targetDatabase.get(index).getSearched().getCourseTitle()
						  	+ " (" +
						  targetDatabase.get(index).getSearched().getCreditCount()
						  	+ "). "+
						  targetDatabase.get(index).getSearched().getTermTaken()
						  	+ " "  +
						  targetDatabase.get(index).getSearched().getYearTaken()
						  	+ " "  +
						  targetDatabase.get(index).getSearched().getCourseGrade()
						  	+ "\n"	;
				// add last searched to LinkedList of found items
				returnBuff.addLast(targetDatabase.get(index).getSearched());
			}
		}
		if(!buffer.equals(""))
		{
			System.out.println("Results:");
			System.out.println(buffer);
			buffer = ""; // clear buffer
			return(returnBuff);
		}
		// Throw exception as no match was found
		else{throw new NoSuchFieldException();}
	}
	
	/*************************************************************
	* Method: gather()	*private* 	                     	     *
	* Purpose: fills buffer with tree					         *
	*          							                         *
	* Parameters: String: TreeNode:		target, current node   	 *
	* Returns: String:         	compiled buffer from tree		 *
	**************************************************************/
	private String gather(String target, TreeNode<Course> current)
	{
		String buffer = "";
		if(current == null) {return buffer;} // if fallen off list
		
		if(current.getDatum().getCourseTitle().
				toLowerCase().contains(target.toLowerCase()))
		{
			// Add the class attributes to the buffer
			buffer += current.getDatum().getCourseNumber() + ": " +
					  current.getDatum().getCourseTitle()  + " (" +
					  current.getDatum().getCreditCount()  + "). "+
					  current.getDatum().getTermTaken()	 + " "  +
					  current.getDatum().getYearTaken()	 + " "  +
					  current.getDatum().getCourseGrade();
			// Check to see if excluded from GPA calc
			if(current.getDatum().getExcludeFlag().equals("Y") || 
			   current.getDatum().getExcludeFlag().equals("y"))
			{
				buffer += " (excluded).\n";
			}
			else{buffer += ".\n";}
		}
		// gather the rest of the left till null
	    buffer += gather(target,current.getRight());
	    // gather the rest of the right till null
		buffer += gather(target,current.getLeft());
		return buffer;
	}

}
