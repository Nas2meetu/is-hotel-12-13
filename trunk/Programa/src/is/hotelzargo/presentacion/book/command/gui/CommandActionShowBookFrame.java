package is.hotelzargo.presentacion.book.command.gui;

import is.hotelzargo.presentacion.book.gui.BookFrame;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.maingui.MainFrame;

public class CommandActionShowBookFrame implements Command {

	private boolean visible;
	
	public CommandActionShowBookFrame(boolean bool) {
		visible = bool;
	}
	
	@Override
	public Object execute() {

		MainFrame.getInstance().setVisible(!visible);
		BookFrame.getInstance().setVisible(visible);
		return null;
	}

}
