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
		Imp parser = new Imp("polbooks.gml");
		Graph g = parser.read();
		parser.give_vis(parser.read());
		File file = new File("randomgraph.csv");
		Writer output = null;
		output = new BufferedWriter(new FileWriter(file));
		output.write("%graph id,same_affiliation\n");
		for(int i=0; i<30; i++)
		{
			New_Class n = new New_Class();
			
		    n.create_random(g);
		    int same_affiliation= n.count_neu+ n.count_right+ n.count_left;
		    output.write(i+","+same_affiliation+"\n");
		    /*System.out.println("Neutral Book Edges = " + n.count_neu);
		    System.out.println("Right Book Edges = "+ n.count_right);
		    System.out.println("Left Book Edges = "+ n.count_left);*/
		    
		}
		output.close();
	
		

	}

}
