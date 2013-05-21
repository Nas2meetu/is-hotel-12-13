package is.hotelzargo.presentacion.employee.command.gui;

import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.employee.gui.EmployeeFrame;
import is.hotelzargo.presentacion.maingui.MainFrame;

public class CommandActionShowEmployeeFrame implements Command {

	private boolean visible;
	
	public CommandActionShowEmployeeFrame(boolean bool) {
		visible = bool;
	}
	@Override
	public Object execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		EmployeeFrame.getInstance().setVisible(visible);
		return null;
	}

}
