package is.hotelzargo.presentacion.shift.command.gui;

import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.maingui.MainFrame;
import is.hotelzargo.presentacion.shift.gui.ShiftFrame;

public class CommandActionShowShiftFrame implements Command {

	private boolean visible;
	
	public CommandActionShowShiftFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public Object execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ShiftFrame.getInstance().setVisible(visible);
		return null;
	}

}
