/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 3                                   *
* ConsolePrint class:  general use program printer			 *
**************************************************************/

public class ConsolePrint
{
	String buffer = ""; // Buffer string for data to be sent to console
	
	/*************************************************************
	* Method: printDatabase()	                                 *
	* Purpose: writes out the entire database			         *
	*          							                         *
	* Parameters:   Database: printBase: database to be printed	 *
	* Returns: Void:             nothing will be returned        *
	**************************************************************/
	public void printDatabase(Database printBase) throws IllegalArgumentException
	{
		int arrayCount = 0; // local arraycount for cycling through the database
		arrayCount = printBase.getArraySize();
		
		// Check the status of the database
		if(arrayCount <= 0)
		{
			throw new IllegalArgumentException("Database is empty!\n");
		}
		
		// Loop semesters
		for(int index=0; index<arrayCount; index++)
		{
			int numCourses = printBase.get(index).size();
			// Loop courses
			for(int index2=0;index2<numCourses;index2++)
			{
				// Add the class attributes to the buffer
				buffer += printBase.getArrayPosition(index,index2).getCourseNumber() + ": " +
						  printBase.getArrayPosition(index,index2).getCourseTitle()  + " (" +
						  printBase.getArrayPosition(index,index2).getCreditCount()  + "). "+
						  printBase.getArrayPosition(index,index2).getTermTaken()	 + " "  +
						  printBase.getArrayPosition(index,index2).getYearTaken()	 + " "  +
						  printBase.getArrayPosition(index,index2).getCourseGrade()  + ""	;
				
				// Check to see if excluded from GPA calc
				if(printBase.getArrayPosition(index,index2).getExcludeFlag().equals("Y") || 
				   printBase.getArrayPosition(index,index2).getExcludeFlag().equals("y"))
				{
					buffer += " (excluded).\n";
				}
				else{buffer += ".\n";}
			}
		}
		System.out.println(buffer); // Print the entire database
		buffer = ""; // clear the buffer
	}
}
