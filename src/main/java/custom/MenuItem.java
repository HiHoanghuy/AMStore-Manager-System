package custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


@SuppressWarnings("serial")
public class MenuItem extends javax.swing.JPanel {

    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;
    
	private final ArrayList<MenuItem> subMenu = new ArrayList<>();
    private ActionListener act;

    public void setShowing(boolean showing) {
        this.showing = showing;
    }
    public ArrayList<MenuItem> getSubMenu() {
        return subMenu;
    }

    public MenuItem(Icon icon, String menuName,ActionListener act,Boolean block,Color color1, Color color2, MenuItem... subMenu) {
    	initComponents(block, color1, color2);
    	setForeground(new Color(255, 255, 255));
        lbIcon.setIcon(icon);
        lbName.setText(menuName);
        if (act != null) {
            this.act = act;
        }
        this.setSize(new Dimension(162, 45));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, 45));
        for (int i = 0; i < subMenu.length; i++) {
            this.subMenu.add(subMenu[i]);
            subMenu[i].setVisible(false);
        }
    }
    
    public void translateName(String newName) {
    	lbName.setText(newName);
    }
   
    private void initComponents(boolean block, Color color1, Color color2) {

        jSeparator1 = new javax.swing.JSeparator();
        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbName.setText("Menu Name Here ...");

        lbName.setForeground(new Color(255, 255, 255));
        setBackground(color1);
//        setOpaque(false);
        
//      Mouse event set menu color
        this.addMouseListener(new MenuItemMouse(this, color1, color2));

//      Mouse event show/hide menu
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        if(block) {
//        	set menu
            layout.setHorizontalGroup(
            	layout.createParallelGroup(Alignment.LEADING)
            		.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            		.addGroup(layout.createSequentialGroup()
            			.addGap(37)
            			.addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            			.addPreferredGap(ComponentPlacement.RELATED)
            			.addComponent(lbName, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
            			.addContainerGap())
            );
            layout.setVerticalGroup(
            	layout.createParallelGroup(Alignment.TRAILING)
            		.addGroup(layout.createSequentialGroup()
            			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            			.addGroup(layout.createParallelGroup(Alignment.LEADING)
            				.addGroup(layout.createSequentialGroup()
            					.addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
            					.addContainerGap())
            				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
            					.addComponent(lbName, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
            					.addGap(0))))
            );
        } else {
//        	set submenu
        	layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
        }
        this.setLayout(layout);

    }

    private boolean showing = false;
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (showing) {
            hideMenu();
        } else {
            showMenu();
        }
        if (act != null) {
            act.actionPerformed(null);
        }
    }//GEN-LAST:event_formMousePressed

    private void showMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < subMenu.size(); i++) {
                    sleep();
                    subMenu.get(i).setVisible(true);
                }
                showing = true;
                getParent().repaint();
                getParent().revalidate();
            }
        }).start();
    }

    private void hideMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = subMenu.size() - 1; i >= 0; i--) {
                    sleep();
                    subMenu.get(i).setVisible(false);
                    subMenu.get(i).hideMenu();
                }
                getParent().repaint();
                getParent().revalidate();
                showing = false;
            }
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
        }
    }
    private class MenuItemMouse implements MouseListener {
		JPanel panel;
		Color color1, color2;
		public MenuItemMouse (JPanel panel, Color color1, Color color2) {
			this.panel = panel;
			this.color1 = color1;
			this.color2 = color2;
		}
		 @Override
		 public void mouseClicked(MouseEvent e) {
		 	panel.setBackground(color2);
		 }
		@Override
		public void mousePressed(MouseEvent e) {
//			panel.setBackground(color2);
		}
		 @Override
		 public void mouseReleased(MouseEvent e) {
//			panel.setBackground(color2);
		 }
		 @Override
		 public void mouseEntered(MouseEvent e) {
		 	panel.setBackground(color2);
		 }
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(color1);
		}
	}
}
