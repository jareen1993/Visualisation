
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPopupMenu;

import prefuse.action.assignment.ColorAction;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.data.Graph;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class FinalControlListener extends ControlAdapter implements Control  {

	public void itemClicked(VisualItem item, MouseEvent e) 
	{
		if(item instanceof NodeItem)
		{
			
			//int neutral,left,congress=0;
			String NAME = ((String) item.get("Name"));
			String STAND = (String) item.get("Stand");
			int itemno = item.getRow();
			JPopupMenu jpub = new JPopupMenu();
			jpub.add("id no.: " +itemno);
			jpub.add("Name: " + NAME);
			jpub.add("Stand: " + STAND);
			int nodedegree = ((NodeItem) item).getDegree()  ;
			jpub.add("Degree: " + nodedegree);
			Graph graph =  ((NodeItem) item).getGraph();
	    	int edgecount = graph.getEdgeCount();
		    //int nodecount = graph.getNodeCount();
			
			int[][] tab = nodeinfo.tabularinfo(graph);
		

			jpub.add("Left: " +tab[itemno][edgecount+1] );
			jpub.add("Neutral: " + tab[itemno][edgecount+2] );
			jpub.add("Cong: " + tab[itemno][edgecount+3]);
			
			jpub.show(e.getComponent(),1,1);

		}

		
		
		
		
		
		
		
	}

}

