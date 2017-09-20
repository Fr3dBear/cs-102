/**************************************************************
* Dalton Nofs                                                 *
* Login ID: nofs5491                                          *
* CS-102, Summer 2017                                         *
* Programming Assignment 5                                    *
* GpaCalc class:  calculator used to find database gpa		  *
**************************************************************/
public class GpaCalc
{
	/*************************************************************
	* Method: calcGpa()	         	            	             *
	* Purpose: Calculate gpa for given database			         *
	*          							                         *
	* Parameters: 												 *
	* 		Database: targetDatabase: database to calc gpa	 	 *
	* Returns: 													 *
	* 		double: the calculated gpa		 					 *
	**************************************************************/
	public double calcGpa(Database targetDatabase) throws IllegalArgumentException
	{
		int totalCredits = 0; // Total credits on not excluded classes
		double creditGpa = 0; // Running total for credit * class grade
		
		// Check the status of the database
		if(targetDatabase.getDatabaseSize() <= 0)
		{
			throw new IllegalArgumentException("N/A\nDatabase is empty!\n");
		}
		
		for(int index=0; index<targetDatabase.getDatabaseSize(); index++)
		{
			creditGpa += gatherGpa(targetDatabase.get(index).getRoot());
			totalCredits += gatherCredits(targetDatabase.get(index).getRoot());
		}
		// Calc final database gpa
		return (creditGpa/totalCredits);
	}
	
	/*************************************************************
	* Method: getClassGrade()      	            	             *
	* Purpose: figure out what the gpa value from string 	     *
	*          							                         *
	* Parameters: 												 *
	* 		Course: targetCourse: course to find the grade	 	 *
	* Returns: 													 *
	* 		double: the calculated gpa		 					 *
	**************************************************************/
	double getClassGrade(Course targetCourse) throws IllegalArgumentException
	{
		double courseGrade = 0;
		// Switch for checking the uppercase version of the grade
		switch(targetCourse.getCourseGrade().toUpperCase())
		{
			case "A": 	courseGrade = 4.0;
					  	break;
					  	
			case "A-": 	courseGrade = 3.7;
			  		   	break;
			  		   	
			case "B+": 	courseGrade = 3.3;
			  		   	break;
			  		   	
			case "B": 	courseGrade = 3.0;
  		  				break;
			  		   	
			case "B-": 	courseGrade = 2.7;
			  		   	break;
			  		  	
			case "C+": 	courseGrade = 2.3;
			  		   	break;
			  		   	
			case "C": 	courseGrade = 2.0;
			  		   	break;
			  		   	
			case "C-": 	courseGrade = 1.7;
			  		   	break;
			  		   	
			case "D+": 	courseGrade = 1.3;
						break;
						
			case "D":	courseGrade = 1.0;
						break;
						
			case "F":	courseGrade = 0.0;
						break;
						
			// Catch all non compiant grades			
			default:	throw new IllegalArgumentException("Grade is not correct!");
		}
		// Return found grade if exception is not thrown first
		return courseGrade;
	}
	
	/*************************************************************
	* Method: gatherGpa()	*private*                     	     *
	* Purpose: gathers grades from tree					         *
	*          							                         *
	* Parameters: TreeNode:		current node	             	 *
	* Returns: float:         	partal gpa from tree			 *
	**************************************************************/
	private double gatherGpa(TreeNode<Course> current)
	{
		double gpa = 0; // running count of gpa
		if(current == null) {return gpa;} // if fallen off list
		
		// Check to see if exclude flag is set
		if(current.getDatum().getExcludeFlag().toUpperCase().equals("N"))
		{
			// Get grade and multiply by the credit count for the top of the gpa calc
			try
			{
				// add to the total credit count
				gpa = (getClassGrade(current.getDatum()) *
						current.getDatum().getCreditCount());
			}
			catch(IllegalArgumentException exc)
			{
				if(current.getDatum().getCourseGrade().toUpperCase().equals("CR") ||
				   current.getDatum().getCourseGrade().toUpperCase().equals("I"))
				{
					// do nothing
					throw new IllegalArgumentException(
							"N/A\nThe grade for " + 
							current.getDatum().getCourseNumber() +
							" is \"" + current.getDatum().getCourseGrade() +
							"\" which is not applicable for GPA calculations!\n");
				}
				else
				{
					throw new IllegalArgumentException(
							"N/A\nThe grade for " + 
							current.getDatum().getCourseNumber() +
							" is \"" + current.getDatum().getCourseGrade() +
							"\" is invalid!\n");
				}
			}
		}
		else{/* do nothing */}
		
		// gather the rest of the left till null
	    gpa += gatherGpa(current.getRight());
	    // gather the rest of the right till null
		gpa += gatherGpa(current.getLeft());
		return gpa;
	}
	
	/*************************************************************
	* Method: gatherCredits()	*private*                  	     *
	* Purpose: gathers total credits from tree			         *
	*          							                         *
	* Parameters: TreeNode:		current node	             	 *
	* Returns: float:         	num of credits					 *
	**************************************************************/
	private int gatherCredits(TreeNode<Course> current)
	{
		int totalCredits = 0; // running count of credits
		if(current == null) {return totalCredits;} // if fallen off list
		
		// Check to see if exclude flag is set
		if(current.getDatum().getExcludeFlag().toUpperCase().equals("N"))
		{
			totalCredits += current.getDatum().getCreditCount();
		}
		else{/* do nothing */}
		
		// gather the rest of the left till null
	    totalCredits += gatherCredits(current.getRight());
	    // gather the rest of the right till null
		totalCredits += gatherCredits(current.getLeft());
		return totalCredits; // return after searching left and right
		
	}
}
