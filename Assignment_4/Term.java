import java.util.LinkedList;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 4                                   *
* ListInterface class: High level linked list of terms for   * 
* 						database							 *
**************************************************************/
public class Term implements TermInterface
{
	Tree<Course> courseTree;		// Tree of all the courses
	String term;					// The term of the courses
	
	/*************************************************************
	* Method: Term()			                      	         *
	* Purpose: default constructor for linkedList obj	         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	Term()
	{
		courseTree = new Tree<Course>(); // init an empty tree
		term = null;
	}
	/*************************************************************
	* Method: Term()			                         	     *
	* Purpose: constructor passed a string				         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	Term(String term)
	{
		courseTree = new Tree<Course>(); // init a empty tree
		this.term = term;
	}

	/*************************************************************
	* Method: isEmpty()			                                 *
	* Purpose: check to see if linkedList is empty		         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: boolean:          if list is empty				 *
	**************************************************************/
	public boolean isEmpty()
	{
		if(courseTree.isEmpty())
			return true;
		return false;
	}

	/*************************************************************
	* Method: get()			                                 	 *
	* Purpose: get object from linked list at index		         *
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Object:           Object stored in index			 *
	**************************************************************/
	public Course get(int index)
	{
		return(courseTree.get(index));
	}

	/*************************************************************
	* Method: add()			                                 	 *
	* Purpose: add a object at specified index			         *        
	*          							                         *
	* Parameters: 												 *
	* 			  int:           index                           *
	* 			  Course:		 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void add(int index, Course item)
	{
		courseList.add(index, item);
	}

	/*************************************************************
	* Method: remove()			                               	 *
	* Purpose: remove index postion and return object removed    *
	*															 *
	* Notes: calls func that can throw indexoutboundsexception	 *         
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Object:           Object removed					 *
	**************************************************************/
	public Course remove(int index)
	{
		return(courseList.remove(index));
	}

	/*************************************************************
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: void:           N/A								 *
	**************************************************************/
	public void removeAll()
	{
		courseList.removeAll(courseList);
	}
	
	/*************************************************************
	* Method: append									 		 *
	* 															 *
	* Purpose: appends the course to the end of the list	     *
	*          							                         *
	* Parameters: Course: 		courseIn              			 *
	* Returns: void:            N/A								 *
	**************************************************************/
	public void append(Course courseIn)
	{
		courseList.addLast(courseIn);
	}
	
	/*************************************************************
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: String		   the term							 *
	**************************************************************/
	public String getTerm()
	{
		return(this.term);
	}
}
