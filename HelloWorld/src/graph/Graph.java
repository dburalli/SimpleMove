package graph;

import java.util.*;

public class Graph {
	public Node[] nodes;
	
	static class Node{
		public int name;
		boolean visited;
		List<Node> neighbors;
		
		Node(int i){
			this.name = i;
			this.neighbors = new ArrayList<>();
		}
		public void addNeighbors(Node neighborNode) {
			this.neighbors.add(neighborNode);
		}
		public List<Node> getNeighbors() {
			return neighbors;
		}
		public void setNeighbors(List<Node> neighbors) {
			this.neighbors = neighbors;
		}
		public int getName() {
			return name;
		}
	}
	
	public static void main(String[] args) {
		Node node40 =new Node(40);
		Node node10 =new Node(10);
		Node node20 =new Node(20);
		Node node30 =new Node(30);
		Node node60 =new Node(60);
		Node node50 =new Node(50);
		Node node70 =new Node(70);
 
		node40.addNeighbors(node20);
		node40.addNeighbors(node10);
		node10.addNeighbors(node30);
		node20.addNeighbors(node10);
		node20.addNeighbors(node30);
		node20.addNeighbors(node60);
		node20.addNeighbors(node50);
		node30.addNeighbors(node60);
		node60.addNeighbors(node70);
		node50.addNeighbors(node70);
		
		//depthFirstSearch(node40);
		breadthFirstSearch(node40);
	}
	
	//recursive DFS
	public static void depthFirstSearch(Node node) {
		//this goes as deep as possible from the initial node
		if (node == null) {
			System.out.println("null node");
		}
		
		//print out nodes info
		System.out.println(node.getName() + " ");
		
		//get list of adjacent nodes each time
		List<Node> neighbors = node.getNeighbors();
		//mark the node we are in as visited
		node.visited = true;
		//for each neighbor node
		for(int i = 0; i < neighbors.size(); i++) {
			//get the neighbor in the list
			Node n = neighbors.get(i);
			//if the neighbor isn't null (removed) and hasn't been visited, search.
			if(n!=null && !n.visited)
			{
				//recursively search the unvisited node
				depthFirstSearch(n);
			}
		}
		
	}
	
	public static void breadthFirstSearch(Node node) {
		//create external queue, to iterate in order
		Queue<Node> queue = new LinkedList<>();
		//list of the nodes
		ArrayList<Node> nodes = new ArrayList<Node>();
		//actual BFS function
		bfs(node,queue, nodes);
		
	}
	
	public static void bfs(Node node, Queue<Node> queue, ArrayList<Node> nodes) {
		//adds parent node, then each following node since recursive
		queue.add(node);
		//visited, to avoid infinity
		node.visited = true;

		//iterate the queue while it has nodes
		while(!queue.isEmpty()) {
			//r = top of queue
			Node r = queue.remove();
			//print the info
			System.out.println(r.getName() + " ");
			//get the neighbors of the top node
			List<Node> neighbors = r.getNeighbors();
			//get every neighbor of r
			for(int i = 0; i < neighbors.size(); i++) {
				//get the every node in neighbors
				Node n = neighbors.get(i);
				//if the node isn't null and hasn't been visited
				if(n!=null && !n.visited)
				{
					//add to the queue
					queue.add(n);
					//visited to avoid collision
					n.visited = true;
				}
			}
		}
	}
}
