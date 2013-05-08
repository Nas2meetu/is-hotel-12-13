package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionAddEmployee implements Command {

	private EmployeeTransfer employeeTransfer;
	
	public CommandActionAddEmployee(EmployeeTransfer t) {
		employeeTransfer = t;
	}
	
	@Override
	public void execute() {
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.addEmployee(employeeTransfer);
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
			//Controller.getInstance().event(Event.ERROR,e.getMessage());
			
		}
	}

}
