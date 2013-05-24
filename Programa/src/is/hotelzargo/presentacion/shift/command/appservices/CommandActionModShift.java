package is.hotelzargo.presentacion.shift.command.appservices;

import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.negocio.shift.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionModShift implements Command {

	private ShiftTransfer shiftTransfer;
	
	public CommandActionModShift(ShiftTransfer t){
		shiftTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modShift(this.shiftTransfer);
			Controller.getInstance().event(Event.MESSAGE,"Turno modificado",null);
		} catch (ShiftAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
