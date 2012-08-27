import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import prefuse.data.Graph;


public class Main extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/*JFrame f = new JFrame("Prefue-edgeview");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000,1000);
		f.setVisible(true);
		*/
		
		Imp parser_1 = new Imp("polbooks.gml");		
		Graph g1 = new Graph();
		try {
			g1 = parser_1.read();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parser_1.give_edge_vis(g1);
		
		
	}
	
	

}
