package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionModShift implements Command {

	private ShiftTransfer shiftTransfer;
	
	public CommandActionModShift(ShiftTransfer t){
		shiftTransfer = t;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modShift(this.shiftTransfer);
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
		}
	}

}
