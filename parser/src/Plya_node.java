
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
public class Plya_node 
{
	
	public int[][] make_table(Graph g)
	{
		Table r2 = g.getNodeTable();
		int[][] nodeinfo = new int[g.getNodeCount()][10];
		for(int i=0;i<g.getNodeCount();i++)
		{
			for(int j=0;j<10;j++)
			{
				nodeinfo[i][j]=0;
				
			}
			nodeinfo[i][5]=g.getDegree(i);
		}
		for(int i=0;i<g.getEdgeCount() ;i++ )
		{
			int src = g.getSourceNode(i);
			int trgt = g.getTargetNode(i);
			String s1 =   r2.getString(src,1);
			String s2 =  r2.getString(trgt,1);
			if(s1.equals(s2)) 
				{
					nodeinfo[trgt][3]++;
					nodeinfo[src][3]++;
				}
			else 
				{
					nodeinfo[trgt][4]++;
					nodeinfo[src][4]++;
				}
			if(s1.equals("l"))
			{
				nodeinfo[trgt][0]++;
				
				
			}
			if(s1.equals("n"))
			{
				nodeinfo[trgt][1]++;
				
			}
			if(s1.equals("c"))
			{
				nodeinfo[trgt][2]++;
				
			}
			
			if(s2.equals("l"))
			{
				nodeinfo[src][0]++;
				
			}
			if(s2.equals("n"))
			{
				nodeinfo[src][1]++;
				
			}
			if(s2.equals("c"))
			{
				nodeinfo[src][2]++;
			}
					
		}
		//for(int i=0;i<g.getNodeCount();i++)
		//{
			//for(int j=0;j<10;j++)
			//{
			//	System.out.print(nodeinfo[i][j] + " , ");
				
		//}
			//System.out.println();
		//}
		return nodeinfo;
	}
	public void max_lcr(Graph g)
	{
		Table r2 = g.getNodeTable();
		int h_nl=0;
		int h_nr=0;
		int h_nc=0;
		int same=0;
		int diff=0;
		int deg=0;
		int nh_nl=0;
		int nh_nr=0;
		int nh_nc=0;
		int nsame=0;
		int ndiff=0;
		int ndeg=0;
		int[][] nodeinfo = new int[g.getNodeCount()][10];
		nodeinfo = make_table(g);
		for(int i=0; i<g.getNodeCount();i++)
		{
			if(nh_nl<nodeinfo[i][0])
			{				
				h_nl=i;
				nh_nl=nodeinfo[i][0];	
			}			
			if(nh_nr<nodeinfo[i][1])
			{
				h_nr=i;
				nh_nr=nodeinfo[i][1];			
			}
			if(nh_nc<nodeinfo[i][2])
			{
				h_nc=i;
				nh_nc=nodeinfo[i][2];
			}			
			if(nsame<nodeinfo[i][3])
			{
				same=i;
				nsame=nodeinfo[i][3];
			}
			if(ndiff<nodeinfo[i][4])
			{
				diff=i;
				ndiff=nodeinfo[i][4];
			}
			if(ndeg<nodeinfo[i][5])
			{
				deg=i;
				ndeg=nodeinfo[i][5];
			}
		}
		String s1 =   r2.getString(h_nl,0);
		String s2 =   r2.getString(h_nr,0);
		String s3 =   r2.getString(h_nc,0);
		String s4 =   r2.getString(same,0);
		String s5 =   r2.getString(diff,0);
		String s6 =   r2.getString(deg,0);
		
		
		System.out.println("Max Edges in l = "+s1+" with number = "+ nh_nl);
		System.out.println("Max Edges in n = "+s2+" with number = "+ nh_nr);
		System.out.println("Max Edges in c = "+s3+" with number = "+ nh_nc);
		System.out.println("Max Edges in same = "+s4+" with number "+ nsame);
		System.out.println("Max Edges in diff = "+ s5 + " with number "+ ndiff);
		System.out.println("Max Degree = "+s6+" with number "+ ndeg);
		
	
	}
	public void min_lcr(Graph g)
	{
		Table r2 = g.getNodeTable();
		int h_nl=0;
		int h_nr=0;
		int h_nc=0;
		int same=0;
		int diff=0;
		int deg=0;
		int nh_nl=1110;
		int nh_nr=1110;
		int nh_nc=1110;
		int nsame=1110;
		int ndiff=1110;
		int ndeg=1110;
		int[][] nodeinfo = new int[g.getNodeCount()][10];
		nodeinfo = make_table(g);
		for(int i=0; i<g.getNodeCount();i++)
		{
			if(nh_nl>nodeinfo[i][0])
			{				
				h_nl=i;
				nh_nl=nodeinfo[i][0];	
			}			
			if(nh_nr>nodeinfo[i][1])
			{
				h_nr=i;
				nh_nr=nodeinfo[i][1];			
			}
			if(nh_nc>nodeinfo[i][2])
			{
				h_nc=i;
				nh_nc=nodeinfo[i][2];
			}			
			if(nsame>nodeinfo[i][3])
			{
				same=i;
				nsame=nodeinfo[i][3];
			}
			if(ndiff>nodeinfo[i][4])
			{
				diff=i;
				ndiff=nodeinfo[i][4];
			}
			if(ndeg>nodeinfo[i][5])
			{
				deg=i;
				ndeg=nodeinfo[i][5];
			}
		}
		String s1 =   r2.getString(h_nl,0);
		String s2 =   r2.getString(h_nr,0);
		String s3 =   r2.getString(h_nc,0);
		String s4 =   r2.getString(same,0);
		String s5 =   r2.getString(diff,0);
		String s6 =   r2.getString(deg,0);
		
		
		System.out.println("Min Edges in l = "+s1+" with number = "+ nh_nl);
		System.out.println("Min Edges in n = "+s2+" with number = "+ nh_nr);
		System.out.println("Min Edges in c = "+s3+" with number = "+ nh_nc);
		System.out.println("Min Edges in same = "+s4+" with number "+ nsame);
		System.out.println("Min Edges in diff = "+ s5 + " with number "+ ndiff);
		System.out.println("Min Degree = "+s6+" with number "+ ndeg);
		
	
	}
}
