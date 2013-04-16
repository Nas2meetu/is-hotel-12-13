package is.hotelzargo.presentacion.comand;

import is.hotelzargo.presentacion.gui.BookFrame;
import is.hotelzargo.presentacion.gui.MainFrame;

public class CommandActionShowBookFrame implements Command {

	private boolean visible;
	
	public CommandActionShowBookFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public void execute() {

		MainFrame.getInstance().setVisible(!visible);
		BookFrame.getInstance().setVisible(visible);		
	}

}
