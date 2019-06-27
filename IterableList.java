package cs143;

import java.util.Iterator;
import java.util.ListIterator;

public interface IterableList<Item extends Comparable<Item>> extends Iterable<Item>  {

	
	/**
	*   @brief Returns an List Iterator for the list.
	*
	*   
	*   @return Iterator
	*/
	public ListIterator<Item> listIterator() ;
	
	
	
	/**
	*   @brief Returns an List Iterator for the list.
	*
	*   
	*   @return Iterator
	*/
	public Iterator<Item> iterator(); 	
	/**
	*   @brief clear all elements from the list.
	*
	*   @param  E The item to insert.
	*   @return void
	*/
	void clear();	

	/**
	*   @brief add an Element to the end of the list
	*
	*   @param  E The item to add to the end of the list.
	*   @return void
	*/
	void append(Item item);

	/**
	*   @brief add an Element to the front of the list
	*
	*   @param  E The item to add to the front of the list.  
	*   @return void
	*/
	void prepend(Item item);

	/**
	*   @brief get the Element at the given index
	*
	*  
	*   @return E The item at the given index 
	*/
	Item get(int index);

	 
	/**
	*   @brief  Get the current number of items in the list.
	*  
	*
	*   @return int
	*/
	int length() ;
	
	
	/**
	*   @brief  Returns true if the list contains no elements.
	*  
	*
	*   @return boolean
	*/
	boolean isEmpty() ;

	/**
	*   @brief  Returns True if the current item is in the list.
	*  
	*
	*   @return bool
	*/
	boolean contains(Item item);

	

	/**
	*   @brief  Returns an array with the items in the list.
	*   They will be in the same order as the list 
	*
	*   @return Item[]
	*/
	Item[] toArray(Item[] array);
	
}
