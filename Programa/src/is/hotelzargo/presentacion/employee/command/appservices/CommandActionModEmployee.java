package is.hotelzargo.presentacion.employee.command.appservices;

import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;

public class CommandActionModEmployee implements Command {

	private EmployeeTransfer employeeTransfer;
	
	public CommandActionModEmployee(EmployeeTransfer t){
		employeeTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modEmployee(employeeTransfer);
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
		}
		return null;
	}

}
