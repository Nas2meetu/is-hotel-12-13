package is.hotelzargo.presentacion.employee.command.appservices;

import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionDelEmployee implements Command {

	private int id;
	
	public CommandActionDelEmployee(int id){
		this.id = id;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delEmployee(this.id);
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
