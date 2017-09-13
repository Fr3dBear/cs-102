import java.io.IOException;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* TermInterface class:  general front end for term			 *
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
	* Method: get()			                                 	 *
	* Purpose: get object from linked list at index		         *
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Object:           Object stored in index			 *
	**************************************************************/
//	public Course get(int index);
	
	/*************************************************************
	* Method: add()			                                 	 *
	* Purpose: add a object at specified index			         *        
	*          							                         *
	* Parameters: 												 *
	* 			  Course:		 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void add(Course item) throws IOException;
	
	/*************************************************************
	* Method: remove()			                               	 *
	* Purpose: remove item from tree						     *  
	*          							                         *
	* Parameters: Course:      course to remove                	 *
	* Returns: void:           nothing is returned				 *
	**************************************************************/
	public void remove(Course item);
	
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
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: String		   the term							 *
	**************************************************************/
	public String getTerm();
}
