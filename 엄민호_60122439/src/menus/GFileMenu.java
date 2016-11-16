package menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GConstants;
import constants.GConstants.EFileMenuItem;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public GFileMenu() {
		super(GConstants.FILEMENU_TITLE);
		for(EFileMenuItem eMenuItem : EFileMenuItem.values()){
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			this.add(menuItem);
		}	
	}		
}
