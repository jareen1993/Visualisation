
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.controls.DragControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class FinalControlListener extends ControlAdapter implements Control  {
	/**
	 * Opens a pop up window on a click on the item
	 * <p>
	 * @param  item is VisualItem to be clicked for the pop up action
	 * @return void
	 */
	public void itemClicked(VisualItem item, MouseEvent e) 
	{
		if(item instanceof NodeItem)
		{
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
		    
			int[][] tab = nodeinfo.tabularinfo(graph);
		
			jpub.add("Left: " +tab[itemno][edgecount+1] );
			jpub.add("Neutral: " + tab[itemno][edgecount+2] );
			jpub.add("Cong: " + tab[itemno][edgecount+3]);
			
			jpub.show(e.getComponent(),1,1);
		}	
	}
}

