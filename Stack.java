package cs143;

import java.lang.reflect.Array;
import java.util.EmptyStackException;

public class Stack<Item> implements StackInterface<Item> {

private Item[] stackItems = null;
private int top = -1;
private int maxSize; 

	public Stack(int maxSize)
	{
		this.maxSize = maxSize;
		
		this.stackItems = (Item[]) new Object[maxSize];
		
	}
	
	@Override
	public void push(Item item) {
   // System.out.println("top:: " + top);
	//System.out.println("MS"+maxSize);
		if (top+1 < maxSize)
		{
		    top++;
			stackItems[top] = item;
			
	
		}
		else 
		{   
		
			throw new EmptyStackException();
			
		}
		

	}

	@Override
	public void pop() 
	{
	
		if (top != -1) 
		{
		stackItems[top+1] = null;
		--top;
		} 
		else
		{
			throw new EmptyStackException();
			
		}
	  
	}

	@Override
	public Item top() {
		
		if(top != -1) 
		{
	   return stackItems[top];
		}
	    else
	    {
	    	throw new EmptyStackException();
	    }
		
	
		
		 
		 
		 
		}
	

	@Override
	public Item topAndPop() {
	 //Item recent = stackItems[top+1];
	Item item =  top();
     pop();
     //return recent;
     return item;
		
	}

	@Override
	public boolean isEmpty() {
		if (top!= -1)
		{
			return false;
		}
		else
		{
		return true;
		}
	}

	@Override
	public void makeEmpty() {
	try {
		if (!isEmpty())
		{
		 while (!isEmpty())
		 {
			 pop();
			 
		 }
		}
		else
		{
			throw new IndexOutOfBoundsException("Your stack is empty");
	    }
		}catch(IndexOutOfBoundsException e)
		{
			System.out.println(e.getMessage());
		}
	
		}


	@Override
	public int size() {
	int size = top+1;
		return size;
	}

}
