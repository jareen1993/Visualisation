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

public class runner 
{
	
	public static void main(String[] args) throws Exception 
	{
		int ocount_left=0;
		int ocount_right=0;
		int ocount_neu=0;
		Imp parser = new Imp("polbooks.gml");		
		Graph g1 = parser.read();
		parser.give_vis(g1);
		for(int i=0; i< g1.getEdgeCount();i++)
		{
			Table r2 = g1.getNodeTable();
			//g1.getEdge(i);
			int src = g1.getSourceNode(i);
			int trgt = g1.getTargetNode(i);
			//System.out.println(src<trgt);
			String s =   r2.getString(src,1);
			String s2 =  r2.getString(trgt,1);
			if (s.equals(s2))
			{
				if (s.equals("l"))
				{
					ocount_left++;
				}
				if (s.equals("c"))
				{
					ocount_right++;
				}
				if (s.equals("n"))
				{
					ocount_neu++;
				}
			}
		}
		New_Class n1 = new New_Class();
		n1.node_cal(g1);
		
		float ol_den= (float)(ocount_left)/(n1.ncount_left);
	    float or_den= (float)(ocount_right)/(n1.ncount_right);
	    float on_den= (float)(ocount_neu)/(n1.ncount_neu);
	    System.out.println("left density = " + ol_den);
	    System.out.println("right density = " + or_den);
	    System.out.println("Neutral density = " + on_den);
	    
	    float b1 = ocount_neu+ ocount_right+ ocount_left;
	    float b2= g1.getEdgeCount();
		float oAff_ratio= (float)(b1/b2);
		System.out.println("Aff ratio in original graph is = "+ oAff_ratio);
				
		File file = new File("randomgraph.csv");
		Writer output = null;
		output = new BufferedWriter(new FileWriter(file));
		output.write("%graph id,same_affiliation_ratio,clustering_coeff_total,clustering_coeff_same\n");
		for(int i=0; i<100; i++)
		{
			
			Graph neeraj = new Graph();
			New_Class n = new New_Class();
			Imp parser2 =new Imp("polbooks.gml");	
			Graph g2 = parser2.read();
			int num_edges = g2.getEdgeCount();
			for(int i1=0; i1< num_edges;i1++)
			{
				g2.removeEdge(i1);
			}
			neeraj = n.create_random(g2, num_edges);
			
		    int same_affiliation= n.count_neu+ n.count_right+ n.count_left;
		    float Aff_ratio;		    
		    Aff_ratio = (float)(same_affiliation)/(num_edges);  		   
		    Cal_triad c= new Cal_triad();	   
		     
		    float f=c.triad(neeraj);
		    
		    output.write(i+","+Aff_ratio+","+f+","+c.same_triad_ratio+"\n");
		    
		    
		}

		output.close();
		New_Class n = new New_Class();
		n.node_cal(g1);
		
		Cal_triad ct= new Cal_triad();
		float org_ct= ct.triad(g1);
		float same_ct = ct.same_triad_ratio;
		System.out.println("clustering coefficient of original dataset =" +org_ct);
		System.out.println("Same clustering coefficient of original dataset =" +same_ct);
		
		Plya_node pn= new Plya_node();
		pn.max_lcr(g1);
		pn.min_lcr(g1);
	}


}