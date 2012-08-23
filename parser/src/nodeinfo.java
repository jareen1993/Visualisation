import javax.xml.soap.Node;

import prefuse.data.Edge;
import prefuse.data.Table;
import prefuse.data.Graph;


public class nodeinfo {
	
	public static int[][] tabularinfo( Graph graph) 
	{
	
		int edgecount = graph.getEdgeCount();
	    int nodecount = graph.getNodeCount();
	    int inter;
	    Table s;
	    String s1,s2;
	    int i,j; Edge e;int src,trgt;
	    int[][] nodeinfo = new int[nodecount][edgecount+4];//total,left,neutral,congress
	    for(i=0;i<nodecount;i++)
	    	for(j=0;j<edgecount+4;j++)
	    		nodeinfo[i][j]=0;	
	    		 
		  for(i=0;i<edgecount;i++)
		  {
			  src = graph.getSourceNode(i);
			  trgt = graph.getTargetNode(i);
			  nodeinfo[src][i]++;
			  nodeinfo[trgt][i]++;
		  }
		  
		  for(i=0;i<nodecount;i++)
		  {
			  for(j=0;j<edgecount;j++)
			  {
				  if(nodeinfo[i][j]==0) {}
				  else
				  {
					 if( graph.getSourceNode(j)==i)
					 {	 
						nodeinfo[i][edgecount]++;
						inter =   graph.getTargetNode(j)  ; 
						s = graph.getNodeTable();	
						s2 =  (String) s.get(inter,"Stand");
						if(s2.equals("l")){nodeinfo[i][edgecount+1]++; }
						else if (s2.equals("n")){nodeinfo[i][edgecount+2]++; }
						else {nodeinfo[i][edgecount+3]++; }					
					 }
					 else 
					 {
							nodeinfo[i][edgecount]++;
							inter =   graph.getSourceNode(j)  ; 
							s = graph.getNodeTable();
							s2 =  (String) s.get(inter,"Stand");
							if(s2.equals("l")){nodeinfo[i][edgecount+1]++; }
							else if (s2.equals("n")){nodeinfo[i][edgecount+2]++; }
							else {nodeinfo[i][edgecount+3]++; }							 
					 }
					  
				  }
				  
			  }
		  }
		  
		//  s = graph.getNodeTable();
		//System.out.println("i"+edgecount);
		  return nodeinfo;
		  
		 
	}

}
