
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPopupMenu;

import prefuse.action.assignment.ColorAction;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class FinalControlListener extends ControlAdapter implements Control {

	public void itemClicked(VisualItem item, MouseEvent e) 
	{
		if(item instanceof NodeItem)
		{
			String NAME = ((String) item.get("Name"));
			String STAND = (String) item.get("Stand");
			
			JPopupMenu jpub = new JPopupMenu();
			jpub.add("Name: " + NAME);
			jpub.add("Stand: " + STAND);
			jpub.show(e.getComponent(),(int) item.getX(), (int) item.getY());

		}
	}

}
