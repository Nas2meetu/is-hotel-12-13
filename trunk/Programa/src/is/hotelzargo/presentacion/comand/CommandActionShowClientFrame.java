package is.hotelzargo.presentacion.comand;

import is.hotelzargo.presentacion.gui.ClientFrame;
import is.hotelzargo.presentacion.gui.MainFrame;

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
