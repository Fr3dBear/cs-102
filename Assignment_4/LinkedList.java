/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 2                                   *
* LinkedList class:  node object for linkedLists			 *
**************************************************************/
public class LinkedList<T> implements ListInterface<T>
{	
	Node<T> head; // Head of the linked list
	
	/*************************************************************
	* Method: LinkedList()			                             *
	* Purpose: default constructor for linkedList obj	         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
	public LinkedList()
	{
		head = null;
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
		return (head == null);
	}
	
	/***************************************************************
	 * Method: size() 											   *
	 * Purpose: determine the size of linked list 				   *
	 * 															   * 
	 *  Parameters: 		N/A									   * 
	 *  Returns: int: 		the size of the array 				   *
	 **************************************************************/
	public int size()
	{
		Node<T> current = head; // counter node started at head
		int counter = 0;	 // counter for size calc
		while(current != null)
		{
			current = current.getNext(); // get next node
			counter++;
		}
		return counter; // return the size of linkedList
	}
	
	/*************************************************************
	* Method: get()			                                 	 *
	* Purpose: get object from linked list at index		         *
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: T:		         Object stored in index			 *
	**************************************************************/
	public T get(int index) throws IndexOutOfBoundsException
	{
		Node<T> current = head; // the current node for count check
		if((index < 0) || (index >= size())) // check for bad index
		{
			throw new IndexOutOfBoundsException("Index not in limits!");
		}
		// iterate through list while counter < size and current != null
		for(int lpindx = 0; lpindx < size() || current != null; lpindx++)
		{
			if(lpindx == index)
			{
				return current.getData(); // get object stored in desired index
			}
			else
			{
				current = current.getNext(); // get next node
			}
		}
		// Index was not found so throw error
		throw new IndexOutOfBoundsException("Index not found!");
	}
	
	/*************************************************************
	* Method: getNode()	!!! Private !!!                        	 *
	* Purpose: get Node from linked list at index		         *
	*          							                         *
	* Parameters: int:           index                           *
	* Returns: Node:             Node stored in index		     *
	**************************************************************/
	private Node<T> getNode(int index) throws IndexOutOfBoundsException
	{
		Node<T> current = head; // the current node for count check
		if((index < 0) || (index >= size())) // check for bad index
		{
			throw new IndexOutOfBoundsException("Index not in limits!");
		}
		// iterate through list while counter < size and current != null
		for(int lpindx = 0; lpindx < size() || current != null; lpindx++)
		{
			if(lpindx == 0)
				return head;
			if(lpindx == index)
			{
				return current;	// return node in desired index
			}
			else
			{
				current = current.getNext(); // get next node
			}
		}
		// Index was not found so throw error
		throw new IndexOutOfBoundsException("Index not found!");
	}
	
	/*************************************************************
	* Method: add()			                                 	 *
	* Purpose: add a object at specified index			         *
	*															 *
	* Notes: calls func that can throw indexoutboundsexception	 *         
	*          							                         *
	* Parameters: 												 *
	* 			  int:           index                           *
	* 			  Object:		 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void add(int index, T data)
	{
		Node<T> target; 			  // Target insertion location
		Node<T> splice = new Node<T>(); // New inserted node
		
		// load data into node
		splice.setData(data);
		
		if(head!=null)
		{
			target = this.getNode(index); // get the target index's node
			// check to see if 1st in list needs to be replaced
			if(target.getPrevious()==null)
			{
				head = splice;
				splice.setPrevious(head);
			}
			// if not 1st in list then set insert to target previous
			else
			{
				splice.setPrevious(target.getPrevious()); // transfer previous
			}
			splice.setNext(target); // set insert to point to target
			target.setPrevious(splice); // set targets previous to insert
		}
		else
		{
			// list is empty
			head = splice;
			splice.setPrevious(head);
		}
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
	public T remove(int index)
	{
		Node<T> target; // target to be removed
		
		target = this.getNode(index); // get target node
		// check if first on list but not last
		if(target.getPrevious() == head && target.getNext() != null)
		{
			// set target to point to head and head to point to target
			target.getNext().setPrevious(head);
			head = target.getNext();
		}
		// check if last on list or first on list
		else if(target.getNext() != null && target.getPrevious() != null)
		{
			// set targets next node to targets previous node
			target.getNext().setPrevious(target.getPrevious());
			// set targets previous node to targets next node
			target.getPrevious().setNext(target.getNext());
		}
		// check if last one list
		else if(target.getNext() == null && target.getPrevious() != head)
		{
			target.getPrevious().setNext(null);
		}
		// if last one list
		else if(target.getNext() == null && target.getPrevious() == head)
		{
			head = null;
		}
		
		// destoy target node
		target.setNext(null);
		target.setPrevious(null);
		
		// return object that was removed
		return target.getData();
	}
	
	/*************************************************************
	* Method: removeAll									 		 *
	* 															 *
	* Purpose: removes all nodes from array					     *
	*          							                         *
	* Parameters:              N/A	                             *
	* Returns: void:           N/A								 *
	**************************************************************/
	public void removeAll() {head=null;}

	/*************************************************************
	* Method: addLast()		                                 	 *
	* Purpose: add a object to last index				         *
	*															 *
	* Notes: calls func that can throw indexoutboundsexception	 *         
	*          							                         *
	* Parameters: 												 *
	* 			  T:			 Object to be placed			 *
	* 															 *
	* Returns: void:           	 N/A							 *
	**************************************************************/
	public void addLast(T data)
	{
		Node<T> target;			  // target Node
		Node<T> insert = new Node<T>(); // New inserted node
		
		// load data into node
		insert.setData(data);
		if(head!=null)
		{
			target = this.getNode(this.size()-1); // get the target index's node
			target.setNext(insert); // set last to be the next node to append
			insert.setPrevious(target); // set insert to the pre last
		}
		else
		{
			head = insert;
			insert.setPrevious(head);
		}
	}
}
