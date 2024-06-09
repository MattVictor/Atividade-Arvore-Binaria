package org.projetoED.BinarySearchTrees;
//Program to implement a Splay Tree.

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree {
	private int rotations = 0;

	static class Node{
		int data;    //data value
		Node left;   //points to left child.
		Node right;  //points to right child
		Node parent; //points to the parent node.
		
		public Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
	
	Node root;   //root of the tree.

	
	//function for performing a Left Rotation.
	private void leftRotate(Node node){
		Node parent = node.parent;
		Node left = node.left;
		node.left = parent;
		parent.right = left;
		if(left!=null){
			left.parent = parent;
		}
		Node gp = node.parent.parent;
		parent.parent = node;
		node.parent = gp;

		if(gp==null){
			root = node;
		}
		else{
			if(gp.left == parent){
				gp.left = node;
			}
			else{
				gp.right = node;
			}
		}
		rotations++;
	}
	
	//function for performing a Right Rotation.
	private void rightRotate(Node node){
		Node parent = node.parent;
		Node right = node.right;
		node.right = parent;
		parent.left = right;
		if(right!=null){
			right.parent = parent;
		}
		Node gp = parent.parent;
		node.parent = gp;
		parent.parent = node;

		if(gp==null){
			root = node;
		}
		else{
			if(gp.left==parent){
				gp.left = node;
			}
			else{
				gp.right = node;
			}
		}
		rotations++;
	}

	//function for performing the Splay Operation.
	private void splay(Node node){
		if(node.parent == null){
			root = node;
			return;
		}
	
		//zig Rotation.
		if(node.parent.parent==null){
			if(node.parent.right == node){
				leftRotate(node);
				root = node;
			}
			else{
				rightRotate(node);
				root = node;
			}
			return;
		}
		
		//zig-zag rotation - left rotation then right rotation
		if(node.parent.right==node && node.parent.parent.left == node.parent){
			leftRotate(node);
			rightRotate(node);
			splay(node);
			return;
		}
	
		//zigzag rotation - right rotation then left rotaion.
		if(node.parent.left == node && node.parent.parent.right == node.parent){
			rightRotate(node);
			leftRotate(node);
			splay(node);
			return;
		}
	
		//zig-zig rotation left rotation then left rotation.
		if(node.parent.right==node && node.parent.parent.right == node.parent){
			leftRotate(node.parent);
			leftRotate(node);
			splay(node);
			return;
		}
		
		//zig-zig rotation right rotation then right rotation.
		if(node.parent.left == node && node.parent.parent.left == node.parent){
			rightRotate(node.parent);
			rightRotate(node);
			splay(node);
			return;
		}
	}
		
	//function to add new elements in the tree.
	public void insert(int data){
		Node node = new Node(data);
		if(root == null){
			root = node;
			return;
		}
		
		Node temp = root;
		
		while(temp!=null){
			if(temp.data>data){
				if(temp.left==null){
					temp.left = node;
					node.parent = temp;
					splay(node);                 //perform splay operation.
					return;
				}
				temp = temp.left;
			}
		
			if(temp.data<data){
				if(temp.right==null){				
					temp.right = node;
					node.parent = temp;
					splay(node);                 //perform splay opertaion.
					return;
				}
				temp = temp.right;
			}
		}
	}

	//function to search for a data value in the tree.
	public Node search(int data){
		if(root==null){
			return null;
		}
		
		Node temp = root;
		while(temp!=null){
			if(temp.data == data){
				splay(temp);
				return temp;
			}
			if(temp.data>data){
				temp = temp.left;
			}
			else{
				temp = temp.right;
			}
		}
		return null;
	}

	//function to find the min value from the given node.
	public Node findMin(Node node){
		if(node==null){
			System.out.println("Empty Tree.");
			return null;
		}
		System.out.println("Finding Minimum.");
		Node min = node;
		while(min.left!=null){
			min = min.left;
		}
		splay(min);
		System.out.println("Minimum node : " + min.data);
		return min;
	}

	//function to remove elements from the tree.
	public void delete(int data){
		Node node = search(data);
		if(node==null){
			return;
		}

		Node min = findMin(node.right);
		if(min==null){
			root = root.left;
			root.parent = null;
			return;
		}
		root.left = root.left.left;
		if(root.left!=null){
			root.left.parent = root;
		}
	}
	
	//function for PreOrder Traversal of the tree.
	private void preOrder(Node node){
		if(node==null){
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	//function to display the tree.
	public void display(){
		System.out.print("Tree's PreOrder Traveral : ");
		preOrder(root);
		System.out.println();
	}

	public Node getRoot(){
		return this.root;
	}

	public int getRotations(){
		return this.rotations;
	}

	public Integer treeHeight(Node node)
	{
		// Base Case
		if (node == null)
			return 0;

		// Create an empty queue for level order traversal
		Queue<Node> q = new LinkedList();

		// Enqueue Root and initialize height
		q.add(node);
		int height = 0;

		while (1 == 1)
		{
			// nodeCount (queue size) indicates number of nodes
			// at current level.
			int nodeCount = q.size();
			if (nodeCount == 0)
				return height;
			height++;

			// Dequeue all nodes of current level and Enqueue all
			// nodes of next level
			while (nodeCount > 0)
			{
				Node newnode = q.peek();
				q.remove();
				if (newnode.left != null)
					q.add(newnode.left);
				if (newnode.right != null)
					q.add(newnode.right);
				nodeCount--;
			}
		}
	}

	public String toString(){
		return "SplayTree";
	}
}				