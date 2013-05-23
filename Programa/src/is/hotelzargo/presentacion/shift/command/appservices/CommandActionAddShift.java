package is.hotelzargo.presentacion.shift.command.appservices;

import java.util.Vector;

import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionAddShift implements Command {
	
	private Vector<String> shiftTransfer;
	
	public CommandActionAddShift(Vector<String> data){
		shiftTransfer = data;
	}

	@Override
	public Object execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addShift(shiftTransfer);
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);			
		}
		return null;
	}
}
