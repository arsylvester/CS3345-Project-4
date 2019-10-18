/* Name: Andrew Sylvester
 * Class: CS 3345
 * Section: 001
 * Semester: Fall 2019
 * Project 4 
 * Class Description: This class Main runs the project to test RedBlackTree
 * using data from an input file. The results are outputed to an output file.
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class Main 
{
    public static void main(String[] args) 
    {
    	 Scanner in;
         if (args.length!=2) 
         {
             System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
             System.exit(0);    
         } 
         try 
 		{
         	//Open input and output files. Create Scanner.
 			File input_file = new File(args[0]);
 			in = new Scanner(input_file).useDelimiter(":|\r\\n");
 			File output_file = new File(args[1]);
 			PrintWriter out;
 			out = new PrintWriter(output_file);
 			
 			String operation = in.next();
 			
 			//Integer red black tree
 			if(operation.compareTo("Integer") == 0)
 			{

 				RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

 				while (in.hasNext()) 
 				{

 					operation = in.next();

 					//Check for which operation to take
 					switch (operation) 
 					{	
 					case "Insert":
 						try 
 						{
 							//Should have a : before the int.
 							if(in.findInLine(":") == null)
 							{
 								out.println("Error in Line: " + operation);
 								break;
 							}	
 							String test = in.findInLine("-*[0-9]*");
 							if(test == null)
 								out.println(rbt.insert(null) ? "True" :"False");
 							else
 								out.println(rbt.insert(Integer.parseInt(test)) ? "True" :"False");

 						} 
 						catch (Exception e) 
 						{
 							out.println("Error in insert: NullPointerException raised");
 						}
 						break;
 					case "Contains":
 						//Should have a : before the int.
 						if(in.findInLine(":") == null)
 						{
 							out.println("Error in Line: " + operation);
 							break;
 						}	
 						String test = in.findInLine("-*[0-9]*");
 						if(test == null)
 							out.println(rbt.contains(null) ? "True" :"False");
 						else
 							out.println(rbt.contains(Integer.parseInt(test)) ? "True" :"False");
 						break;
 					case "PrintTree":
 						out.println(rbt.toString());
 						break;
 					default:
 						out.println("Error in Line: " + operation);
 						//in.nextLine();
 					}
 					if(in.hasNext())
 						in.nextLine();
 				}
 			}
 			//String red black tree
 			else if(operation.compareTo("String") == 0)
 			{
 				RedBlackTree<String> rbt = new RedBlackTree<String>();

 				while (in.hasNext()) 
 				{

 					operation = in.next();

 					//Check for which operation to take
 					switch (operation) 
 					{	
 					case "Insert":
 						try 
 						{
 							//Should have a : before the String.
 							if(in.findInLine(":") == null)
 							{
 								out.println("Error in Line: " + operation);
 								break;
 							}	
 							String test = in.findInLine(".*");
 							out.println(rbt.insert(test) ? "True" :"False");

 						} 
 						catch (Exception e) 
 						{
 							out.println("Error in insert: NullPointerException raised");
 						}
 						break;
 					case "Contains":
 						//Should have a : before the String.
 						if(in.findInLine(":") == null)
 						{
 							out.println("Error in Line: " + operation);
 							break;
 						}	
 						String test = in.findInLine(".*");
 						out.println(rbt.contains(test) ? "True" :"False");
 						break;
 					case "PrintTree":
 						out.println(rbt.toString());
 						break;
 					default:
 						out.println("Error in Line: " + operation);
 						//in.nextLine();
 					}
 					if(in.hasNext())
 						in.nextLine();
 				}
 			}
 			//Any other object then integer or string
 			else
 			{
 				out.println("Only works for objects Integers and Strings");
 			}
 			in.close();
			out.close();
 		} 
         catch (Exception e) 
         {
 			System.out.println("Exception: " + e.getMessage());
 		}
    }
    
    
}