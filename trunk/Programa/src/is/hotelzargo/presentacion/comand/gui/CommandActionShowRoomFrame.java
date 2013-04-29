package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.room.RoomFrame;

public class CommandActionShowRoomFrame implements Command {

	private boolean visible;
	
	public CommandActionShowRoomFrame(boolean bool) {
		visible = bool;
	}
	@Override
	public void execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		RoomFrame.getInstance().setVisible(visible);
	}

}
