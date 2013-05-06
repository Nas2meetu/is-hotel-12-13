package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.client.ClientFrame;

public class CommandActionShowClientFrame implements Command {

	private boolean visible;
	
	public CommandActionShowClientFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public void execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ClientFrame.getInstance().setVisible(visible);
	}

}
