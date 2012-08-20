import prefuse.data.Edge;
import prefuse.data.Table;
import prefuse.data.Graph;


public class nodeinfo {
	
	public static int[][] tabularinfo(Graph graph) 
	{
		int edgecount = graph.getEdgeCount();
	    int nodecount = graph.getNodeCount();
	    
	    int i,j; Edge e;int src,trgt;
	    int[][] nodeinfo = new int[nodecount][edgecount];
	    for(i=0;i<nodecount;i++)
	    	for(j=0;j<edgecount;j++)
	    		nodeinfo[i][j]=0;	
	    		 
		  for(i=0;i<edgecount;i++)
		  {
			  src = graph.getSourceNode(i);
			  trgt = graph.getTargetNode(i);
			  nodeinfo[src][trgt]=1;
			  nodeinfo[trgt][src]=1;
		  }
		  
		  return nodeinfo;
		  
		 
	}

}
