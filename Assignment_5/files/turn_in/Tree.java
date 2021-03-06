import java.io.IOException;
import java.util.NoSuchElementException;

/*************************************************************
* Dalton Nofs                                                *
* Login ID: nofs5491                                         *
* CS-102, Summer 2017                                        *
* Programming Assignment 5                                   *
* Tree class:  Class for maintaining trees					 *
**************************************************************/
class Tree <T extends Comparable<T>>
{
    TreeNode<T> root; // root for the tree
    TreeNode<T> searchedNode; // node holder for searched node
    
    /*************************************************************
	* Method: Tree()			                      	         *
	* Purpose: default constructor for Tree class		         *
	*          							                         *
	* Parameters:                N/A                             *
	* Returns: void:             N/A						     *
	**************************************************************/
    public Tree()
    {
    	root=null;
    }

    /*************************************************************
	* Method: add()			                      		         *
	* Purpose: adds a value to the tree					         *
	*          							                         *
	* Parameters: T:			type to add                      *
	* Returns: void:            N/A							     *
	**************************************************************/
    public void add(T target) throws IOException
    {
    	root=add(target,root);
    }
    
    /*************************************************************
	* Method: add()	*private*	                      	         *
	* Purpose: recursive private func					         *
	*          							                         *
	* Parameters: T,TreeNode:	target, current node             *
	* Returns: TreeNode<T>:            TreeNode obj				 *
	**************************************************************/
    private TreeNode<T> add(T target, TreeNode<T> current) throws IOException
    {
    	// if null its last so just add to end
		if(current == null)
		{
		    TreeNode<T> leaf = new TreeNode<T>();
		    leaf.setDatum(target);
		    leaf.setLeft(null);
		    leaf.setRight(null);
		    return leaf;
		}
		if(search(target))
			throw new IOException("Course already exists");
		if(current.getDatum().compareTo(target)<0) // cont add to right branch
		   current.setRight( add(target, current.getRight()) );
		else									   // cont add to left branch
		    current.setLeft( add(target, current.getLeft()) );
		return current;
    }
    
    /*************************************************************
	* Method: search()			                      	         *
	* Purpose: searches tree for target					         *
	*          							                         *
	* Parameters: T:		 target								 *
	* Returns: boolean:      if found or not					 *
	**************************************************************/
    public boolean search(T target)
    {
    	return search(target,root);
    }
    
    /*************************************************************
	* Method: search()	*private*                      	     *
	* Purpose: recursive search fucn					         *
	*          							                         *
	* Parameters: T,TreeNode:	target, current node             *
	* Returns: boolean:         if found true					 *
	**************************************************************/
    private boolean search(T target, TreeNode<T> current)
    {
		if(current == null) {return false;} // if fallen off list
		if(current.getDatum().compareTo(target) == 0) // base case
		{
			searchedNode = current;
		    return true;
		}
	    if(current.getDatum().compareTo(target)<0) // continue search
		    return search(target,  current.getRight());
		else
		    return search(target, current.getLeft()); // continue search
    }
    
    /*************************************************************
	* Method: remove()			                      	         *
	* Purpose: searches and removes target from tree	         *
	*          							                         *
	* Parameters: T:		 target								 *
	* Returns: void:      	 N/A								 *
	**************************************************************/
    public void remove(T target)
    { 
    	root = remove(target,root);
    }
    
    /*************************************************************
	* Method: search()	*private*                      	         *
	* Purpose: recursive remove fucn					         *
	*          							                         *
	* Parameters: T,TreeNode:	target, current node             *
	* Returns: TreeNode<T>:     fixed treeNode					 *
	**************************************************************/
    private TreeNode<T> remove(T target, TreeNode<T> current) throws NoSuchElementException
    {
        if(current==null) // check to see if fell off list
        	throw new NoSuchElementException();
        if(current.getDatum().compareTo(target)<0) // search right branch
        {
        	current.setRight(remove(target,current.getRight()));
        	return current;
        }
        else if(current.getDatum().compareTo(target)>0) // search left branch
        {
        	current.setLeft(remove(target,current.getLeft()));
        	return current;
        }
        if(current.getLeft()==null) return current.getRight(); //eob found
        if(current.getRight()==null) return current.getLeft(); //eob found
        TreeNode<T> heir=current.getLeft(); // start heir search on left
        while(heir.getRight()!=null) // search for heir
        	heir=heir.getRight();
        current.setDatum(heir.getDatum()); // reconfigure tree
        current.setLeft(remove(heir.getDatum(),current.getLeft()));
        return current;
    }
    
    /*************************************************************
	* Method: isEmpty()			                      	         *
	* Purpose: is tree empty?							         *
	*          							                         *
	* Parameters: void			N/A					             *
	* Returns: boolean:     	if empty 1, else 0				 *
	**************************************************************/
    public boolean isEmpty()
    {
    	if(root == null)
    		return true;
    	else
    		return false;
    }
    
    /*************************************************************
	* Method: removeAll()		                      	         *
	* Purpose: remove all nodes							         *
	*          							                         *
	* Parameters: void:			N/A					             *
	* Returns: void:			N/A								 *
	**************************************************************/
    public void removeAll()
    {
    	root = null;
    }
    
    /*************************************************************
   	* Method: getSearched()		                      	         *
   	* Purpose: get node for last searched		   		         *
   	*          							                         *
   	* Parameters: void:			N/A					             *
   	* Returns: T:				data from last node searched	 *
   	**************************************************************/
    public TreeNode<T> getSearched()
    {
    	return  searchedNode;
    }
    
    /*************************************************************
   	* Method: getRoot()			                      	         *
   	* Purpose: get node for last searched		   		         *
   	*          							                         *
   	* Parameters: void:			N/A					             *
   	* Returns: TreeNode<T>:		the head node of the tree		 *
   	**************************************************************/
    public TreeNode<T> getRoot()
    {
    	return  root;
    }
}
