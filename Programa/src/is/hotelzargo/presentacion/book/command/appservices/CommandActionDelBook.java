package is.hotelzargo.presentacion.book.command.appservices;

import is.hotelzargo.negocio.exception.BookAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionDelBook implements Command {

	private int id;
	
	public CommandActionDelBook(int id)
	{
		this.id = id;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delBook(this.id);
		} catch (BookAppServicesException e) {
			e.printStackTrace();
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
