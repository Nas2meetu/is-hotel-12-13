package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionAddShift implements Command {
	
	private ShiftTransfer shiftTransfer;
	
	public CommandActionAddShift(ShiftTransfer data){
		shiftTransfer = data;
	}

	@Override
	public void execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addShift(shiftTransfer);
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
	}
}
