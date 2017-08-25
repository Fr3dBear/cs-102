/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 4                                   *
* TreeNode class:  TreeNode object for creating trees		 *
**************************************************************/
class TreeNode<T>
{
    T datum;
    TreeNode<T> left;
    TreeNode<T> right;
    
    /*************************************************************
	* Method: Node()			                                 *
	* Purpose: default constructor for TreeNode obj			     *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public TreeNode()
	{
		datum = null;
		left = null;
		right = null;
	}
	
	/*************************************************************
	* Method: getData()			                                 *
	* Purpose: get previous node or next node			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: T:      data stored in node					     *
	**************************************************************/
	public T getDatum()
	{
		return datum;
	}
	
	/*************************************************************
	* Method: setDatum()			                                 *
	* Purpose: get previous node or next node			         *
	*          							                         *
	* Parameters: Object:		 Data to be stored in node       *
	* Returns: void:             N/A							 *
	**************************************************************/
	public void setDatum(T datum)
	{
		this.datum = datum;
	}
	
	/*************************************************************
	* Method: getLeft()/getRight()	                             *
	* Purpose: get left node or right node				         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: Node:             Node pointing to next or prev   *
	**************************************************************/
	public TreeNode<T> getLeft()
	{
		return left;
	}
	public TreeNode<T> getRight()
	{
		return right;
	}
	
	/*************************************************************
	* Method: setLeft()/setRight()	                             *
	* Purpose: Set the left or right node				         *
	*          							                         *
	* Parameters: Node:          Next/previous node              *
	* Returns: Void:             N/A						     *
	**************************************************************/
	public void setLeft(TreeNode<T> left)
	{
		this.left = left;
	}	
	public void setRight(TreeNode<T> right)
	{
		this.right = right;
	}
}