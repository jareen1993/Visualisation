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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.DragControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
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

		public class Imp 
		{
	    	//static Graph graph1 = new Graph();
	    	int source=0,target=0;
	    	String names2,values2;
	    	
	    	public Graph read() throws FileNotFoundException 
		    { 
	    		Graph g = new Graph();
	    		Imp parser = new Imp("polbooks.gml");
	    		g = parser.processLineByLine();
	    		
	    		return g; 
		    }
	    	public Imp(String aFileName)
	    	{
	    		fFile = new File(aFileName);  
	    	}
		  
		  /** Template method that calls {@link #processLine(String)}.  */
	    	public final Graph processLineByLine() throws FileNotFoundException 
	    	{
	    		Graph graph1 = new Graph();
				Graph g = new Graph();
			    //Note that FileReader is used, not File, since File is not Closeable
			    Scanner scanner = new Scanner(new FileReader(fFile));
			    try 
			    {
			    	
			      //first use a Scanner to get each line
			    	graph1.addColumn("Name", String.class);
					graph1.addColumn("Stand", String.class);
					while ( scanner.hasNextLine() )
			        {  	  
						graph1=processLine(scanner.nextLine(), graph1);
			        }
			    }
			    finally 
			    {
				      //ensure the underlying stream is always closed
				      //this only has any effect if the item passed to the Scanner
				      //constructor implements Closeable (which it does in this case).
			    	scanner.close();
			    }
			    g=graph1;
			    return g;
		  }
		  
	
		  protected Graph processLine(String aLine, Graph g)
		  {  
			    //use a second Scanner to parse the content of each line 
				String firstword = new String();
			    Graph graph1=g;		    
			    String inter = new String();
			    int firstspace =  aLine.indexOf(32);
			    int b=0; int d,d1;int directed ;
			   
			    if (firstspace==-1){firstword = aLine;}
			    else
			    {
			    	while(aLine.charAt(firstspace+1)==' ')
			    	{firstspace=firstspace+1;}
			    	b = aLine.indexOf(32,firstspace+1);
			    	
			    	if(b==-1){ firstword = aLine.substring(firstspace,aLine.length()); }
			    	else {firstword = aLine.substring(firstspace+1,b);}
			    }
			    
			    
			    if (firstword.equals("directed")){directed = aLine.charAt(b+1);}
			    else if (firstword.equals("label"))
			    {
			    	 d = aLine.indexOf(34);
			    	 names2 = aLine.substring(d+1,aLine.length());	 
			    }
			    else if (firstword.equals("value"))
			    {
			    	Node n = graph1.addNode();
			    	 d1 = aLine.indexOf(34);
			    	 values2 = aLine.substring(d1+1,aLine.length()-1);	
			    	 n.set("Name", names2);
			    	 n.set("Stand", values2);
			    }
			    else if (firstword.equals("source"))
			    {
			    	inter = aLine.substring(b+1,aLine.length());
			    	source = Integer.parseInt(inter);
			   	}		    
			    else if (firstword.equals("target"))
			    {
			    	inter = aLine.substring(b+1,aLine.length());
			    	target = Integer.parseInt(inter);
			    	graph1.addEdge(source, target);
			    }
			    else {}	    
			    return graph1;
		  }
		  
		  
		  // PRIVATE 
		  private final File fFile;	  
		  private static void log(Object aObject){ System.out.println(String.valueOf(aObject));}		  
		  public void give_edge_vis(Graph graph)
		  {
			  
			   int edgecount = graph.getEdgeCount();
			    int nodecount = graph.getNodeCount();
			    int c_same=0;
			    int c_ltoc=0;
			    int c_lton=0;
			    int c_ntoc=0;
			    
			    int[][] nodeinformation  = new int[nodecount][edgecount];
			    nodeinformation= nodeinfo.tabularinfo(graph);
			    
			    graph.getEdgeTable().addColumn("Type", String.class);
			    int i;
			    int src,trgt;
			    String s1,s2;
			    for(i=0;i<edgecount;i++)
			    {
			    	src = graph.getSourceNode(i);
			    	trgt = graph.getTargetNode(i);
			    	Table s = graph.getNodeTable();
			    	s1 = (String) s.get(src,"Stand");
			    	s2 = (String) s.get(trgt,"Stand");
			    	if(s1.equals(s2)){ graph.getEdgeTable().set(i, "Type", "Same"); c_same++;}
			    	else
			    	{
			    		if(s1.equals("l") && s2.equals("c")){ graph.getEdgeTable().set(i, "Type", "ltoc"); c_ltoc++;}
			    	if(s1.equals("c") && s2.equals("l")){ graph.getEdgeTable().set(i, "Type", "ltoc");c_ltoc++;}
			    	if(s1.equals("l") && s2.equals("n")){ graph.getEdgeTable().set(i, "Type", "lton");c_lton++;}
			    	if(s1.equals("n") && s2.equals("l")){ graph.getEdgeTable().set(i, "Type", "lton");c_lton++;}
			    	if(s1.equals("n") && s2.equals("c")){ graph.getEdgeTable().set(i, "Type", "ntoc");c_ntoc++;}
			    	if(s1.equals("c") && s2.equals("n")){ graph.getEdgeTable().set(i, "Type", "ntoc");c_ntoc++;}
			    	}
			    	 
			    }
			    System.out.println("Total Edges = " +edgecount);
			    System.out.println("Same Edges = " +c_same);
			    System.out.println("L to N Edges = " +c_lton);
			    System.out.println("N to C Edges = " +c_ntoc);
			    System.out.println("L to C Edges = " +c_ltoc);
			    Visualization vis = new Visualization();
		        vis.add("graph", graph);
		        vis.setInteractive("graph.edges", null, false);
		        FinalRenderer r = new FinalRenderer();
			    vis.setRendererFactory(new DefaultRendererFactory(r));
	int[] palette = {ColorLib.rgb(100, 210, 100), ColorLib.rgb(255,100,100),ColorLib.rgb(100,100,255),ColorLib.rgb(242,181,82)};
	DataColorAction fill = new DataColorAction("graph.edges","Type",Constants.NOMINAL,
            VisualItem.STROKECOLOR, palette);
	
	ColorAction nodes = new ColorAction("graph.nodes", 
	        VisualItem.FILLCOLOR, ColorLib.gray(200));
	        
	        
	        ActionList animate = new ActionList(Activity.INFINITY);
	        animate.add(new ForceDirectedLayout("graph"));
	        animate.add(fill);
	        //animate.add(fill1);
	        //animate.add(fill2);
	        animate.add(nodes);
	       animate.add(new RepaintAction());
	        
	        vis.putAction("layout", animate);
	        
	        // -- 5. the display and interactive controls -------------------------
	        
	        Display d = new Display(vis);
	        d.setSize(2000, 1500); // set display size
	        // drag individual items around
	        d.addControlListener(new DragControl());
	        // pan with left-click drag on background
	        d.addControlListener(new PanControl()); 
	        // zoom with right-click drag
	        d.addControlListener(new ZoomControl());
	        d.addControlListener(new WheelZoomControl());
	        d.addControlListener(new ZoomToFitControl());
	        d.addControlListener(new NeighborHighlightControl());

	        
	      d.addControlListener(new FinalControlListener());
	    //   d.addControlListener(new HoverActionControl("fill"));
	        

	        
	        // create a new window to hold the visualization
	        JFrame frame = new JFrame("prefuse example");
	        // ensure application exits when window is closed
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(d);
	        frame.pack();           // layout components in window
	        frame.setVisible(true); // show the window
	        
	        // assign the colors
	        vis.run("color");
	        // start up the animated layout
	        vis.run("layout");
	
	
		  }
		  
		  public void give_vis(Graph x)
		  {
			    
			    int edgecount = x.getEdgeCount();
			    int nodecount = x.getNodeCount();
			    
		    int[][] nodeinformation  = new int[nodecount][edgecount];
			    nodeinformation= nodeinfo.tabularinfo(x);
				
	
		        Visualization vis = new Visualization();
		        vis.add("graph", x);
		        vis.setInteractive("graph.edges", null, false);
			        LabelRenderer r1 = new LabelRenderer("Name");
		        r1.setRoundedCorner(8, 8);
		        
		        FinalRenderer r = new FinalRenderer();
			        vis.setRendererFactory(new DefaultRendererFactory(r));
		        int[] palette = {ColorLib.rgb(100, 210, 100), ColorLib.rgb(255,100,100),ColorLib.rgb(100,100,255)};
		        DataColorAction fill = new DataColorAction("graph.nodes", "Stand",Constants.NOMINAL,VisualItem.FILLCOLOR,palette);
		        ColorAction edges = new ColorAction("graph.edges", 
		        VisualItem.STROKECOLOR, ColorLib.gray(200));
		        
		        fill.add(VisualItem.FIXED, ColorLib.gray(0));
		        fill.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,200,125));
		        
		        
		        ActionList animate = new ActionList(Activity.INFINITY);
		        animate.add(new ForceDirectedLayout("graph"));
		        animate.add(fill);
		        //animate.add(fill1);
		        //animate.add(fill2);
		        animate.add(edges);
		       animate.add(new RepaintAction());
		        
		        vis.putAction("layout", animate);
		        
		        // -- 5. the display and interactive controls -------------------------
		        
		        Display d = new Display(vis);
		        d.setSize(2000, 1500); // set display size
		        
		        d.addControlListener(new DragControl());
		        
		        d.addControlListener(new PanControl()); 
		       
		        d.addControlListener(new ZoomControl());
		        d.addControlListener(new WheelZoomControl());
		        d.addControlListener(new ZoomToFitControl());
		        d.addControlListener(new NeighborHighlightControl());

		        
		      d.addControlListener(new FinalControlListener());
		    
		        JFrame frame = new JFrame("prefuse example");
		        JButton b = new JButton("edge view");
		        frame.add(b,BorderLayout.SOUTH);
		        Main l = new Main();
		        b.addActionListener(l);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.add(d);
		        frame.pack();           // layout components in window
		        frame.setVisible(true); // show the window
		        
		        
		        vis.run("color");
		        
		        vis.run("layout");      
			  
		  }
		} 
