package is.hotelzargo.presentacion.client.command.gui;

import is.hotelzargo.presentacion.client.gui.ClientFrame;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.maingui.MainFrame;

public class CommandActionShowClientFrame implements Command {

	private boolean visible;
	
	public CommandActionShowClientFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public Object execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ClientFrame.getInstance().setVisible(visible);
		return null;
	}

}
