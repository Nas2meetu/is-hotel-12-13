package is.hotelzargo.presentacion.shift.command.appservices;

import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionDelShift implements Command {
	
	private int id;
	
	public CommandActionDelShift(int id){
		this.id = id;
	}

	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delShift(this.id);
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
