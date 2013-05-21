package is.hotelzargo.presentacion.employee.command.appservices;

import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;

public class CommandActionAddEmployee implements Command {

	private EmployeeTransfer employeeTransfer;
	
	public CommandActionAddEmployee(EmployeeTransfer t) {
		employeeTransfer = t;
	}
	
	@Override
	public Object execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addEmployee(employeeTransfer);
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
		return null;
	}

}
