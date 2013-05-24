package is.hotelzargo.presentacion.book.command.appservices;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionModBook implements Command {

	private BookTransfer bookTransfer;
	
	public CommandActionModBook(BookTransfer t){
		bookTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modBook(this.bookTransfer);
			Controller.getInstance().event(Event.MESSAGE,"Reserva modificada",null);
		} catch (BookAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
