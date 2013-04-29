package is.hotelzargo.presentacion.comand.gui;

import is.hotelzargo.presentacion.comand.Command;
import is.hotelzargo.presentacion.gui.MainFrame;
import is.hotelzargo.presentacion.gui.book.BookFrame;

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
