/* Name: Andrew Sylvester
 * Class: CS 3345
 * Section: 001
 * Semester: Fall 2019
 * Project 4 
 * Class Description: This class RedBlackTree is an implementation of a RedBlackTree.
 */
public class RedBlackTree<E extends Comparable<E>>
{
	private static Boolean RED = false;
	private static Boolean BLACK = true;
	private Node<E> root;
	private int treeSize;
	
	/**
	 * Node class to be used to hold data for the Red Black tree.
	 * @param <E>
	 */
	private static class Node<E extends Comparable<E>>
	{
		public E element;
		public Node<E> leftChild;
		public Node<E> rightChild;
		public Node<E> parent;
		public Boolean color;
		
		/**
		 * Constructor
		 * @param element The element to be held by the node
		 * @param parent The parent of the node
		 */
		public Node(E element, Node<E> parent)
		{
			this.element = element;
			leftChild = null;
			rightChild = null;
			this.parent = parent;
			color = RED;
		}
	}
	
	/**
	 * Constructor
	 */
	public RedBlackTree() 
	{
		root = null;
		treeSize = 0;
	}
	
	/**
	 * Inserts a node in the proper position of the tree. If already exists will return false.
	 * @param element The element to add to the tree.
	 * @return Whether or not the element was added to the tree.
	 * @throws NullPointerException
	 */
	public Boolean insert(E element) throws NullPointerException
	{
		//Make sure not null. Throw exception if so.
		if(element == null)
			throw new NullPointerException("Element is NULL.");
		
		//If tree is empty make root.
		if(root == null)
		{
			root = new Node<E>(element, null);
			root.color = BLACK;
			treeSize++;
			return true;
		}
		
		//Start by inserting at bottom of tree.
		Node<E> z = insertAtBottom(element, root, root);
		
		//If already in tree return false.
		if(z == null)
			return false;
		
		//Now that the node is at the bottom need to rotate and/or recolor till in the right place and no double reds.
		while(z.parent != null && z.parent.color == RED)
		{
			Node<E> uncle = getSibling(z.parent);
			Node<E> parent = z.parent;
			Node<E> grandparent = parent.parent;
			
			if(uncle == null || uncle.color == BLACK) // Uncle is black
			{
				if(grandparent.leftChild != null && grandparent.leftChild.element.compareTo(parent.element) == 0)
				{
					if(parent.leftChild != null && parent.leftChild.element.compareTo(z.element) == 0) //Zig left
					{
						parent.parent = grandparent.parent;
						if(parent.parent != null)
						{
							if(parent.element.compareTo(parent.parent.element) < 0)
								parent.parent.leftChild = parent;
							else
								parent.parent.rightChild = parent;
						}
						
						grandparent.leftChild = parent.rightChild;
						if(grandparent.leftChild != null)
							grandparent.leftChild.parent = grandparent;
						
						parent.rightChild = grandparent;
						grandparent.parent = parent;
						parent.color = BLACK;
						grandparent.color = RED;
						if(parent.parent == null)
							root = parent;
					}
					else //Zig-zag triangle left
					{
						grandparent.leftChild = z.rightChild;
						if(grandparent.leftChild != null)
							grandparent.leftChild.parent = grandparent;
						
						parent.rightChild = z.leftChild;
						if(parent.rightChild != null)
							parent.rightChild.parent = parent;
						
						z.parent = grandparent.parent;
						if(z.parent != null)
						{
							if(z.element.compareTo(z.parent.element) < 0)
								z.parent.leftChild = z;
							else
								z.parent.rightChild = z;
						}
						
						z.rightChild = grandparent;
						grandparent.parent = z;
						z.leftChild = parent;
						parent.parent = z;
						z.color = BLACK;
						grandparent.color = RED;
						
						if(z.parent == null)
							root = z;
					}
				}
				else
				{
					if(parent.leftChild != null && parent.leftChild.element.compareTo(z.element) == 0) //Zig-zag triangle right
					{
						grandparent.rightChild = z.leftChild;
						if(grandparent.rightChild != null)
							grandparent.rightChild.parent = grandparent;
						
						parent.leftChild = z.rightChild;
						if(parent.leftChild != null)
							parent.leftChild.parent = parent;
						
						z.parent = grandparent.parent;
						if(z.parent != null)
						{
							if(z.element.compareTo(z.parent.element) < 0)
								z.parent.leftChild = z;
							else
								z.parent.rightChild = z;
						}
						
						z.leftChild = grandparent;
						grandparent.parent = z;
						z.rightChild = parent;
						parent.parent = z;
						z.color = BLACK;
						grandparent.color = RED;
						
						if(z.parent == null)
							root = z;
					}
					else //Zig right
					{
						parent.parent = grandparent.parent;
						if(parent.parent != null)
						{
							if(parent.element.compareTo(parent.parent.element) < 0)
								parent.parent.leftChild = parent;
							else
								parent.parent.rightChild = parent;
						}
						
						grandparent.rightChild = parent.leftChild;
						if(grandparent.rightChild != null)
							grandparent.rightChild.parent = grandparent;
						
						parent.leftChild = grandparent;
						grandparent.parent = parent;
						parent.color = BLACK;
						grandparent.color = RED;
						
						if(parent.parent == null)
							root = parent;
					}
				}
			}
			else // Uncle is red, recolor
			{
				parent.color = BLACK;
				grandparent.color = RED;
				uncle.color = BLACK;
				z = grandparent;
			}
			
		}
		
		root.color = BLACK;
		treeSize++;
		return true;

			
	}
	
