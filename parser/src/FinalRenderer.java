import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

public class FinalRenderer extends AbstractShapeRenderer
{
	protected Ellipse2D m_box = new Ellipse2D.Double();
	
	@Override
	/**
	 * Gives different shape to nodes of different affliation
	 * <p>
	 * @param  item is VisualItem for which shape is to be changed
	 * @return Shape of the item
	 */
	protected Shape getRawShape(VisualItem item) 
	{	
		int x=0;
		if(item.get("Stand").equals("l")) {x=15;}
		if(item.get("Stand").equals("c")) {x=22;}
		if(item.get("Stand").equals("n")) {x=10;}
		
		m_box.setFrame(item.getX(), item.getY(),x,x);

		return m_box;
	}
}