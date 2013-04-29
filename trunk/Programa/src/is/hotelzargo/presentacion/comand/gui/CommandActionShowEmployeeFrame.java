package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.employee.EmployeeFrame;

public class CommandActionShowEmployeeFrame implements Command {

	private boolean visible;
	
	public CommandActionShowEmployeeFrame(boolean bool) {
		visible = bool;
	}
	@Override
	public void execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		EmployeeFrame.getInstance().setVisible(visible);
	}

}
