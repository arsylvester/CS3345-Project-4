/* Name: Andrew Sylvester
 * Class: CS 3345
 * Section: 001
 * Semester: Fall 2019
 * Project 3 
 * Class Description: This class P2Driver runs the project to test IDedLinkedList, IDedObject, and MyItem
 * using data from an input file. The results are outputed to an output file.
 * (Note: This file is pretty much untouched from what is given on eLearning as everything works as intended.)
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class Main 
{
    public static void main(String[] args) 
    {
    	try
    	{
	        RedBlackTree<Integer> rbTree = new RedBlackTree();
	        rbTree.insert(1);
	        rbTree.insert(2);
	        rbTree.insert(3);
	        rbTree.insert(4);
	        rbTree.insert(5);
	        rbTree.insert(6);
	        rbTree.insert(7);
	        rbTree.insert(8);
	        System.out.println("Finished");
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
    
}