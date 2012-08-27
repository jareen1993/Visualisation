//import javax.xml.soap.Node;

import prefuse.data.Graph;
import prefuse.data.Table;
import java.io.FileNotFoundException;
import java.lang.*;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.activity.Activity;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.render.ShapeRenderer;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.data.io.TableReader;


public class Cal_triad {
	
	public int same_triad=0;
	public float same_triad_ratio=0;
	/**
	 * Calculates clustering coefficient for the given graph
	 * <p>
	 * @param  Graph g for which clustering coefficient is to be calculated
	 * @return float value of clustering coefficient
	 */
	public float triad (Graph g) {
		int num_triad=0;
		Table r2 = g.getNodeTable();
		for (int i=0; i< g.getEdgeCount();i++) {
			int source_node= g.getSourceNode(i);
			int target_node= g.getTargetNode(i);
			for(int j=i+1; j<g.getEdgeCount();j++) {
				if(g.getSourceNode(j)==source_node) {
					if(g.getEdge(g.getNode(target_node),g.getNode(g.getTargetNode(j))) !=null && g.getEdge(target_node,g.getTargetNode(j)) >i) {
						String s1 =   r2.getString(source_node,1);
						String s2 =  r2.getString(target_node,1);
						String s3 =  r2.getString(g.getTargetNode(j),1);
						if(s1.equals(s2) && s2.equals(s3)) {
							same_triad++;
						}
						num_triad++;
					}
					else if(g.getEdge(g.getNode(g.getTargetNode(j)), g.getNode(target_node)) !=null && g.getEdge(g.getTargetNode(j),target_node) >i) {
						String s1 =   r2.getString(source_node,1);
						String s2 =  r2.getString(target_node,1);
						String s3 =  r2.getString(g.getTargetNode(j),1);
						if(s1.equals(s2) && s2.equals(s3)) {
							same_triad++;
						}
						num_triad++;
					}	
				}
				else if(g.getTargetNode(j)==source_node) {
					if(g.getEdge(g.getNode(target_node),g.getNode(g.getSourceNode(j))) !=null && g.getEdge(target_node,g.getSourceNode(j)) >i) {
						String s1 =   r2.getString(source_node,1);
						String s2 =  r2.getString(target_node,1);
						String s3 =  r2.getString(g.getSourceNode(j),1);
						if(s1.equals(s2) && s2.equals(s3)) {
							same_triad++;
						}
						num_triad++;
					}
					else if(g.getEdge(g.getNode(g.getSourceNode(j)), g.getNode(target_node)) !=null && g.getEdge(g.getSourceNode(j),target_node)> i)
					{
						String s1 =   r2.getString(source_node,1);
						String s2 =  r2.getString(target_node,1);
						String s3 =  r2.getString(g.getSourceNode(j),1);
						if(s1.equals(s2) && s2.equals(s3)) {
							same_triad++;
						}
						num_triad++;
					}
				}
			}
		}
		
		int nc2= g.getNodeCount()*(g.getNodeCount()-1)/2;
		same_triad_ratio= (float)(same_triad)/(nc2);
		
		return((float)(num_triad)/(nc2));
	}

}
