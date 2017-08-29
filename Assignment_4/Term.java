import java.io.IOException;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 4                                   *
* Term class: High level binary tree of terms for database   * 
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
	* Method: getSearched()	                                 	 *
	* Purpose: get object from linked list at index		         *
	*          							                         *
	* Parameters:           	 N/A                             *
	* Returns: Object:           Object stored in index			 *
	**************************************************************/
	public Course getSearched()
	{
		return(courseTree.getSearched().getDatum());
	}
	
	/*************************************************************
	* Method: search()			                      	         *
	* Purpose: searches tree for target					         *
	*          							                         *
	* Parameters: T:		 target								 *
	* Returns: boolean:      if found or not					 *
	**************************************************************/
    public boolean search(Course target)
    {
    	return courseTree.search(target);
    }

	/*************************************************************
	* Method: add()			                                 	 *
	* Purpose: add a object at specified index			         *        
	*          							                         *
	* Parameters: 												 *
	* 			  Course:		 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void add(Course item) throws IOException
	{
		courseTree.add(item);
	}

	/*************************************************************
	* Method: remove()			                               	 *
	* Purpose: remove item from tree						     *  
	*          							                         *
	* Parameters: Course:      course to remove                	 *
	* Returns: void:           nothing is returned				 *
	**************************************************************/
	public void remove(Course item)
	{
		courseTree.remove(item);
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
		courseTree.removeAll();
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
	
	/*************************************************************
	* Method: getRoot()									 		 *
	* 															 *
	* Purpose: gets tree root node							     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: TreeNode<T>:	   the root node					 *
	**************************************************************/
	public TreeNode<Course> getRoot()
	{
		return(this.courseTree.getRoot());
	}
}
