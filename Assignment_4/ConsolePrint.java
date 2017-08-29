/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 4                                   *
* ConsolePrint class:  general use program printer			 *
**************************************************************/

public class ConsolePrint
{	
	/*************************************************************
	* Method: printDatabase()	                                 *
	* Purpose: writes out the entire database			         *
	*          							                         *
	* Parameters:   Database: printBase: database to be printed	 *
	* Returns: Void:             nothing will be returned        *
	**************************************************************/
	public void printDatabase(Database printBase) throws IllegalArgumentException
	{
		String buffer = ""; // Buffer string for data to be sent to console
		int arrayCount = 0; // local arraycount for cycling through the database
		arrayCount = printBase.getDatabaseSize();
		
		// Check the status of the database
		if(arrayCount <= 0)
		{
			throw new IllegalArgumentException("Database is empty!\n");
		}
		
		// Loop semesters
		for(int index=0; index<arrayCount; index++)
		{
			buffer += gather(printBase.get(index).getRoot());
		}
		System.out.println(buffer); // Print the entire database
		buffer = ""; // clear the buffer
	}

	/*************************************************************
	* Method: gather()	*private* 	                     	     *
	* Purpose: fills buffer with tree					         *
	*          							                         *
	* Parameters: TreeNode:		current node	             	 *
	* Returns: String:         	compiled buffer from tree		 *
	**************************************************************/
	private String gather(TreeNode<Course> current)
	{
		String buffer = "";
		if(current == null) {return buffer;} // if fallen off list
		buffer += current.getDatum().getCourseNumber() + ": " +
				  current.getDatum().getCourseTitle()  + " (" +
				  current.getDatum().getCreditCount()  + "). "+
				  current.getDatum().getTermTaken()	   + " "  +
				  current.getDatum().getYearTaken()	   + " "  +
				  current.getDatum().getCourseGrade()  + ""	;
		// Check to see if excluded from GPA calc
		if(current.getDatum().getExcludeFlag().equals("Y") || 
		   current.getDatum().getExcludeFlag().equals("y"))
		{
			buffer += " (excluded).\n";
		}
		else{buffer += ".\n";}
		// gather the rest of the left till null
	    buffer += gather(current.getRight());
	    // gather the rest of the right till null
		buffer += gather(current.getLeft());
		return buffer;
	}

}
