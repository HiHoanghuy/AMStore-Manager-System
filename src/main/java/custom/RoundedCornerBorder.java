package custom;

import java.util.Objects;
import java.awt.*;
import java.awt.geom.*;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;

public class RoundedCornerBorder extends AbstractBorder{
	  protected static final int ARC = 25;
	  @Override public void paintBorder(
	      Component c, Graphics g, int x, int y, int width, int height) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    int r = ARC;
	    int w = width  - 1;
	    int h = height - 1;

	    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
	    if (c instanceof JPopupMenu) {
	      g2.setPaint(c.getBackground());
	      g2.fill(round);
	    } else {
	      Container parent = c.getParent();
	      if (Objects.nonNull(parent)) {
	        g2.setPaint(parent.getBackground());
	        Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
	        corner.subtract(round);
	        g2.fill(corner);
	      }
	    }
	    g2.setPaint(Color.blue);
	    g2.draw(round);
	    g2.dispose();
	  }
	  @Override public Insets getBorderInsets(Component c) {
	    return new Insets(4, 8, 4, 8);
	  }
	  @Override public Insets getBorderInsets(Component c, Insets insets) {
	    insets.set(4, 8, 4, 8);
	    return insets;
	  }
}
