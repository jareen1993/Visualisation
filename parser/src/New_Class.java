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
import java.util.Random;

import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
public class New_Class 
{
	int count_neu = 0;
	int count_right = 0;
	int count_left = 0;
	int ncount_neu=0;
	int ncount_right=0;
	int ncount_left=0;
	
	
	public Graph create_random(Graph g, int num)
	{
		//runner run = new runner(); 
		
		Table r2 = g.getNodeTable();
		Graph g1 = new Graph();
		g1=g;
		
		int n_edges = num;
		int n_nodes = g1.getNodeCount();
		Random rands = new Random();
		
		for(int i=0;i<n_edges;i++)
		{
			int first = rands.nextInt(n_nodes-1);
			int second = rands.nextInt(n_nodes-1);
			while(first==second)
			{
				//System.out.println("Error Might in error");
				second = rands.nextInt(n_nodes-1);
			}
			g1.addEdge(first, second);
			
			
			String s =   r2.getString(first,1);
			String s2 =  r2.getString(second,1);
			 //System.out.println(s);
			 //System.out.println(s2);
			if (s.equals(s2))
			{
				if (s.equals("l"))
				{
					count_left++;
				}
				if (s.equals("c"))
				{
					count_right++;
				}
				if (s.equals("n"))
				{
					count_neu++;
				}
			}
		}	
		
		n_edges=0;
		return g1;
	}
	public void node_cal(Graph g)
	{
		Table r2 = g.getNodeTable();
		for(int i=0; i< g.getNodeCount(); i++)
		{
			String s =   r2.getString(i,1);
			
			if (s.equals("n"))
			{
				ncount_neu++;
			}
			if (s.equals("c"))
			{
				ncount_right++;
			}
			if (s.equals("l"))
			{
				ncount_left++;
			}
			
		}
		
	}
}
