package is.hotelzargo.presentacion.book.command.appservices;

import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.util.Vector;

public class CommandActionFindBook implements Command {

	private String checkIn;
	private String checkOut;
	
	public CommandActionFindBook(Vector<String> dates){		
		this.checkIn = dates.get(0);
		this.checkOut = dates.get(1);
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			return facade.findBook(checkIn,checkOut);
		} catch (BookAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
