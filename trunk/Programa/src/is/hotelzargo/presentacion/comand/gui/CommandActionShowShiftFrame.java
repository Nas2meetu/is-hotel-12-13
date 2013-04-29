package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.shift.ShiftFrame;

public class CommandActionShowShiftFrame implements Command {

	private boolean visible;
	
	public CommandActionShowShiftFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public void execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		ShiftFrame.getInstance().setVisible(visible);
	}

}
