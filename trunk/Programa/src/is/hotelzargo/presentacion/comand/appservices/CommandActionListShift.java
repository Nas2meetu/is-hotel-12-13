package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.ShiftAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListShift implements Command {

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listShift();
		} catch (ShiftAppServicesException e) {
			e.printStackTrace();
		}
	}

}
