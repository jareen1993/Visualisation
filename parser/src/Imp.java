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
	    		log("Done.");
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
		  
		  public void give_vis(Graph x)
		  {
			// -- 2. the visualization --------------------------------------------
		        
	    		// add the graph to the visualization as the data group "graph"
	    		// nodes and edges are accessible as "graph.nodes" and "graph.edges"
	    		Visualization vis = new Visualization();
	    		vis.add("graph", x);
	    		vis.setInteractive("graph.edges", null, false);
	        
	    		// -- 3. the renderers and renderer factory ---------------------------
	        
		        // draw the "name" label for NodeItems
		        LabelRenderer r = new LabelRenderer("Name");
		        r.setRoundedCorner(8, 8); // round the corners
		        
		        // create a new default renderer factory
		        // return our name label renderer as the default for all non-EdgeItems
		        // includes straight line edges for EdgeItems by default
		        vis.setRendererFactory(new DefaultRendererFactory(r));
		        	        
		        // -- 4. the processing actions ---------------------------------------
		        
		        // create our nominal color palette
		        // pink for females, baby blue for males
		        int[] palette = new int[] {
		            ColorLib.rgb(255,180,180), ColorLib.rgb(190,190,255),ColorLib.rgb(90,90,255)
		        };
		        // map nominal data values to colors using our provided palette
		        DataColorAction fill = new DataColorAction("graph.nodes", "Stand",
		                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
		        // use black for node text
		        ColorAction text = new ColorAction("graph.nodes",
		                VisualItem.TEXTCOLOR, ColorLib.gray(0));
		        // use light grey for edges
		        ColorAction edges = new ColorAction("graph.edges",
		               VisualItem.STROKECOLOR, ColorLib.gray(200));
		        
		        // create an action list containing all color assignments
		        ActionList color = new ActionList();
		        color.add(fill);
		        color.add(text);
		       color.add(edges);
		        
		        // create an action list with an animated layout
		        ActionList layout = new ActionList(Activity.INFINITY);
		        layout.add(new ForceDirectedLayout("graph"));
		        layout.add(new RepaintAction());
		        
		        // add the actions to the visualization
		        vis.putAction("color", color);
		        vis.putAction("layout", layout);
		        
		        
		        // -- 5. the display and interactive controls -------------------------
		        
		        Display d = new Display(vis);
		        d.setSize(2000, 1500); // set display size
		        // drag individual items around
		        d.addControlListener(new DragControl());
		        // pan with left-click drag on background
		        d.addControlListener(new PanControl()); 
		        // zoom with right-click drag
		        d.addControlListener(new ZoomControl());
		        
		        // -- 6. launch the visualization -------------------------------------
		        
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
		} 
