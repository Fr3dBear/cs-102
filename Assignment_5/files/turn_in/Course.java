import java.text.ParseException;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* Course class:  default class for Course objects		     *
**************************************************************/
public class Course implements Comparable<Course>
{
	int creditCount;	 // Number of credits the class is worth (1-4)
	String termTaken;    // Term and year class was taken
	String termTakenRaw; // Term taken raw format "01,02,03,04"
	String yearTaken;	 // Year course was taken
	String courseNumber; // Course number ie: CS-471
	String courseTitle;  // Title of course ie: Software Engineering 1
	String courseGrade;  // Grade achieved by student ("A-" or "I", "CR")
	String excludeFlag;  // Flag for checking to see if grade should be in gpa calc
	
	/*************************************************************
	* Method: get[private_var]()                                 *
	* Purpose: Provide the ability to access private Course      *
	*          variables				                         *
	* Parameters:                                                *
	*    			             N/A					         *
	* Returns: String/Int:       private variable value	         *
	**************************************************************/
	public int getCreditCount()
	{
		return creditCount;
	}
	public String getTermTaken()
	{
		return termTaken;
	}
	public String getTermTakenRaw()
	{	
		// raw form, only 01,02,03,04 is stored, however
		//     for true raw we want year as well so return it
		return (yearTaken+termTakenRaw);
	}
	public String getYearTaken()
	{
		return yearTaken;
	}
	public String getCourseNumber()
	{
		return courseNumber;
	}
	public String getCourseTitle()
	{
		return courseTitle;
	}
	public String getCourseGrade()
	{
		return courseGrade;
	}
	public String getExcludeFlag()
	{
		return excludeFlag;
	}
	
	/*************************************************************
	* Method: set[private_var]()                                 *
	* Purpose: Provide the ability to set private Course         *
	*          variables				                         *
	* Parameters:                                                *
	*   String/Int: [private_var]:	    value to be set	         *
	* Returns: 							N/A				         *
	**************************************************************/
	public void setCreditCount(int creditCount)
	{
		this.creditCount = creditCount;
	}
	public void setTermTaken(String termTaken) throws ParseException
	{
		// Set term taken for raw then find the word value
		if(termTaken.equals("01"))
		{
			this.termTakenRaw = "01";
			this.termTaken = "Winter";
		}
		else if(termTaken.equals("02"))
		{
			this.termTakenRaw = "02";
			this.termTaken = "Spring";
		}
		else if(termTaken.equals("03"))
		{
			this.termTakenRaw = "03";
			this.termTaken = "Summer";
		}
		else if(termTaken.equals("04"))
		{
			this.termTakenRaw = "04";
			this.termTaken = "Fall";
		}
		// Throw flag to show data passed is incorrect
		else {throw new ParseException("Term is not correct!", -1);}
	}
	public void setYearTaken(String yearTaken)
	{
		this.yearTaken = yearTaken;
	}
	public void setCourseNumber(String courseNumber)
	{
		this.courseNumber = courseNumber;
	}
	public void setCourseTitle(String courseTitle)
	{
		this.courseTitle = courseTitle;
	}
	public void setCourseGrade(String courseGrade) throws ParseException
	{
		// Cheack to see if matches grading scale
		if("ABCDFICRAUB+C+D+A-B-C-".contains(courseGrade.toUpperCase()))
			this.courseGrade = courseGrade;
		else
			throw new ParseException("Grade is not acceptable!", -1);
	}
	public void setExcludeFlag(String excludeFlag) throws ParseException
	{
		if(excludeFlag.equals("Y") || excludeFlag.equals("y") ||
		   excludeFlag.equals("N") || excludeFlag.equals("n"))
		{
			this.excludeFlag = excludeFlag;
		}
		// Throw flag to show data passed is incorrect
		else {throw new ParseException("Exclude flag is not correct!", -1);}
	}
	
	/*************************************************************
	* Method: compareTo()		                                 *
	* Purpose: to compare classes		                         *
	* Parameters:                                                *
	*   Course:	    			Course to be compared	         *
	* Returns: int: 			-1 is, compare in is less        *
	* 							0 is compare in is ==			 *
	* 							1 is compare is greater			 *
	**************************************************************/
	public int compareTo(Course compareIn)
	{
		return compareIn.getCourseNumber().compareToIgnoreCase(this.getCourseNumber());
	}
}