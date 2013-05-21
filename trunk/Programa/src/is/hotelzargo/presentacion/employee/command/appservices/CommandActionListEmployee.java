package is.hotelzargo.presentacion.employee.command.appservices;

import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;

public class CommandActionListEmployee implements Command {

	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			return facade.listEmployee();
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
		}
		return null;
	}

}
