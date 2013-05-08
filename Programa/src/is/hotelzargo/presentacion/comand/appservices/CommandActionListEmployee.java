package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionListEmployee implements Command {

	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.listEmployee();
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
		}
	}

}
