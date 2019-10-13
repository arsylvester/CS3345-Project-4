
public class RedBlackTree<E extends Comparable<E>>
{
	private static Boolean RED = false;
	private static Boolean BLACK = true;
	private Node<E> root;
	private int treeSize;
	
	private static class Node<E extends Comparable<E>>
	{
		public E element;
		public Node<E> leftChild;
		public Node<E> rightChild;
		public Node<E> parent;
		public Boolean color;
		
		public Node(E element, Node<E> parent)
		{
			this.element = element;
			leftChild = null;
			rightChild = null;
			this.parent = parent;
			color = RED;
		}
	}
	
	public RedBlackTree() 
	{
		root = null;
		treeSize = 0;
	}
	
	public Boolean insert(E element) throws NullPointerException
	{
		if(element == null)
			throw new NullPointerException("Element is NULL.");
		
		if(root == null)
		{
			root = new Node<E>(element, null);
			root.color = BLACK;
			treeSize++;
			return true;
		}
		
		Node<E> z = insertAtBottom(element, root, root);
		
		if(z == null)
			return false;
		
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
						if(parent.parent != null)
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
						if(parent.parent != null)
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
	
	public Boolean contains(Comparable<E> object)
	{
		if(object == null)
			return false;
		return false;
	}
	
	public String toString()
	{
		return "";
	}

}
