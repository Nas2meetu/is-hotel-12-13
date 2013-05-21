package is.hotelzargo.presentacion.service.command.gui;

import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.maingui.MainFrame;
import is.hotelzargo.presentacion.service.gui.ServicesFrame;

public class CommandActionShowServicesFrame implements Command {
	
	private boolean visible;
	
	public CommandActionShowServicesFrame(boolean bool) {
		visible = bool;
	}

	@Override
	public Object execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ServicesFrame.getInstance().setVisible(visible);
		return null;
		
	}

}
