/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 3                                   *
* ListInterface class:  general front end for term			 *
**************************************************************/
public interface TermInterface
{    
	/*************************************************************
	* Method: isEmpty()			                                 *
	* Purpose: check to see if linkedList is empty		         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: boolean:          if list is empty				 *
	**************************************************************/
	public boolean isEmpty();
	
	/*************************************************************
	* Method: size()			                                 *
	* Purpose: determine the size of linked list		         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: int:          	 the size of the array			 *
	**************************************************************/
	public int size();
	
	/*************************************************************
	* Method: get()			                                 	 *
	* Purpose: get object from linked list at index		         *
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Object:           Object stored in index			 *
	**************************************************************/
	public Course get(int index);
	
	/*************************************************************
	* Method: add()			                                 	 *
	* Purpose: add a object at specified index			         *        
	*          							                         *
	* Parameters: 												 *
	* 			  int:           index                           *
	* 			  Object:		 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void add(int index, Course item);
	
	/*************************************************************
	* Method: remove()			                               	 *
	* Purpose: remove index postion and return object removed    *
	*															 *
	* Notes: calls func that can throw indexoutboundsexception	 *         
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Object:           Object removed					 *
	**************************************************************/
	public Course remove (int index);
	
	/*************************************************************
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: void:           N/A								 *
	**************************************************************/
	public void removeAll();
	
	/*************************************************************
	* Method: append									 		 *
	* 															 *
	* Purpose: appends the course to the end of the list	     *
	*          							                         *
	* Parameters: Course: courseIn  				             *
	* Returns: void:            N/A								 *
	**************************************************************/
	public void append(Course courseIn);
	
	/*************************************************************
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: String		   the term							 *
	**************************************************************/
	public String getTerm();
}
