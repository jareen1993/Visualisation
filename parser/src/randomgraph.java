import java.util.Random;

import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;


public class randomgraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int count1_same=0;
		int count2_same=0;
		
		Graph graph = new Graph();
		graph.addColumn("Name", Integer.class);
		graph.addColumn("Stand", String.class);
		String[] Stands = {"l", "c", "n"};
		Random rand = new Random();
		for (int i = 0; i < 150; i++)
		{
		Node n = graph.addNode();
		n.set("Stand", Stands[rand.nextInt(3)]);
		n.set("Name", rand.nextInt(46) + 20);	
		}
		
		Table r2 = graph.getNodeTable();
		
		for(int i = 0; i < 150; i++)
		{
		int first = rand.nextInt(150);
		int second = rand.nextInt(149);
		graph.addEdge(first, second);
		
		String s =   r2.getString(first,1);
		String s2 =  r2.getString(second,1);
		if (s.equals(s2)){count1_same++;}
		}
		
		
		
		Graph graph2 = new Graph();
		graph2.addColumn("Name", Integer.class);
		graph2.addColumn("Stand", String.class);
		String[] Standss = {"l", "c", "n"};
		Random rands = new Random();
		for (int i = 0; i < 150; i++)
		{
		Node n = graph2.addNode();
		n.set("Stand", Standss[rand.nextInt(3)]);
		n.set("Name", rands.nextInt(46) + 20);	
		}
		
		Table r = graph2.getNodeTable();
		
		for(int i = 0; i < 150; i++)
		{
		int first = rand.nextInt(150);
		int second = rand.nextInt(149);
		graph2.addEdge(first, second);	

		String s =   r.getString(first,1);
		String s2 =  r.getString(second,1);
		if (s.equals(s2)){count2_same++;}
		}
		
		
		System.out.print("Count for graph 1 is " +count1_same);
		System.out.println(" And Count for graph 2 is " +count2_same);

	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
