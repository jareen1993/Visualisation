import javax.xml.soap.Node;

import prefuse.data.Graph;
import prefuse.data.Table;


public class Cal_triad {
	
	public int same_triad=0;
	public float same_triad_ratio=0;
	
	public float triad (Graph g) {
		int num_triad=0;
		Table r2 = g.getNodeTable();
		for (int i=0; i< g.getEdgeCount();i++) {
			int source_node= g.getSourceNode(i);
			int target_node= g.getTargetNode(i);
			int j=i+1;
			while(j<g.getEdgeCount() && g.getSourceNode(j)==source_node) {
				
				if (g.getEdge(g.getNode(g.getTargetNode(j)), g.getNode(target_node)) !=null) {
					
					String s1 =   r2.getString(source_node,1);
					String s2 =  r2.getString(target_node,1);
					String s3 =  r2.getString(g.getTargetNode(j),1);
					if(s1.equals(s2) && s2.equals(s3)) {
						same_triad++;
					}
					num_triad++;
					
				}
				j++;
			}
		}
		//System.out.println("number of triads =" + num_triad);
		//System.out.println(same_triad+","+ num_triad);
		int nc2= g.getNodeCount()*(g.getNodeCount()-1)/2;
		same_triad_ratio= (float)(same_triad)/(nc2);
		//System.out.println("clustering coefficient =" + (float)(num_triad)/(nc2));
		return((float)(num_triad)/(nc2));
	}

}
