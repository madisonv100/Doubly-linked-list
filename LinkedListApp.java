package cs143;

import java.util.ListIterator;
 
public class LinkedListApp {
 

	 
	 private  Stack<Integer> stack = new Stack<Integer>(150);
	 
	private	  DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		
		public  void ordered(int insert) {
			boolean added = false;
			//only creating one object
			Integer currentNum = new Integer(insert);
			
			if (list.isEmpty()) {
				System.out.println("empty list");
				ListIterator<Integer> it = list.listIterator();
				it.add(insert);
				stack.push(insert);
				added = true;
				System.out.println("pushed :" + insert);
				System.out.println("top : " + stack.top()); 
			} else {
				ListIterator<Integer> it = list.listIterator();
				while (it.hasNext()) {
					Integer current = it.next();
					//System.out.println("cur" + current);
					if (current > insert) {
						it.previous();
						it.add(insert);
						stack.push(insert); 
						System.out.println("pushed :" + insert);
						System.out.println("top : " + stack.top());
						added = true;
						break;
					}
					//break;
				}
				if (!added) {
					System.out.println("not added");
					it.add(insert);
					stack.push(insert);
					System.out.println("pushed :" + insert);
					System.out.println("top : " + stack.top());
				}
			}
printList();

		}

int protectNum = 0;

	public void protect(int insert)
	{
		protectNum++;
		
		for(int i = 0 ; i < insert; i ++)
		{
		System.out.println("Protect Number " + stack.top());
		stack.pop();
		}
	}
	
	public void remove()
	{
		ListIterator<Integer> it =  list.listIterator();
		while(it.hasNext())
		{
			if(it.next() == stack.top())
			{
				//System.out.print("top  :"+ stack.top());
				System.out.println("remove number " + stack.top());	
				it.remove();
			stack.pop();
			//System.out.print("top  :"+ stack.top());
				break;
			}
		}
	}
	public void removeInOrder(int insert)
	{
		
		
		for(int i = 0; i < insert; i ++)
		{
			//System.out.println("x");
			remove();
		}
	}
	
	public void printList() {
		System.out.println("list lenght  :"+list.length());

		for (int i = 0; i < stack.size()+protectNum; i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	public int  getListSize()
	{
		return stack.size()+protectNum;
	}

	public DoublyLinkedList<Integer> getList() { 
		return list;
	}

	public void setList(DoublyLinkedList<Integer> list) {
		this.list = list;
	}

	public Stack<Integer> getStack() {
		return stack;
	}

	public void setStack(Stack<Integer> stack) {
		this.stack = stack;
	}
	
	
}
