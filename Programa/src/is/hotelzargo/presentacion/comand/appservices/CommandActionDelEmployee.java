package is.hotelzargo.presentacion.comand.appservices;

import is.hotelzargo.negocio.BusinessFactory;
import is.hotelzargo.negocio.Facade;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.presentacion.comand.Command;

public class CommandActionDelEmployee implements Command {

	private String id;
	
	public CommandActionDelEmployee(String id){
		this.id = id;
	}
	
	@Override
	public void execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.delEmployee(this.id);
		} catch (EmployeeAppServicesException e) {
			e.printStackTrace();
		}
	}

}
