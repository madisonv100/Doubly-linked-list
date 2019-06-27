package cs143;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		LinkedListApp app = new LinkedListApp();
		 Scanner scan = new Scanner(System.in);

		System.out.println("Please give me your file name");
		String file = scan.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				String parts[] = line.split(",");
				for (int i = 0; i < parts.length; i++) {
					int listSize = app.getListSize();
					
					int absNum = (Math.abs(Integer.valueOf(parts[i])));
					int num = (Integer.valueOf(parts[i]));
					if(num < -10)
					{ 
						if(absNum > app.getStack().size()  && num < -20)
						{
							//System.out.println("protect nothing");
							
							;
						}
						else if(num > -20)
						{
							int newNum = absNum % 10;
							if(newNum > app.getStack().size())
							{
								System.out.println("protecting whole list");
								app.protect(app.getStack().size());
							}
							else
							{
							
							app.protect(newNum); 
							}
							
						} 
						else
						{
						
						app.removeInOrder(absNum);
						}
					}
					app.ordered(Integer.valueOf(num));
					

				}
 
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		app.printList();
	}

}