	/**
	 * This method is used by Insert to place the element at the bottom of the tree like a standard Binary insert.
	 * @param element The element to add to the tree.
	 * @param node The current node in the recursive traversal.
	 * @param parent The parent of the current node in the recursive traversal.
	 * @return The node that was just inserted. Returns null if already in the tree.
	 */
	private Node<E> insertAtBottom(E element, Node<E> node, Node<E> parent)
	{
		if(node == null)
		{
			if(element.compareTo(parent.element) < 0)
				return parent.leftChild = new Node<E>(element, parent);
			else
				return parent.rightChild = new Node<E>(element, parent);
		}
		
		if(element.compareTo(node.element) < 0)
		{
			return insertAtBottom(element, node.leftChild, node);
		}
		else if(element.compareTo(node.element) > 0)
		{
			return insertAtBottom(element, node.rightChild, node);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Gets the sibling of a node. Useful for finding the uncle as well.
	 * @param node The node to find the sibling of
	 * @return The sibling, can be null.
	 */
	private Node<E> getSibling(Node<E> node)
	{
		if(node.parent.leftChild != null && node.parent.leftChild.element.compareTo(node.element) == 0) // is left, get right
		{
			return node.parent.rightChild;
		}
		else // is left, get right
		{
			return node.parent.leftChild;
		}
	}
	
	/**
	 * Public method to see if an object is in the tree. Calls a helper contains method.
	 * @param object The object to find in the tree.
	 * @return True if found, false otherwise or if object is null.
	 */
	public boolean contains(Comparable<E> object)
	{
		if(object == null)
			return false;
		return contains(object, root);
	}
	
	/**
	 * Helper contains method. Finds an object in the tree recursively.
	 * @param object The object to find in the tree.
	 * @param node The current node in the recursive traversal of the tree.
	 * @return True if object is found in the tree, false otherwise.
	 */
	private boolean contains(Comparable<E> object, Node<E> node)
	{
		if(node == null)
		{
			return false;
		}
		
		if(object.compareTo(node.element) < 0)
		{
			return contains(object, node.leftChild);
		}
		else if(object.compareTo(node.element) > 0)
		{
			return contains(object, node.rightChild);
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Public toString method. Calls the helper totString to recursively traverse the tree.
	 * @return The elements in the tree in pre-order. * next to red nodes.
	 */
	public String toString()
	{
		return toString(root, "");
	}
	
	/**
	 * Helper method for toString.
	 * @param node Current node in the recursive pre-order traversal.
	 * @param output Current string of values in the tree.
	 * @return A string with values of the tree.
	 */
	private String toString(Node<E> node, String output)
	{
		if(node != null)
		{
			if(node.color == RED)
			{
				output = output + "*" + node.element.toString() + " " + toString(node.leftChild, output) + toString(node.rightChild, output);
			}
			else
			{
				output = output + node.element.toString() + " " + toString(node.leftChild, output) + toString(node.rightChild, output);
			}
			return output;
		}
		else
		{
			return "";
		}
	}

}
