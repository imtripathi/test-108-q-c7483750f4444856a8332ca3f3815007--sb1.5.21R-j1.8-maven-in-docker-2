package org.codejudge.sb.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.codejude.sb.model.FriendSuggestionList;
import org.codejudge.sb.entity.Friends;
import org.codejudge.sb.exception.NoDataFoundException;
import org.codejudge.sb.repository.FriendRepo;

public class FriendSuggestion 
{ 
 
	private Queue<Node> queue;
	static ArrayList<Node> nodes=new ArrayList<Node>();
	static class Node
	{
		String data;
		boolean visited;
		List<Node> neighbours;
 
		Node(String data)
		{
			this.data=data;
			this.neighbours=new ArrayList<>();
 
		}
		public void addneighbours(Node neighbourNode)
		{
			this.neighbours.add(neighbourNode);
		}
		public List<Node> getNeighbours() {
			return neighbours;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
	}
 
	public FriendSuggestion()
	{
		queue = new LinkedList<Node>();
	}
 
	public List<String> bfs(Node node)
	{
		List<String> list = new ArrayList<>();
		queue.add(node);
		node.visited=true;
		while (!queue.isEmpty())
		{
 
			Node element=queue.remove();
			System.out.print(element.data + "t");
			list.add(element.data);
			List<Node> neighbours=element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Node n=neighbours.get(i);
				if(n!=null && !n.visited)
				{
					queue.add(n);
					n.visited=true;
 
				}
			}
 
		}
		return list;
	}
	
	
	
	public FriendSuggestionList getFriendSuggestionList(String friendsufggestionfor, FriendRepo frnds) throws NoDataFoundException{
		FriendSuggestionList fs= new FriendSuggestionList();
		List<String> ls= new ArrayList<>();
		List<Friends>fr=frnds.getFriendSuggestion(friendsufggestionfor);
		if (fr == null || fr.isEmpty()) {
			throw new NoDataFoundException("No friend suggestion");
		}
		Node nodeA =new Node(friendsufggestionfor);
		int len=fr.size();
		while(len>=0) {
			
			for(Friends frs:fr) {
				if(frs.getFriendReqTo().equals(friendsufggestionfor)) {
					nodeA.addneighbours(new Node(frs.getFriendReqFrom()));
					
					friendsufggestionfor=frs.getFriendReqFrom();
					break;
					
				}}
				len--;
		}
		
		List<String> list=bfs(nodeA);
		for(int i=2;i<list.size();i++) {
			ls.add(list.get(i));
		}
		if (ls == null || ls.isEmpty()) {
			throw new NoDataFoundException("No friend suggestion");
		}
		fs.setSuggestions(ls);
		
		return fs;
			}

	
	
}