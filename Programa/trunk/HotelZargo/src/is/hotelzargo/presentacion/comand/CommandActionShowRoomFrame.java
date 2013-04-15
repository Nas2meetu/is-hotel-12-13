package is.hotelzargo.presentacion.comand;

import is.hotelzargo.presentacion.gui.RoomFrame;
import is.hotelzargo.presentacion.gui.MainFrame;

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
