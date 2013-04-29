package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.services.ServicesFrame;

public class CommandActionShowServicesFrame implements Command {
	
	private boolean visible;
	
	public CommandActionShowServicesFrame(boolean bool) {
		visible = bool;
	}

	@Override
	public void execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ServicesFrame.getInstance().setVisible(visible);
		
	}

}
