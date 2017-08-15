/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 3                                   *
* Node class:  node object for linkedLists					 *
**************************************************************/
public class Node
{
	Object data;	// Data to be stored in Node
	Node next;		// Next node ptr
	Node previous;	// Previous node ptr
	
	/*************************************************************
	* Method: Node()			                                 *
	* Purpose: default constructor for node obj			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public Node()
	{
		data = null;
		next = null;
		previous = null;
	}
	
	/*************************************************************
	* Method: getData()			                                 *
	* Purpose: get previous node or next node			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: Object:           data type stored in node	     *
	**************************************************************/
	public Object getData()
	{
		return data;
	}
	
	/*************************************************************
	* Method: setData()			                                 *
	* Purpose: get previous node or next node			         *
	*          							                         *
	* Parameters: Object:		 Data to be stored in node       *
	* Returns: void:             N/A							 *
	**************************************************************/
	public void setData(Object data)
	{
		this.data = data;
	}
	
	/*************************************************************
	* Method: getNext()/getPrevious()                            *
	* Purpose: get previous node or next node			         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: Node:             Node pointing to next or prev   *
	**************************************************************/
	public Node getNext()
	{
		return next;
	}
	public Node getPrevious()
	{
		return previous;
	}
	
	/*************************************************************
	* Method: setNext()/setPrevious()                            *
	* Purpose: Set the next or previous node			         *
	*          							                         *
	* Parameters: Node:          Next/previous node              *
	* Returns: Void:             N/A						     *
	**************************************************************/
	public void setNext(Node next)
	{
		this.next = next;
	}	
	public void setPrevious(Node previous)
	{
		this.previous = previous;
	}
	
}
