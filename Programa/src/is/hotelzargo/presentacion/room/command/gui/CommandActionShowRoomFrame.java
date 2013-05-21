package is.hotelzargo.presentacion.room.command.gui;

import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.maingui.MainFrame;
import is.hotelzargo.presentacion.room.gui.RoomFrame;

public class CommandActionShowRoomFrame implements Command {

	private boolean visible;
	
	public CommandActionShowRoomFrame(boolean bool) {
		visible = bool;
	}
	@Override
	public Object execute() {
		
		MainFrame.getInstance().setVisible(!visible);
		RoomFrame.getInstance().setVisible(visible);
		return null;
	}

}
